package cn.flydiy.core.service;

import cn.flydiy.common.util.ExceptionUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.entity.Org;
import cn.flydiy.core.entity.User;
import cn.flydiy.core.constants.LoginStatus;
import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.core.repository.UserRepository;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.service.RoleUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrgService orgService;

    @Override
    public BaseRepository getCurrRepository(){
        return userRepository;
    }

    @Override
    public User findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public List<Map> queryAllUser() {
        List<User> userList = userRepository.queryAllUser();
        return BeanUtil.convertBeansToMaps(userList);
    }

    @Override
    public List<Map> queryUserListByOrgId(String orgId) {
        List<User> userInfoList = userRepository.queryAllUserByOrgId(orgId);
        return BeanUtil.convertBeansToMaps(userInfoList);
    }

    @Override
    public Map querySonOrgAndUserByOrgId(String parentId,String type,String roleId,String filterType) {

        List<Org> allOrgList = orgService.queryAllOrg(filterType);

        Map map = new HashMap();
        // 查询下级组织
        List<Org> orgObjList = new ArrayList();
        for(Org org : allOrgList){
            if(StringUtil.equals(org.getParentId(),parentId)){
                orgObjList.add(org);
            }
        }

        List<Map> userList = new ArrayList();
        // 下级组织信息
        List<Map> orgList = BeanUtil.convertBeansToMaps(orgObjList);

        // 人员选择组件
        if(StringUtil.equalsIgnoreCase(type,"user")){
            // 查询该组织下的人员
            List<Map> tempList = this.queryUserListByOrgId(parentId);

            // 是否要角色过滤
            List<String> roleIds = new ArrayList();
            if(StringUtil.isNotEmpty(roleId)){
                roleIds = SpringContext.getBean(RoleUserService.class).findUserIdByRoleId(roleId);
                // 处理当前组织的人员
                String str = roleIds.toString();
                for(Map userMap:tempList){
                    String userId = (String)userMap.get("id");
                    if(str.indexOf(userId)>=0){
                        userList.add(userMap);
                    }
                }
            }else{
                userList = tempList;
            }
            String idStr = roleIds.toString();

            // 查询组织中的人数
            List<User> allUserList = userRepository.queryAllUser();

            List<Org> orgListToSum = orgService.queryAllOrg(filterType);
            for(Map orgMap:orgList){
                Org orgBean = new Org();
                // 统计该组织下的所有组织节点
                List<Org> nextOrgList = this.getNextNodeOrgList((String)orgMap.get("id"),orgListToSum);
                BeanUtil.copyPropertiesToBean(orgBean,orgMap);
                nextOrgList.add(orgBean);
                // 统计该组织下的所有组织节点的人数
                int count = this.countThisOrgListUserNumber(nextOrgList,allUserList,roleId,idStr);
                orgMap.put("count",count);
            }
        }else if(StringUtil.equalsIgnoreCase(type,"org")){
            // 组织选择
            for(Map orgMap:orgList){
                String orgId = (String)orgMap.get("id");
                boolean isHasNext = this.isHasNextOrg(orgId,allOrgList);
                orgMap.put("isHasNext",isHasNext);
            }
        }


        // 查询上级到根节点的组织信息(面包屑数据)
        List<Org> breadList = this.getUserBreadCrunb(parentId,allOrgList);
        // 整理面包屑
        List<Org> fatherOrgList = new ArrayList<Org>();
        String orgParentId = "-1";
        int index = breadList.size();
        for(int i =0;i<index;i++){
            for(Org org : breadList){
                if(StringUtil.equalsIgnoreCase(org.getParentId(),orgParentId)){
                    orgParentId = org.getId();
                    fatherOrgList.add(org);
                    break;
                }
            }
        }


        map.put("userList",userList);
        map.put("orgList",orgList);
        map.put("fatherOrgList",fatherOrgList);
        return map;
    }

    public boolean isHasNextOrg(String orgId,List<Org> allOrgList){
        List<Org> orgObjList = new ArrayList();
        for(Org org : allOrgList){
            if(StringUtil.equalsIgnoreCase(org.getParentId(),orgId)){
                orgObjList.add(org);
            }
        }
        if(orgObjList.size()>0){
            return true;
        }else{
            return false;
        }
    }

    // 处理人员组件中显示的面包屑
    public List<Org> getUserBreadCrunb(String orgId,List<Org> allOrgList){
        Org org = new Org();
        for(Org orgObj:allOrgList){
            if(StringUtil.equals(orgObj.getId(),orgId)){
                org = orgObj;
                break;
            }
        }
        if(StringUtil.isEmpty(org.getId())){
            ExceptionUtil.throwBaseRunTimeException("sys.org.noexists.error",new String[]{orgId});
        }
        List<Org> resultList = new ArrayList();
        resultList.add(org);
        String parentId = org.getParentId();
        if(!StringUtil.equals(parentId,"-1")){
            List<Org> fatherList = this.getUserBreadCrunb(parentId,allOrgList);
            resultList.addAll(fatherList);
        }
        return resultList;
    }

    // 统计该组织下的所有组织节点的人数
    public int countThisOrgListUserNumber(List<Org> orgList,List<User> userList,String roleId,String idStr){

        int count = 0;
        for(Org org:orgList){
            String orgId = org.getId();
            for(User user:userList){
                // 如果需要角色过滤
                if(StringUtil.isNotEmpty(roleId)){
                    if(StringUtil.equalsIgnoreCase(orgId,user.getOrgId()) && idStr.indexOf(user.getId())>=0){
                        count++;
                    }
                }else{
                    if(StringUtil.equalsIgnoreCase(orgId,user.getOrgId())){
                        count++;
                    }
                }

            }
        }

        return count;
    }

    // 获取该节点的所有下级节点的列表
    public List<Org> getNextNodeOrgList(String parentId,List<Org> nextNodeOrgList){
        List<Org> sonSonOrgList = new ArrayList<Org>();
        List<Org> sonOrgList = new ArrayList<Org>();
        for(Org org:nextNodeOrgList){
            if(StringUtil.equalsIgnoreCase(org.getParentId(),parentId)){
                sonSonOrgList.add(org);
                sonOrgList.add(org);
            }
        }

        if(sonSonOrgList.size()>0){
            for(Org org:sonSonOrgList){
                sonOrgList.addAll(this.getNextNodeOrgList(org.getId(),nextNodeOrgList));
            }
        }

        return sonOrgList;
    }

    @Override
    public List<Map> queryUserListByIds(String[] ids) {
        List<User> allUserList = userRepository.queryAllUser2();
        List<User> userList = new ArrayList<User>();
        for(String id:ids){
            boolean flag = false;
            for(User user:allUserList){
                if(StringUtil.equalsIgnoreCase(id,user.getId())){
                    userList.add(user);
                    flag = true;
                    break;
                }
            }
            if (!flag){
                User emptyUser = new User();
                emptyUser.setId(id);
                emptyUser.setRealName("该用户不存在");
                userList.add(emptyUser);
            }
        }
        return BeanUtil.convertBeansToMaps(userList);
    }

    @Override
    public List<User> queryUserByParams(String param,String parentId,String roleId) {
        List<User> userList = new ArrayList();
        List<User> tempList = userRepository.queryUserByParams(param);
        // 是否要角色过滤
        List<String> roleIds = new ArrayList();
        if(StringUtil.isNotEmpty(roleId)){
            roleIds = SpringContext.getBean(RoleUserService.class).findUserIdByRoleId(roleId);
            // 处理当前组织的人员
            for(User user:tempList){
                String userId = user.getId();
                if(roleIds.contains(userId)){
                    userList.add(user);
                }
            }
            tempList = userList;
        }else{
            userList = tempList;
        }
        // 是否有组织过滤
        if(StringUtil.isNotEmpty(parentId)){
            userList = new ArrayList();
            List<Org> orgListToSum = orgService.queryAllOrg();
            // 统计该组织下的所有组织节点
            List<Org> nextOrgList = this.getNextNodeOrgList(parentId,orgListToSum);
            nextOrgList.add(orgService.findOne(parentId));
            List<String> userIds = nextOrgList.stream().map(Org::getId).distinct().collect(Collectors.toList());

            for(User user:tempList){
                String orgId = user.getOrgId();
                if(userIds.contains(orgId)){
                    userList.add(user);
                }
            }
        }
        return userList;
    }

    // 获取部门经理的
    public List<String> queryDepartManagerByUserId(Map map){
        List<String> ids = new ArrayList();
        // 获取工作流提交申请人的信息
        String userId = (String) map.get("creator");
        // 获取用户信息
        User user = userRepository.findOne(userId);

        String leaderId = queryDepartLeaderId(user.getOrgId(),userId);
        ids.add(leaderId);
        return ids;
    }

    // 查询上级部门的负责人
    public String queryDepartLeaderId(String orgId ,String userId){
        // 获取用户所在部门信息
        Org org = orgService.findOne(orgId);
        // 获取部门的负责人
        String leader = org.getLeader();
        if(StringUtil.isEmpty(leader)){
            leader = "213";
        }else{
            if(StringUtil.equalsIgnoreCase(userId,leader) && !StringUtil.equalsIgnoreCase(org.getParentId(),"-1")){
                // 再往上一级查询
                leader = this.queryDepartLeaderId(org.getParentId(),userId);
            }
        }
        return leader;
    }

    // 获取表单创建人
    public List<String> queryCreator(Map map){
        List<String> ids = new ArrayList();
        // 获取工作流提交申请人的信息
        String userId = (String) map.get("creator");

        ids.add(userId);
        return ids;
    }

    //ChangePassword的手工代码
    @Override
    public void changePassword(String password) {
        String userId = WebUtils.getLoginUser().getId();
        User user = this.findOne(userId);
        user.setPassword(cn.flydiy.base.util.SystemUtil.encryptPassword(password));
        user.setPwdModiTime(new Date());
        user.setStatus(LoginStatus.VALID);
        this._updateEntityById(user);
    }

    @Override
    public void resetPsw(String[] ids) {
        List<Map> userList = this.queryUserListByIds(ids);
        for(Map map:userList){
            map.put("password",cn.flydiy.base.util.SystemUtil.encryptPassword(LoginStatus.INITIAL));
        }
        List<User> updateList = BeanUtil.convertMapsToBeans(userList,User.class);
        this._updateEntityById(updateList.toArray(new User[]{}));
    }

    @Override
    public List<Map> queryAllUserByOrgId(String orgId) {
        List<User> allUserList = userRepository.queryAllUser();
        List<Org> allOrgList = BeanUtil.convertMapsToBeans(orgService.queryAllOrgIgnoreStatus(),Org.class);

        // 获取orgId所包含的所有的下级组织
        List<String> orgIdList = new ArrayList();
        orgIdList.add(orgId);
        List<Org> sonOrgList = this.getNextNodeOrgList(orgId,allOrgList);
        for(Org org : sonOrgList){
            orgIdList.add(org.getId());
        }

        // 获取该组织集合中的所有人员
        List<User> resultList = new ArrayList();
        for(User user : allUserList){
            String userOrgId = user.getOrgId();
            if(orgIdList.indexOf(userOrgId)>=0){
                resultList.add(user);
            }
        }
        return BeanUtil.convertBeansToMaps(resultList);
    }
}
