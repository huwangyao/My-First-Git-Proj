package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;

public class StaffDto extends BaseDto {

    private String StaffID;

    private String ChnName;

    private String EnglishName;

    private String Name;

    private String BirthDate;

    private String BusnEmail;

    private String WeChat;

    private String LastHireDate;

    private String BGOrganizationID;

    private String BGOrganizationName;

    private String DepOrganizationID;

    private String DepOrganizationName;

    private String StaffTypeCode;

    private String PostID;

    private String Native;

    private String EducationID;

    private String EducationName;

    private String HRStatus;

    private String PhotoUrl;

    private String PostName;

    private String GenderName;

    private String ContractCompanyName;

    private String RtxName;

    private String StaffTypeName;

    private String HighSchool;

    private String Major;

    private String level;

    private String LocationName;

    private String PositionName;

    private String OrganizationFullName;

        private String ManagerPositionID;

    private String ManagerPositionName;

    public String getManagerPositionID() {
        return ManagerPositionID;
    }

    public void setManagerPositionID(String ManagerPositionID) {
        this.ManagerPositionID = ManagerPositionID;
    }

    public String getManagerPositionName() {
        return ManagerPositionName;
    }

    public void setManagerPositionName(String ManagerPositionName) {
        this.ManagerPositionName = ManagerPositionName;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String LocationName) {
        this.LocationName = LocationName;
    }

public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String PositionName) {
        this.PositionName = PositionName;
    }


public String getOrganizationFullName() {
        return OrganizationFullName;
    }

    public void setOrganizationFullName(String OrganizationFullName) {
        this.OrganizationFullName = OrganizationFullName;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String staffID) {
        StaffID = staffID;
    }

    public String getChnName() {
        return ChnName;
    }

    public void setChnName(String chnName) {
        ChnName = chnName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getBusnEmail() {
        return BusnEmail;
    }

    public void setBusnEmail(String busnEmail) {
        BusnEmail = busnEmail;
    }

    public String getWeChat() {
        return WeChat;
    }

    public void setWeChat(String weChat) {
        WeChat = weChat;
    }

    public String getLastHireDate() {
        return LastHireDate;
    }

    public void setLastHireDate(String lastHireDate) {
        LastHireDate = lastHireDate;
    }

    public String getBGOrganizationID() {
        return BGOrganizationID;
    }

    public void setBGOrganizationID(String BGOrganizationID) {
        this.BGOrganizationID = BGOrganizationID;
    }

    public String getBGOrganizationName() {
        return BGOrganizationName;
    }

    public void setBGOrganizationName(String BGOrganizationName) {
        this.BGOrganizationName = BGOrganizationName;
    }

    public String getDepOrganizationID() {
        return DepOrganizationID;
    }

    public void setDepOrganizationID(String depOrganizationID) {
        DepOrganizationID = depOrganizationID;
    }

    public String getDepOrganizationName() {
        return DepOrganizationName;
    }

    public void setDepOrganizationName(String depOrganizationName) {
        DepOrganizationName = depOrganizationName;
    }

    public String getStaffTypeCode() {
        return StaffTypeCode;
    }

    public void setStaffTypeCode(String staffTypeCode) {
        StaffTypeCode = staffTypeCode;
    }

    public String getPostID() {
        return PostID;
    }

    public void setPostID(String postID) {
        PostID = postID;
    }

    public String getNative() {
        return Native;
    }

    public void setNative(String aNative) {
        Native = aNative;
    }

    public String getEducationID() {
        return EducationID;
    }

    public void setEducationID(String educationID) {
        EducationID = educationID;
    }

    public String getEducationName() {
        return EducationName;
    }

    public void setEducationName(String educationName) {
        EducationName = educationName;
    }

    public String getHRStatus() {
        return HRStatus;
    }

    public void setHRStatus(String HRStatus) {
        this.HRStatus = HRStatus;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }

    public String getPostName() {
        return PostName;
    }

    public void setPostName(String postName) {
        PostName = postName;
    }

    public String getGenderName() {
        return GenderName;
    }

    public void setGenderName(String genderName) {
        GenderName = genderName;
    }

    public String getContractCompanyName() {
        return ContractCompanyName;
    }

    public void setContractCompanyName(String contractCompanyName) {
        ContractCompanyName = contractCompanyName;
    }

    public String getRtxName() {
        return RtxName;
    }

    public void setRtxName(String rtxName) {
        RtxName = rtxName;
    }

    public String getStaffTypeName() {
        return StaffTypeName;
    }

    public void setStaffTypeName(String staffTypeName) {
        StaffTypeName = staffTypeName;
    }

    public String getHighSchool() {
        return HighSchool;
    }

    public void setHighSchool(String highSchool) {
        HighSchool = highSchool;
    }

    @Override
    public String toString() {
        return "StaffDto{" +
                "Name='" + Name + '\'' +
                ", DepOrganizationName='" + DepOrganizationName + '\'' +
                ", EducationName='" + EducationName + '\'' +
                ", PostName='" + PostName + '\'' +
                ", HighSchool='" + HighSchool + '\'' +
                '}';
    }
}
