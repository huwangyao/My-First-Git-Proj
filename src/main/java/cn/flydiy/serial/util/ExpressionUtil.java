package cn.flydiy.serial.util;

import cn.flydiy.Main;
import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.entity.CommonEntity;
import cn.flydiy.core.entity.Formula;
import cn.flydiy.core.service.FormulaService;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by player on 2017/10/23.
 */
public class ExpressionUtil {

    private static final DecimalFormat df =new DecimalFormat("#.00");

    /**
     * 计算List<Bean>配置了公式的值
     * @param beanList
     * @return
     */
    public static List<Map> workOutListBean(List<? extends CommonEntity> beanList){
        if(CollectionUtils.isEmpty(beanList)){
            return Collections.EMPTY_LIST;
        }else {
            Class<? extends CommonEntity> beanClass = beanList.get(0).getClass();
            List<Map> mapList = BeanUtil.convertBeansToMaps(beanList);
            workOutListMap(mapList,beanClass);
            return mapList;
        }
    }

    public static void workOutListMap(List<Map> mapList,Class<? extends CommonEntity> beanClass){
        Table table = beanClass.getAnnotation(Table.class);//在实体中通过JPA注解获取表名
        String tableCode = table.name();
        workOutListMap(mapList,tableCode);
    }

    public static void workOutListMap(List<Map> mapList,String tableCode){
        if(StringUtil.isEmpty(tableCode)){
            System.out.println("无法计算,该bean没有表名信息");
        }
        FormulaService formulaService = SpringContext.getBean(FormulaService.class);
        List<Formula> formulas = formulaService.queryByTableCode(tableCode);
        for (Map map : mapList) {
            JEP jep = getJEP(map);
            for (Formula formula : formulas) {
                String conditionExpr = formula.getConditionExpr();
                String formulaExpr = formula.getFormulaExpr();
                String evaluate = formula.getEvaluate();
                Object object = workOutKey(jep, map, conditionExpr, formulaExpr, evaluate);
                if(object != null){
                    jep.addVariable(evaluate, object);
                }
            }
        }
    }

    /**
     * 非动态配置公式方式
     * @param mapList
     * @param conditionExpr
     * @param formulaExpr
     * @param evaluate
     */
    public static void workOutListMap(List<Map> mapList,String conditionExpr,String formulaExpr, String evaluate){
        for (Map map : mapList) {
            JEP jep = getJEP(map);
            workOutKey(jep,map,conditionExpr,formulaExpr,evaluate);
        }
    }

    /**
     * 计算出表达式并填充
     * @param jep
     * @param map
     * @param conditionExpr
     * @param formulaExpr
     * @param evaluate
     */
    private static Object workOutKey(JEP jep,Map map,String conditionExpr, String formulaExpr, String evaluate){
        //如果有条件 且 条件为false
        if(StringUtil.isNotEmpty(conditionExpr) && !workOutBool(jep,conditionExpr)){
            return null;
        }
        Object value = workOutSingle(jep, formulaExpr,0);
        String format = String.format("%.2f",(Double) value);
        Double valueOf = null;
        try {
            valueOf = Double.valueOf(format);
            if(valueOf.isNaN() || valueOf.isInfinite()){
                valueOf = 0.0;
            }
        }catch (NumberFormatException e){
            valueOf = 0.0;
        }
        map.put(evaluate,valueOf);
        return valueOf;
    }

    /**
     * 判断条件表达式
     * @param jep
     * @param expression
     * @return
     */
    private static boolean workOutBool(JEP jep,String expression){
        return (Double)workOutSingle(jep,expression,0) > 0;
    }

    /**
     * 计算表达式的值
     * @param jep
     * @param expression
     * @return
     */
    private static Object workOutSingle(JEP jep,String expression,int time){
        Object result = null;
        try { //执行
            Node parse = jep.parse(expression);
            result = jep.evaluate(parse);
        } catch (ParseException e) {

            String errorInfo = jep.getErrorInfo();
            System.out.println(errorInfo);
            if (errorInfo!=null && errorInfo.startsWith("Unrecognized symbol \"") && time <5){
                String nullKey = errorInfo.split("\"")[1];
                jep.addVariable(nullKey,0);
                result = workOutSingle(jep,expression,time++);
            }else {
                throw new BaseRunTimeException("公式表达式:"+expression+"解析失败",e);
            }
        }
        if(result == null){
            throw new BaseRunTimeException("公式表达式:"+expression+"解析失败");
        }
        return result;
    }

    /**
     * 获取填充好变量的JEP对象
     * @param param
     * @return
     */
    private static JEP getJEP(Map param){
        JEP jep = new JEP();
        Set<Map.Entry> set = param.entrySet();
        for (Map.Entry entry : set) {
            Object entryValue = entry.getValue();
            String entryKey = (String) entry.getKey();
            jep.addVariable(entryKey, entryValue!=null?entryValue:0);
        }
        return jep;
    }

    public static void main(String[] args) {
        String expression = "basicAndMeritWages+mealSupplementMoney+FormalSupplement-sickMoney-leaveMoney-leaveDeduction+1000";

        double f = 19208.925;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String result = String.format("%.2f",f);
        System.out.println(result);
        System.out.println(f1);

//        List<Map> mapList = new ArrayList<>();
//        Map map = new HashMap();
//        map.put("basicAndMeritWages",2343.00);
//        map.put("mealSupplementMoney",0.00);
//        map.put("FormalSupplement",0.00);
//        map.put("sickMoney",0.00);
//        map.put("leaveMoney",0.00);
//        map.put("leaveDeduction",0.00);
//        mapList.add(map);
//
//        ExpressionUtil.workOutListMap(mapList,"", expression, "answerPay");
//                for (Map hashMap : mapList) {
//            System.out.println(hashMap.get("answerPay"));
//        }

    }
}
