package cn.flydiy.serial.util;

import cn.flydiy.common.util.DateUtil;
import cn.flydiy.common.util.ExceptionUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.serial.entity.SerialNum;
import cn.flydiy.serial.entity.SerialRule;
import cn.flydiy.serial.service.SerialNumService;
import cn.flydiy.serial.service.SerialRuleService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yinyingyang on 2017/4/11 0011.
 */
public class SerialNumberUtils {

    //默认日期格式
    public static final String DEFAULT_DETAFORMAT = "yyyyMMdd";

    //默认起始序列号
    public static final long START_NUMBER = 1;

    //默认步长
    public static final int STEP = 1;

    private static String pattern = "(\\{\\{([\\w]*):?([\\w]*):?([\\w]*)?\\}\\})";
    //用此正则后，m.group(int)   int=   1:规则片段；2：类型（如STRING）；3\4：参数1\2

    private static Pattern compile = Pattern.compile(pattern);

    /**
     * 无map，从数据库获取规则
     */
    public static String getSerialNum(String table, String col){
        return getSerialNum(table,col, Collections.emptyMap());
    }

    /**
     * 无map，传入规则
     */
    public static String getSerialNum(String table, String col, String rule) {
        return getSerialNum(table,col,rule, Collections.emptyMap());
    }

    /**
     * 有map，从数据库获取规则
     */
    public static String getSerialNum(String table, String col, Map map) {
        SerialRuleService serialRuleService=SpringContext.getBean(SerialRuleService.class);
        //得到规则
        SerialRule serialRule = serialRuleService.queryByTableAndCol(table, col);
        if(serialRule==null){
            ExceptionUtil.throwBaseRunTimeException("sys.serial.empty.error",new String[]{table,col});
        }
        String rule = serialRule.getRule();
        //        String rule="aa{{STRING:code}}--{{DATE:yyyyMMdd}}--{{SEQ:6}}--{{DATESEQ:4:yyyyMMdd}}--{{ORGSEQ:4}}--{{ORGDATESEQ:4}}";//测试规则
        //测试此规则时参数需要有：table,col,code,可选参数ORG_ID
        return getSerialNum(table, col, rule,map);
    }

    /**
     * 有map，传入规则
     */
    public static String getSerialNum(String table, String col, String rule,Map map) {

        SerialNumService serialNumService=SpringContext.getBean(SerialNumService.class);

        if(StringUtil.isEmpty(rule)){
            ExceptionUtil.throwBaseRunTimeException("sys.serial.empty.error",new String[]{table,col});
        }

        String str=new String(rule);
        Matcher m = compile.matcher(str);
        while (m.find()) {
            //循环对规则片段进行处理
            String type = m.group(2);//规则类型
            String var = m.group(3);//参数1，可能是长度也可能是日期格式
            String format = m.group(4);//参数2，日期格式
            switch (type) {

                case "STRING":
                    String val;
                    if(( val= MapUtils.getString(map,var))!=null){
                        str = StringUtils.replace(str, m.group(1), val);
                    }else {
                        ExceptionUtil.throwBaseRunTimeException("sys.serial.string.error",new String[]{rule,m.group(1),var});
                    }
                    break;

                case "DATE":
                    str = StringUtils.replace(str,m.group(1), getDateByFormat(var));
                    break;

                case "SEQ":
                    //查询序列号
                    int seqLength = Integer.parseInt(var);

                    SerialNum seqSerialNum = serialNumService.queryByTableAndCol(table, col, type, null);
                    String seqNumber;
                    if (seqSerialNum != null) {
                        Long serialNumber = seqSerialNum.getSerialNumber();
                        seqNumber = switchNum( serialNumber + STEP, seqLength);
                        serialNumService.update(seqSerialNum.getId(), serialNumber+STEP, null);

                    } else {
                        //没找到则创建序列
                        serialNumService.newSerialNum(table,col,type,null,null);
                        seqNumber = switchNum( START_NUMBER, seqLength);
                    }

                    //替换
                    str = StringUtils.replace(str,m.group(1), seqNumber);
                    break;

                case "DATESEQ":
                    //获取日期格式，如果没有则使用默认
                    if (StringUtil.isBlank(format)) {
                        format = DEFAULT_DETAFORMAT;
                    }
                    //查询序列号
                    int dateSeqLength = Integer.parseInt(var);
                    SerialNum dateSeqNumberSerialNum = serialNumService.queryByTableAndCol(table, col, type, null);
                    String dateSeqNumber;
                    String date = getDateByFormat(format);

                    if (dateSeqNumberSerialNum != null) {
                        Long serialNumber;
                        if (date.equals(dateSeqNumberSerialNum.getDateRecord())) { //无需重置

                            serialNumber = dateSeqNumberSerialNum.getSerialNumber() + STEP;

                        }else {//日期记录与当前系统日期不同，重置序列号

                            serialNumber=START_NUMBER;

                        }
                        dateSeqNumber = switchNum(serialNumber, dateSeqLength);
                        serialNumService.update(dateSeqNumberSerialNum.getId(), serialNumber, date);

                    } else {
                        //没找到则创建序列
                        serialNumService.newSerialNum(table,col,type,null,date);
                        dateSeqNumber = switchNum(START_NUMBER, dateSeqLength);
                    }

                    //替换
                    str = StringUtils.replace(str,m.group(1), dateSeqNumber);
                    break;

                case "ORGSEQ":
                    //获取组织id，没有则使用当前登录用户的
                    String orgId=(String) map.get("ORG_ID");
                    if(StringUtil.isBlank(orgId)){
                        orgId=WebUtils.getLoginUser().getOrgId();
                    }

                    int orgSeqLength=Integer.parseInt(var);
                    SerialNum orgSerialNum= serialNumService.queryByTableAndCol(table, col, type,orgId);
                    String orgSerialNumber;

                    if (orgSerialNum != null) {

                        Long serialNumber = orgSerialNum.getSerialNumber()+STEP;
                        orgSerialNumber = switchNum( serialNumber , orgSeqLength);
                        serialNumService.update(orgSerialNum.getId(), serialNumber, null);

                    } else {
                        //没找到则创建序列
                        serialNumService.newSerialNum(table,col,type,orgId,null);
                        orgSerialNumber = switchNum(START_NUMBER, orgSeqLength);

                    }

                    str = StringUtils.replace(str,m.group(1), orgSerialNumber);
                    break;

                case "CORPCODE":
                    //获取法人信息
                    String corpId=WebUtils.getLoginUser().getCorpId();
                    Map org = serialNumService.queryOrgById(corpId);
                    String code = "";
                    // 获取法人的code
                    String corpCode=(String) org.get("code");
                    if(StringUtil.isNotEmpty(var)){
                        int corpSeqLength=Integer.parseInt(var);
                        if(corpSeqLength>corpCode.length()) {
                            code = corpCode;
                            for (int i = 0; i < corpSeqLength - corpCode.length(); i++) {
                                code = "0" + code;
                            }
                        }else if(corpSeqLength == corpCode.length()){
                            code = corpCode;
                        }else if(corpCode.length()>corpSeqLength && corpSeqLength>=0){
                            code = corpCode.substring(corpCode.length()-corpSeqLength,corpCode.length());
                        }else{
                            code = corpCode;
                        }
                    }else{
                        code = corpCode;
                    }

                    str = StringUtils.replace(str,m.group(1), code);
                    break;

                case "ORGDATESEQ":
                    //获取组织id,序列号长度和日期格式
                    String orgDateId=(String) map.get("ORG_ID");
                    if(StringUtil.isBlank(orgDateId)){
                        orgDateId=WebUtils.getLoginUser().getOrgId();
                    }
                    int orgDateLength=Integer.parseInt(var);
                    if(StringUtil.isBlank(format)){
                        format=DEFAULT_DETAFORMAT;
                    }

                    SerialNum orgDateSerialNum = serialNumService.queryByTableAndCol(table, col, type, orgDateId);
                    String orgDateSerialNumber;
                    String orgDate = getDateByFormat(format);

                    if (orgDateSerialNum != null) {
                        Long serialNumber;
                        if (orgDate.equals(orgDateSerialNum.getDateRecord())) { //无需重置

                            serialNumber = orgDateSerialNum.getSerialNumber() + STEP;

                        }else {//日期记录与当前系统日期不同，重置序列号

                            serialNumber=START_NUMBER;

                        }
                        orgDateSerialNumber = switchNum(serialNumber, orgDateLength);
                        serialNumService.update(orgDateSerialNum.getId(), serialNumber, orgDate);

                    } else {
                        //没找到则创建序列
                        serialNumService.newSerialNum(table,col,type,orgDateId,orgDate);
                        orgDateSerialNumber = switchNum(START_NUMBER, orgDateLength);

                    }
                    str = StringUtils.replace(str,m.group(1), orgDateSerialNumber);
                    break;

                default:
                    ExceptionUtil.throwBaseRunTimeException("sys.serial.rule.error",new String[]{rule,m.group(1)});
            }
        }
        if(StringUtil.equals(rule,str)){
            ExceptionUtil.throwBaseRunTimeException("sys.serial.rule.error",new String[]{rule,""});
        }
        return str;
    }
    /**
     * 无map，从数据库获取规则
     */
    public static String findSerialNum(String table, String col){
        return findSerialNum(table,col, Collections.emptyMap());
    }

    /**
     * 无map，传入规则
     */
    public static String findSerialNum(String table, String col, String rule) {
        return findSerialNum(table,col,rule, Collections.emptyMap());
    }

    /**
     * 有map，从数据库获取规则
     */
    public static String findSerialNum(String table, String col, Map map) {
        SerialRuleService serialRuleService=SpringContext.getBean(SerialRuleService.class);
        //得到规则
        SerialRule serialRule = serialRuleService.queryByTableAndCol(table, col);
        if(serialRule==null){
            ExceptionUtil.throwBaseRunTimeException("sys.serial.empty.error",new String[]{table,col});
        }
        String rule = serialRule.getRule();
        //        String rule="aa{{STRING:code}}--{{DATE:yyyyMMdd}}--{{SEQ:6}}--{{DATESEQ:4:yyyyMMdd}}--{{ORGSEQ:4}}--{{ORGDATESEQ:4}}";//测试规则
        //测试此规则时参数需要有：table,col,code,可选参数ORG_ID
        return findSerialNum(table, col, rule,map);
    }

    /**
     * 有map，传入规则
     */
    public static String findSerialNum(String table, String col, String rule,Map map) {

        SerialNumService serialNumService=SpringContext.getBean(SerialNumService.class);

        if(StringUtil.isEmpty(rule)){
            ExceptionUtil.throwBaseRunTimeException("sys.serial.empty.error",new String[]{table,col});
        }

        String str=rule;
        Matcher m = compile.matcher(str);
        while (m.find()) {
            //循环对规则片段进行处理
            String type = m.group(2);//规则类型
            String var = m.group(3);//参数1，可能是长度也可能是日期格式
            String format = m.group(4);//参数2，日期格式
            switch (type) {

                case "STRING":
                    String val;
                    if(( val= MapUtils.getString(map,var))!=null){
                        str = StringUtils.replace(str, m.group(1), val);
                    }else {
                        ExceptionUtil.throwBaseRunTimeException("sys.serial.string.error",new String[]{rule,m.group(1),var});
                    }
                    break;

                case "DATE":
                    str = StringUtils.replace(str,m.group(1), getDateByFormat(var));
                    break;

                case "SEQ":
                    //查询序列号
                    int seqLength = Integer.parseInt(var);

                    SerialNum seqSerialNum = serialNumService.queryByTableAndCol(table, col, type, null);
                    String seqNumber;
                    if (seqSerialNum != null) {
                        Long serialNumber = seqSerialNum.getSerialNumber();
                        seqNumber = switchNum( serialNumber + STEP, seqLength);

                    } else {
                        //没找到则创建序列
                        serialNumService.newSerialNum(table,col,type,null,null);
                        seqNumber = switchNum( START_NUMBER, seqLength);
                    }

                    //替换
                    str = StringUtils.replace(str,m.group(1), seqNumber);
                    break;

                case "DATESEQ":
                    //获取日期格式，如果没有则使用默认
                    if (StringUtil.isBlank(format)) {
                        format = DEFAULT_DETAFORMAT;
                    }
                    //查询序列号
                    int dateSeqLength = Integer.parseInt(var);
                    SerialNum dateSeqNumberSerialNum = serialNumService.queryByTableAndCol(table, col, type, null);
                    String dateSeqNumber;
                    String date = getDateByFormat(format);

                    if (dateSeqNumberSerialNum != null) {
                        Long serialNumber;
                        if (date.equals(dateSeqNumberSerialNum.getDateRecord())) { //无需重置

                            serialNumber = dateSeqNumberSerialNum.getSerialNumber() + STEP;

                        }else {//日期记录与当前系统日期不同，重置序列号

                            serialNumber=START_NUMBER;

                        }
                        dateSeqNumber = switchNum(serialNumber, dateSeqLength);

                    } else {
                        //没找到则创建序列
                        serialNumService.newSerialNum(table,col,type,null,date);
                        dateSeqNumber = switchNum(START_NUMBER, dateSeqLength);
                    }

                    //替换
                    str = StringUtils.replace(str,m.group(1), dateSeqNumber);
                    break;

                case "ORGSEQ":
                    //获取组织id，没有则使用当前登录用户的
                    String orgId=(String) map.get("ORG_ID");
                    if(StringUtil.isBlank(orgId)){
                        orgId=WebUtils.getLoginUser().getOrgId();
                    }

                    int orgSeqLength=Integer.parseInt(var);
                    SerialNum orgSerialNum= serialNumService.queryByTableAndCol(table, col, type,orgId);
                    String orgSerialNumber;

                    if (orgSerialNum != null) {

                        Long serialNumber = orgSerialNum.getSerialNumber()+STEP;
                        orgSerialNumber = switchNum( serialNumber , orgSeqLength);

                    } else {
                        //没找到则创建序列
                        serialNumService.newSerialNum(table,col,type,orgId,null);
                        orgSerialNumber = switchNum(START_NUMBER, orgSeqLength);

                    }

                    str = StringUtils.replace(str,m.group(1), orgSerialNumber);
                    break;

                case "ORGDATESEQ":
                    //获取组织id,序列号长度和日期格式
                    String orgDateId=(String) map.get("ORG_ID");
                    if(StringUtil.isBlank(orgDateId)){
                        orgDateId=WebUtils.getLoginUser().getOrgId();
                    }
                    int orgDateLength=Integer.parseInt(var);
                    if(StringUtil.isBlank(format)){
                        format=DEFAULT_DETAFORMAT;
                    }

                    SerialNum orgDateSerialNum = serialNumService.queryByTableAndCol(table, col, type, orgDateId);
                    String orgDateSerialNumber;
                    String orgDate = getDateByFormat(format);

                    if (orgDateSerialNum != null) {
                        Long serialNumber;
                        if (orgDate.equals(orgDateSerialNum.getDateRecord())) { //无需重置

                            serialNumber = orgDateSerialNum.getSerialNumber() + STEP;

                        }else {//日期记录与当前系统日期不同，重置序列号

                            serialNumber=START_NUMBER;

                        }
                        orgDateSerialNumber = switchNum(serialNumber, orgDateLength);

                    } else {
                        //没找到则创建序列
                        serialNumService.newSerialNum(table,col,type,orgDateId,orgDate);
                        orgDateSerialNumber = switchNum(START_NUMBER, orgDateLength);

                    }
                    str = StringUtils.replace(str,m.group(1), orgDateSerialNumber);
                    break;

                default:
                    ExceptionUtil.throwBaseRunTimeException("sys.serial.rule.error",new String[]{rule,m.group(1)});
            }
        }
        if(StringUtil.equals(rule,str)){
            ExceptionUtil.throwBaseRunTimeException("sys.serial.rule.error",new String[]{rule,""});
        }
        return str;
    }

    /**
     * 根据格式返回当前系统时间
     */
    public static String getDateByFormat(String format) {
        return DateUtil.toDateString(new Date(), format);
    }

    /**
     * 转换序列号格式
     */
    public static String switchNum(long num,int length){
        return length==0?num+"":StringUtil.fillAndSubstrToLen('0', num + "", length);
    }

    /*
    对规则进行检查，无错误返回null，有错返回第一段出错的片段
     */
    public static String checkRule(String rule){
        String str=rule;
        Matcher m = compile.matcher(str);
        while (m.find()) {
            String ru = m.group(2);
            switch (ru) {

                case "STRING":
                    if(m.group(3)==null){
                        return ru;
                    }
                    str=str.substring(m.group(1).length());
                    break;

                case "DATE":
                    try {
                        new SimpleDateFormat(m.group(3)).format(new Date());
                    }catch (Exception e){
                        return ru;
                    }
                    str=str.substring(m.group(1).length());
                    break;

                case "SEQ":
                    try {
                        Integer.parseInt(m.group(3));
                    }catch (Exception e){
                        return ru;
                    }
                    str=str.substring(m.group(1).length());
                    break;

                case "DATESEQ":
                    String format = m.group(3);
                    if(!StringUtil.isEmpty(format)) {
                        try {
                            new SimpleDateFormat(format).format(new Date());
                        } catch (Exception e) {
                            return ru;
                        }
                    }
                    str=str.substring(m.group(1).length());
                    break;

                case "ORGSEQ":
                    try {
                        Integer.parseInt(m.group(3));
                    }catch (Exception e){
                        return ru;
                    }
                    str=str.substring(m.group(1).length());
                    break;

                case "ORGDATESEQ":
                    try {
                        Integer.parseInt(m.group(3));
                        String orgFormat = m.group(4);
                        if(!StringUtil.isEmpty(orgFormat)) {
                            new SimpleDateFormat(orgFormat).format(new Date());
                        }
                    }catch (Exception e){
                        return ru;
                    }
                    str=str.substring(m.group(1).length());
                    break;

                default:
                    return ru;
            }
        }
        return null;
    }

}




/*
现已做好的

 {{DATE:yyyyMMdd}}  --- DATE前缀，表示日期，yyyyMMdd是日期格式，该字符串会被按照yyyyMMdd格式化的日期替换
 {{SEQ:8}} --- SEQ前缀，表示使用序列号，8为该序列号的长度
 {{DATESEQ:6:yyyyMMdd}} --- DATESEQ前缀，表示时间序列号，按时间重置序列号，6：表示序列号长度，（yyyyMMdd是日期格式，可不写，默认按天重置）
 {{ORGSEQ:6}} --- ORGSEQ前缀为组织序列号，6为该序列号的长度
 {{ORGDATESEQ:4:yyyyMMdd}} --- ORGDATESEQ前缀，组织时间序列号，4为该序列号的长度，（yyyyMMdd是日期格式，可不写，默认按天重置）

 例如：fly_{{DATE:yyyyMMdd}}_{{SEQ:6}} 会被转换成 fly_20170430_012345
 */



/*
 {{STRING:code}} --- STRING前缀，表示字符串，传进什么字符串就被什么字符串给替换
 {{DATE:yyyyMMdd}}  --- DATE前缀，表示日期，yyyyMMdd是日期格式，该字符串会被按照yyyyMMdd格式化的日期替换
 {{SEQ:8}} --- SEQ前缀，表示使用序列号，8为该序列号的长度
 {{DATESEQ:6:yyyyMMdd}} --- DATESEQ前缀，表示时间序列号，按时间重置序列号，6：表示序列号长度，（yyyyMMdd是日期格式，可不写，默认按天重置）
 {{ORGSEQ:6}} --- ORGSEQ前缀为组织序列号，不设时使用当前登录用户的组织序列号，6为该序列号的长度
 {{ORGDATESEQ:4:yyyyMMdd}} --- ORGDATESEQ前缀，组织时间序列号，4为该序列号的长度，（yyyyMMdd是日期格式，可不写，默认按天重置）

注：.组织序列号和组织时间序列号需要使用到orgId，默认使用当前登录用户的rogId，也可以在页面中传入key为ORG_ID的paramMap

 例如：fly_{{STRING:code}}_{{DATE:yyyyMMdd}}_{{SEQ:6}} 传入参数code=test会被转换成 fly_test_20170430_012345
 */