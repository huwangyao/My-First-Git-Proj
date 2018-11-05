package cn.flydiy.core.service;

import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.ExceptionUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.dto.DBSaveParam;
import cn.flydiy.core.entity.Dict;
import cn.flydiy.core.entity.DictItem;
import cn.flydiy.core.repository.DictRepository;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.metadata.util.MetadataExcelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    DictRepository dictRepository;
    @Autowired
    DictItemService dictItemService;

    @Override
    public List<Map> queryEnumTerm(String title) {
        List<Dict> resultList = dictRepository.queryEnumTerm(title);
        return BeanUtil.convertBeansToMaps(resultList);
    }

    @Override
    public void deleteEnum(Dict dict) {
        String dictId = dict.getId();
        String dictCode = dict.getCode();
        // 删除枚举项
        this._deleteByIds(dictId);
        // 删除对应的枚举值
        dictItemService.deleteByCodes(dictCode);
    }

    @Override
    public void saveEnum(Dict dict) {
        String code = dict.getCode();
        List<Map> checkThieCode = this.queryEnumByCode(code);
        if(checkThieCode.size()>0){
            ExceptionUtil.throwBaseRunTimeException("sys.dict.code.exist.error",new String[]{code});
        }
        this.save(dict);

    }

    @Override
    public List<Map> queryEnumByCode(String code) {
        return BeanUtil.convertBeansToMaps(dictRepository.queryEnumByCode(code));
    }

    @Override
    public void updateEnum(Dict dict) {
        String code = dict.getCode();
        Dict oldDict = this.findOne(dict.getId());
        String oldCode = oldDict.getCode();

        // 如果code改变了
        if (!StringUtil.equalsIgnoreCase(code, oldCode)) {
            List<Map> checkThieCode = this.queryEnumByCode(code);
            if(checkThieCode.size()>0){
                ExceptionUtil.throwBaseRunTimeException("sys.dict.code.exist.error",new String[]{code});
            }
            // 更新枚举值的对应code值
            dictItemService.updateNewCodeByOldCode(oldCode,code);
        }
        this._updateEntityById(dict);
    }

    @Override
    public ByteArrayOutputStream exportDict(String fileName, String[] ids)  throws IOException {
        List<Dict> dictList = this.findByIds(ids);
        List<Map> dictMapList = BeanUtil.convertBeansToMaps(dictList);

        List<String> codeList = new ArrayList();
        for(Map map : dictMapList){
            String code = (String)map.get("code");
            codeList.add(code);
        }
        String[] codes = codeList.toArray(new String[]{});
        List<DictItem> dictItemList = dictItemService.queryDictItemByCodes(codes);
        List<Map> dictItemMapList = BeanUtil.convertBeansToMaps(dictItemList);

        // dict页签
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Dict");
        sheet.setDefaultColumnWidth(25);
        this.addDataToSheet(dictMapList,sheet);

        // dictItem页签
        HSSFSheet itemSheet = wb.createSheet("DictItem");
        itemSheet.setDefaultColumnWidth(25);
        this.addDataToSheet(dictItemMapList,itemSheet);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wb.write(output);
        output.flush();
        return output;
    }

    /**
     * 将数据整理到Excel中的页签中
     * @param list
     * @param sheet
     */
    public void addDataToSheet(List<Map> list,HSSFSheet sheet){
        int colNum = 0;
        int rownum = 0;
        //创建HSSFRow对象
        HSSFRow row = sheet.createRow(rownum++);

        // 字典项数据处理
        // 有数据就进入,没有就跳过
        if(list.size()>0) {
            // 第一行是 列名(属性名称)
            Map map = list.get(0);
            java.util.Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
                if(MetadataExcelUtil.canExportAttr((String)entry.getKey())) {
                    HSSFCell cell = row.createCell(colNum++);
                    cell.setCellValue((String) entry.getKey());
                }
            }
            //  后面是数据
            for(Map item:list){
                colNum = 0;
                // 加一行
                HSSFRow nextRow = sheet.createRow(rownum++);
                // 一列列数据
                java.util.Iterator data = item.entrySet().iterator();
                while (data.hasNext()) {
                    java.util.Map.Entry dataEntry = (java.util.Map.Entry) data.next();
                    if(MetadataExcelUtil.canExportAttr((String)dataEntry.getKey())) {
                        HSSFCell cell = nextRow.createCell(colNum++);
                        cell.setCellValue((String) dataEntry.getValue());
                    }
                }
            }
        }
    }

    /**
     * 解析Excel中的页签
     * @param sheet
     * @return
     */
    public List<Map> realExcelSheet(Sheet sheet){
        int lastRowNum = sheet.getLastRowNum();
        Row firstRow = sheet.getRow(0);
        short lastCellNum = firstRow.getLastCellNum();
        List<Map> list = new ArrayList();
        for (int i = 1; i <= lastRowNum; i++) {
            Map map = new HashMap();
            Row row = sheet.getRow(i);
            for (short j = 0; j < lastCellNum; j++) {
                Cell attrValueCell = row.getCell(j);
                if (attrValueCell == null) {
                    continue;
                }
                Cell cell = firstRow.getCell(j);
                String cellValue = cell.getStringCellValue();//实际上就是返回时候key的名字

                CellType cellTypeEnum = attrValueCell.getCellTypeEnum();
                map.put(cellValue, attrValueCell.getStringCellValue());
            }
            list.add(map);
        }
        return list;
    }

    @Override
    public Map importDict(InputStream inputStream){
        Map result = new HashMap();
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);    // 字典项的页签
        Sheet itemSheet = wb.getSheetAt(1);     // 字典值的页签

        List<Map> dictList = this.realExcelSheet(sheet);
        List<Map> dictItemList = this.realExcelSheet(itemSheet);

        result.put("dict",dictList);
        result.put("dictItem",dictItemList);
        return result;
    }

    public void importDict(String idSuffix, List<Dict> dicts,List<DictItem> dictItems) {
        if (CollectionUtils.isEmpty(dicts)){
            return ;
        }
        Set<String> enumCodes = new HashSet<>();
        for (Dict dict : dicts) {
            enumCodes.add(dict.getCode());
            dict.setId(subUnderLine(dict.getId())+idSuffix);
        }
        for (DictItem dictItem : dictItems) {
            dictItem.setId(subUnderLine(dictItem.getId())+idSuffix);
        }
        deleteByCodes(enumCodes.toArray(new String[]{}));

        DBSaveParam dbSaveParam = new DBSaveParam();
        dbSaveParam.setUseCurrCorpId(true);
        dbSaveParam.setUseCurrTenantId(true);
        dbSaveParam.setNoGenerateNewId(true);
        save(dbSaveParam,dicts.toArray(new Dict[]{}));
        dictItemService.save(dbSaveParam,dictItems.toArray(new DictItem[]{}));
    }

    private String subUnderLine(String id){
        if (id.length()>31) {
            int indexOf = id.lastIndexOf("_");
            if(indexOf>0){
                return id.substring(0,indexOf);
            }else {
                return id;
            }
        }else {
            return id;
        }
    }

    public void deleteByCodes(String... codes) {
        dictRepository.deleteByCodes(codes);
        dictItemService.deleteByCodes(codes);
    }

    @Override
    public void saveEnums(List<Map> dictList, List<Map> dictItemList) {
        List<Dict> dict = BeanUtil.convertMapsToBeans(dictList,Dict.class);
        for(Dict temp : dict){
            String code = temp.getCode();
            List<Map> checkThieCode = this.queryEnumByCode(code);
            if(checkThieCode.size()>0){
                ExceptionUtil.throwBaseRunTimeException("sys.dict.code.exist.error",new String[]{code});
            }
        }
        this.save(dict.toArray(new Dict[]{}));

        List<Map> dictItem = new ArrayList();
        for(Dict temp : dict){
            String code = temp.getCode();
            for(Map map : dictItemList){
                String itemCode = (String)map.get("code");
                if(StringUtil.equalsIgnoreCase(itemCode,code)){
                    dictItem.add(map);
                }
            }
        }
        List<DictItem> dictItemL = BeanUtil.convertMapsToBeans(dictItem,DictItem.class);
        dictItemService.save(dictItemL.toArray(new DictItem[]{}));
    }

    public List<Dict> queryByEnumCode(String... enumCodes) {
        return dictRepository.queryEnumByCode(enumCodes);
    }

    @Override
    public Map queryDictAndDictItemByCode(String code) {
        Map map = new HashMap();
        String[] codes= new String[]{code};
        // 查询字典项信息
        List<Dict> dictList = this.queryByEnumCode(codes);
        Dict dict = new Dict();
        if(dictList.size()>0){
            dict = dictList.get(0);
        }
        // 查询字典值信息
        List<DictItem> dictItems = dictItemService.queryDictItemByCodes(codes);

        map.put("dict",dict);
        map.put("dictItem",dictItems);

        return map;
    }

    @Override
    public void updateDictAndDictItems(Dict dict, List<DictItem> dictItems) {
        for(DictItem item : dictItems){
            item.setCode(dict.getCode());
        }
        if(StringUtil.isEmpty(dict.getId())){
            this.save(dict);
        }else{
            this._updateEntityById(dict);
        }
        dictItemService.deleteByCodes(dict.getCode());
        dictItemService.save(dictItems.toArray(new DictItem[]{}));
    }
}
