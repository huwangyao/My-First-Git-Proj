package cn.flydiy.core.service;

import cn.flydiy.base.util.SystemUtil;
import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.cache.CacheManager;
import cn.flydiy.core.cache.CacheVoiceAssistant;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.metadata.cache.MetadataPool;
import cn.flydiy.metadata.cache.RoutePathPool;
import cn.flydiy.metadata.constant.Const;
import cn.flydiy.metadata.dict.OperationType;
import cn.flydiy.metadata.entity.GenBizObject;
import cn.flydiy.metadata.entity.GenPageContent;
import cn.flydiy.metadata.entity.RecognitionRecord;
import cn.flydiy.metadata.param.Metadata;
import cn.flydiy.metadata.service.GenBizObjectService;
import cn.flydiy.metadata.service.GenPageContentService;
import cn.flydiy.metadata.service.RecognitionRecordService;
import cn.flydiy.metadata.util.AnsjUtil;
import cn.flydiy.metadata.util.Cosine;
import cn.flydiy.web.entity.Menu;
import cn.flydiy.web.service.MenuService;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by player on 2018/1/9.
 */
@Service
public class VoiceAssistantServiceImpl implements VoiceAssistantService{

    private boolean flag = false;

    @Autowired
    private GenBizObjectService objectService;

    @Autowired
    private GenPageContentService pageService;

    @Autowired
    private RecognitionRecordService recordService;

    @Autowired
    private MenuService menuService;

    @Override
    public ResponseData analyzeSentence(String sentence) {
        initDictory();
        long start = System.currentTimeMillis();
        Result result = ToAnalysis.parse(sentence);//分词结果的一个封装，主要是一个List<Term>的terms
        for (Term term : result.getTerms()) {
            System.out.println(term.toString());
        }
        long end = System.currentTimeMillis();

        RecognitionRecord recognitionRecord = new RecognitionRecord();
        recognitionRecord.setSentence(sentence);
        recognitionRecord.setSegTime(end-start);//分词耗时
        List<Term> terms = result.getTerms();
        OperationType operationType = getOperationType(terms);
        recognitionRecord.setOperationType(operationType == null?"null":operationType.name());

        ResponseData responseData = handleOperation(terms, recognitionRecord);
        recordService.save(recognitionRecord);
        if(responseData == null){
            return new ResponseData(recognitionRecord);
        }else {
            return responseData;
        }
    }

    @Override
    public List<Term> test(String sentence) {
        initDictory();
        Result parse = ToAnalysis.parse(sentence);
        for (Term term : parse.getTerms()) {
            System.out.println(term.toString());
        }
        AnsjUtil.nOfAfterV(parse.getTerms());
        return parse.getTerms();
    }

    /**
     *
     * @param terms
     * @param recognitionRecord
     * @return
     */
    private ResponseData handleOperation(List<Term> terms,RecognitionRecord recognitionRecord){
        ResponseData responseData = null;
        Term term = null;
        StringBuffer segString = new StringBuffer();
        //是否有录入到字典的关键字
        for (Term temp : terms) {
            String natureStr = temp.getNatureStr();
            if(CacheVoiceAssistant.MENU_FLAG.equals(natureStr) || CacheVoiceAssistant.PAGE_FLAG.equals(natureStr) || CacheVoiceAssistant.OBJ_FLAG.equals(natureStr)){
                term = temp;
            }
            segString.append(temp.toString());
        }
        recognitionRecord.setSegResult(segString.toString());

        String key = AnsjUtil.nOfAfterV(terms);
        //如果当前在进行路由跳转的话
        if(OperationType.SKIP.name().equals(recognitionRecord.getOperationType())){
            String routePath = "";
            if(term != null){
                if (CacheVoiceAssistant.MENU_FLAG.equals(term.getNatureStr())) {
                    List<Menu> menus = menuService.queryByName(term.getName());
                    //除非中途菜单被删了才有可能出现被删掉的情况
                    if(CollectionUtils.isNotEmpty(menus)){
                        routePath = menus.get(0).getUrl();
                    }
                }else if(CacheVoiceAssistant.PAGE_FLAG.equals(term.getNatureStr())){
                    List<GenPageContent> genPageContents = pageService.queryByPageName(term.getName());
                    if(CollectionUtils.isNotEmpty(genPageContents)){
                        routePath = "/main/" + RoutePathPool.ROUTE_PATH_POOL.getRoutePath(genPageContents.get(0).getDirId()) + Const.PATH_SEPARATOR + genPageContents.get(0).getPageCode();
                    }
                }
            }else {//这时就要去找相似度最高的了(只找菜单的就好了)
                List<String> menuNames = CacheVoiceAssistant.getCompareArr(CacheVoiceAssistant.DICT_CACHE_PREFIX,CacheVoiceAssistant.MENU_FLAG);
                String result = Cosine.getMostSimilar(key, menuNames);
                if(StringUtil.isEmpty(result)){
                    List<String> pageNames = CacheVoiceAssistant.getCompareArr(CacheVoiceAssistant.DICT_CACHE_PREFIX, CacheVoiceAssistant.PAGE_FLAG);
                    result = Cosine.getMostSimilar(key, pageNames);
                    if(StringUtil.isEmpty(result)) {
                        throw new BaseRunTimeException("core.voice.not.match.menuOrPage");
                    }
                    List<GenPageContent> genPageContents = pageService.queryByPageName(result);
                    routePath = "/main/" + RoutePathPool.ROUTE_PATH_POOL.getRoutePath(genPageContents.get(0).getDirId()) + Const.PATH_SEPARATOR + genPageContents.get(0).getPageCode();
                }else{
                    List<Menu> menus = menuService.queryByName(result);
                    routePath = menus.get(0).getUrl();
                }
            }
            recognitionRecord.setRouthPath(routePath);
        //如果当前在进行查询操作的话
        }else if(OperationType.SELECT.name().equals(recognitionRecord.getOperationType())){
            String tableCode = findTableCode(term,key);
            StringBuffer sqlString = new StringBuffer();
            Metadata metadata = MetadataPool.MD_POOL.getMetadata(tableCode);
            sqlString.append("select ");
            int count = 0;
            Map labelInfo = new HashMap();
            labelInfo.put("$title",metadata.getBizObject().getEntityName());
            labelInfo.put("$table",tableCode);
            for (Map<String, Object> objectMap : metadata.getAttrs()) {
                String attrCode = MapUtils.getString(objectMap, "attrCode");
                if(StringUtil.isEmpty(MapUtils.getString(objectMap,"remark")) && !"password".equals(attrCode.toLowerCase())){
                    labelInfo.put(attrCode,MapUtils.getString(objectMap, "attrName"));
                    sqlString.append(attrCode).append(",");
                    count++;
                }
            }
            if(count == 0){
                sqlString.append("*");
            }else {
                sqlString.deleteCharAt(sqlString.length()-1);
            }
            sqlString.append(" from ").append(tableCode).append(" where 1=1 ");
            List<Map> objectData = objectService.executeSql(sqlString.toString(),null);
            Map result = new HashMap();
            result.put("data",objectData);
            result.put("labelInfo",labelInfo);
            responseData = new ResponseData();
            responseData.setObj(result);
        }
        return responseData;
    }

    /**
     * 找到表名
     * @param term
     * @param key
     * @return
     */
    private String findTableCode(Term term,String key){
        String tableCode = null;
        String entityName = term == null ? "" : term.getName();
        //如果字典里面没有
        if(StringUtil.isEmpty(entityName)){
            List<String> entityNames = CacheVoiceAssistant.getCompareArr(CacheVoiceAssistant.DICT_CACHE_PREFIX,CacheVoiceAssistant.OBJ_FLAG);
            entityName = Cosine.getMostSimilar(key, entityNames);
            if(StringUtil.isEmpty(entityName)){
                throw new BaseRunTimeException("core.voice.not.match.obj");
            }
        }
        List<GenBizObject> bizObjects = objectService.queryByEntityName(entityName);
        if (CollectionUtils.isNotEmpty(bizObjects)) {
            GenBizObject genBizObject = bizObjects.get(0);
            tableCode = genBizObject.getTableCode();
        }
        return tableCode;
    }

    /**
     * 找到操作类型
     * @param terms
     * @return
     */
    private OperationType getOperationType(List<Term> terms){
        OperationType result = null;
        for (Term term : terms) {
            String natureStr = term.getNatureStr();
            String name = term.getName();
            //判断操作类型(目前就两种)
            if ("v".equals(natureStr)) {
                if (name.indexOf("跳") > -1 || name.indexOf("进") > -1 || name.indexOf("打开") > -1) {
                    result = OperationType.SKIP;
                    break;
                } else if (name.indexOf("查") > -1) {
                    result = OperationType.SELECT;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 初始化字典
     */
    private void initDictory() {
        if(!flag){
            List<String> entityNames = CacheVoiceAssistant.getCompareArr(CacheVoiceAssistant.DICT_CACHE_PREFIX, CacheVoiceAssistant.OBJ_FLAG);
            List<String> pageNames = CacheVoiceAssistant.getCompareArr(CacheVoiceAssistant.DICT_CACHE_PREFIX,CacheVoiceAssistant.PAGE_FLAG);
            List<String> menuNames = CacheVoiceAssistant.getCompareArr(CacheVoiceAssistant.DICT_CACHE_PREFIX,CacheVoiceAssistant.MENU_FLAG);

            for (String entityName : entityNames) {
                DicLibrary.insert(DicLibrary.DEFAULT, entityName,CacheVoiceAssistant.OBJ_FLAG,1000);
            }
            for (String pageName : pageNames) {
                DicLibrary.insert(DicLibrary.DEFAULT, pageName,CacheVoiceAssistant.PAGE_FLAG,1000);
            }
            for (String menuName : menuNames) {
                DicLibrary.insert(DicLibrary.DEFAULT, menuName,CacheVoiceAssistant.MENU_FLAG,1100);
            }
            flag = true;
        }
    }
}
