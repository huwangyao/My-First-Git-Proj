package cn.flydiy.hangj.cache;

import cn.flydiy.bids.cache.DataSourceCache;
import cn.flydiy.bids.entity.Ds;
import cn.flydiy.bids.util.BidsUtils;
import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.ResourceUtils;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.dto.StaffDto;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import org.apache.commons.collections4.MapUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StaffInfoCache {

    public static StaffInfoCache staffCache = new StaffInfoCache();

    private Set<String> newStaffMap = new HashSet<>(512);

    private Map<String,StaffDto> staffIdKeyMap = new ConcurrentHashMap(65536);

    private Map<String,String> fullNameKeyMap = new ConcurrentHashMap(65536);

    private String baseSql = "SELECT \n" +
            "T01.StaffID, --员工ID\n" +
            "T02.ChnName ,--姓名\n" +
            "T02.RtxName AS EnglishName,--英文名\n" +
            "T02.Name ,--fullname\n" +
            "t01.StaffTypeCode ,--员工类型编码\n" +
            "t01.StaffTypeName ,--员工类型描述\n" +
            "T03.BGOrganizationID ,--BG编码\n" +
            "T03.BGOrganizationName, --bg名\n" +
            "T03.DepOrganizationID ,--部门编码\n" +
            "T03.DepOrganizationName,--部门名\n" +
            "T01.PostID,--岗位编码\n" +
            "T01.PostName,--岗位名\n" +
            "T01.ManagerPositionID,--titleId\n" +
            "T01.ManagerPositionName,--title\n" +
            "T02.BirthDate,--生日\n" +
            "CONCAT(T02.NativeProvince,T02.NativeCity) AS [Native],--籍贯\n" +
            "T02.EducationID,--学历编码\n" +
            "t02.EducationName,--学历\n" +
            "replace(t02.HighSchool,'0','') AS HighSchool,--毕业院校\n" +
            "T02.Major, --专业\n" +
            "CONCAT('http://r.hrc.oa.com/custom/150/',t02.RtxName,'.png') AS PhotoUrl,--招聘url\n" +
            "t01.LastHireDate,--入职日期\n" +
            "t02.GenderName,--性别\n" +
            "t01.HRStatus,--在职状态\n" +
            "t02.ContractCompanyName,--合同所属公司\n" +
            "t02.BusnEmail,--email\n" +
            "t02.RtxName,--rtx\n" +
            "t02.WeChat,--微信\n" +
            "IIF(t02.WeChat='0','',t02.WeChat) AS WeChat2, --微信号\n" +
            "T01.LocationName,--工作地\n" +
            "T01.PositionName,--职位\n" +
            "T04.OrganizationFullName --组织机构全路径\n" +
            "FROM \n" +
            "DM.DM_DIM_Job T01\n" +
            "LEFT JOIN \n" +
            "DM.DM_DIM_StaffCurrentBaseInfo T02\n" +
            "ON T01.StaffID=T02.StaffID\n" +
            "LEFT JOIN [DM].[DM_DIM_OrganizationHierarchyCurrent] T03\n" +
            "ON T03.OrganizationID=T01.OrganizationID\n" +
            "LEFT JOIN DM.DM_DIM_Organization T04\n" +
            "ON T04.OrganizationID=T01.OrganizationID AND T04.CurrentFlag=1\n" +
            "WHERE T01.CurrentFlag=1 AND T01.HRStatus='A' and t01.EmployeeRCD=0 ";

    private Ds ds = new Ds();
    {
        String url = ResourceUtils.getProperty("esf.ps.staff.db.url");
        String user = ResourceUtils.getProperty("esf.ps.staff.db.user");
        String password = ResourceUtils.getProperty("esf.ps.staff.db.password");
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPasswd(password);

        loadAllStaffInfo();
    }

    /**
     * 加载全部员工
     */
    private void loadAllStaffInfo(){
        List<StaffDto> staffDtos = getStaffsBySql(baseSql,Collections.emptyList());
        for (StaffDto staffDto : staffDtos) {
            String id = staffDto.getStaffID();
            staffIdKeyMap.put(id,staffDto);
            fullNameKeyMap.put(staffDto.getEnglishName() + "(" + staffDto.getChnName() + ")",staffDto.getStaffID());
        }
    }

    /**
     * 根据sql获取人员
     * @param sql
     * @param params
     * @return
     */
    public List<StaffDto> getStaffsBySql(String sql,List<Map> params){
        DruidDataSource dataSource = DataSourceCache.DS_CACHE.getDataSource(ds);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StaffDto> staffDtos = null;

        try {
            //获取链接
            connection = dataSource.getConnection().getConnection();

            if(CollectionUtils.isNotEmpty(params)){
                for (Map param : params) {
                    String name = MapUtils.getString(param, "name");
                    String condition = MapUtils.getString(param, "condition");
                    String value = MapUtils.getString(param, "value");

                    sql = sql + " AND " + name + " " + condition + " ? " ;
                }
            }
            //执行SQL
            preparedStatement = connection.prepareStatement(sql);

            if(CollectionUtils.isNotEmpty(params)){
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject((i+1), params.get(i).get("value"));
                }
            }
            resultSet = preparedStatement.executeQuery();
            List<Map> allStaffInfo = BidsUtils.resultSetToListMap(resultSet);
            staffDtos = BeanUtil.convertMapsToBeans(allStaffInfo, StaffDto.class);
        } catch (SQLException e) {
            throw new BaseRunTimeException("数据库操作异常");
        }finally {
            boolean success = true;
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    success = false;
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    success = false;

                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    success = false;
                }
            }
            if(!success){
                throw new BaseRunTimeException("数据库操作异常");
            }
        }
        return staffDtos;
    }

    public StaffDto findOne(String staffId){
        StaffDto map = staffIdKeyMap.get(staffId);
        return map;
    }

    public List<StaffDto> queryByIds(String... staffIds){
        List<StaffDto> staffDtos = new ArrayList<>();
        for (String staffId : staffIds) {
            StaffDto staffDto = staffIdKeyMap.get(staffId);
            staffDtos.add(staffDto);
        }
        return staffDtos;
    }

    public StaffDto queryByEngName(String engName){
        Set<String> keySet = fullNameKeyMap.keySet();
        StaffDto staffDto = null;
        for (String key : keySet) {
            String eng = key.substring(0, key.indexOf("("));
            if(eng.equals(engName)){
                staffDto = staffIdKeyMap.get(fullNameKeyMap.get(key));
                break;
            }
        }
        return staffDto;
    }

    public List<String> queryIdLikeName(String name){
        Set<String> keySet = fullNameKeyMap.keySet();
        List<String> ids = new ArrayList<>();
        for (String key : keySet) {
            if(key.indexOf(name) > -1){
                String id = fullNameKeyMap.get(key);
                ids.add(id);
            }
        }
        return ids;
    }

    public List<StaffDto> queryByParams(List<Map> params){
        return getStaffsBySql(baseSql,params);
    }

    public boolean isNewStaff(String engName) {
        return newStaffMap.contains(engName);
    }

    private StaffInfoCache() {
    }

    public List<String> queryIdByEngNames(List<String> engNames) {
        List<String> ids = new ArrayList<>();
        Set<String> keySet = fullNameKeyMap.keySet();
        for (String key : keySet) {
            String eng = key.substring(0, key.indexOf("("));
            if(engNames.contains(eng)){
                ids.add(fullNameKeyMap.get(key));
                break;
            }
        }
        return ids;
    }
}
