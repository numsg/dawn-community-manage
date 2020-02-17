package com.gsafety.dawn.community.manage.contract.model.changde;

/**
 * 常德使用
 */
public class TroubleshootRecordModel {
    private String id;
    private String name;  //姓名
    private String idNumber;// 身份证号
    private String sex; //性别（0:男，1:女）
    private String age; //年龄
    private String residence; //家庭住址
    private String community; //小区（详细地址）
    private String building; //楼栋（详细地址）
    private String unit; //单元（详细地址）
    private String roomNumber;//房号（详细地址）
    private String phone;  // 联系电话
    private String fever; //体温记录
    /**
     * 其他症状（0:无，1:乏力，2:干咳，3:肌痛，4:寒战，
     * 5:呼吸困难，6:咽痛，7:头疼，8:眩晕，9:腹痛，
     * 10:腹泻，11:恶心，12:呕吐，13:鼻塞）
     */
    private String symptom;
    private String travelLivingHubei; //是否有湖北旅居史（1是0否）
    /**
     * 行程 1. 【 点选 】武汉以外的湖北人入常德人员 2.【 点选 】武汉入常德
     * 3. 【 点选 】常德入武汉以外的湖北辖区后返回常德
     * 4. 【 点选 】常德入武汉后返回常德
     * 5. 【 点选 】既非常德人又非湖北人，途径湖北进入常德
     * 6. 【 点选 】既非常德人又非武汉人，途径武汉进入常德
     */
    private String trip;
    private String touchPersonIsolation; //是否有新型肺炎接触史（0:否，1:是）
    private String touchHubei; //是否与湖北暴露史人员接触【 1是0否 】
    private String temperature;  //体温
    private String discomfort; //有无咳嗽、胸闷等不适症状（1是0否）
    private String wuhanAddress;//常德人入武汉后居住地
    private String leaveHubeiDate;//离开湖北日期(yyyymmdd)
    private String vehicle; //交通工具飞机、火车、大巴、自驾车
    private String vehicleNo; //车次/航班
    private String stayPlace;//沿途停留地点
    private String backDate; //返回常德日期
    private String fourteenDays;  //满14天日期(1是0否)
    private String otherToWuling; //是否外地返武陵区人员(1是0否)
    private String whereToWuling; //回程地点【填空】
    private String howToWuling; //火车/飞机/汽车/自驾
    private String vehicleNoWuling; //航班/车次
    private String togetherPersonWuling; //同程人员
    private String workUnitWuling;//工作单位
    private String permanentWuling;//是否本街道常驻人口【1是0否】
    private String proveWuling;  //是否有相关证明(1是0否)
    private String remark; //备注
    private String createTime;
    private String  reporterName;
    private String  reporterPhone;

    public TroubleshootRecordModel() {
    }

    public TroubleshootRecordModel(String id, String name, String idNumber, String sex, String age, String residence, String community, String building, String unit, String roomNumber, String phone, String fever, String symptom, String travelLivingHubei, String trip, String touchPersonIsolation, String touchHubei, String temperature, String discomfort, String wuhanAddress, String leaveHubeiDate, String vehicle, String vehicleNo, String stayPlace, String backDate, String fourteenDays, String otherToWuling, String whereToWuling, String howToWuling, String vehicleNoWuling, String togetherPersonWuling, String workUnitWuling, String permanentWuling, String proveWuling, String remark, String createTime, String reporterName, String reporterPhone) {
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
        this.sex = sex;
        this.age = age;
        this.residence = residence;
        this.community = community;
        this.building = building;
        this.unit = unit;
        this.roomNumber = roomNumber;
        this.phone = phone;
        this.fever = fever;
        this.symptom = symptom;
        this.travelLivingHubei = travelLivingHubei;
        this.trip = trip;
        this.touchPersonIsolation = touchPersonIsolation;
        this.touchHubei = touchHubei;
        this.temperature = temperature;
        this.discomfort = discomfort;
        this.wuhanAddress = wuhanAddress;
        this.leaveHubeiDate = leaveHubeiDate;
        this.vehicle = vehicle;
        this.vehicleNo = vehicleNo;
        this.stayPlace = stayPlace;
        this.backDate = backDate;
        this.fourteenDays = fourteenDays;
        this.otherToWuling = otherToWuling;
        this.whereToWuling = whereToWuling;
        this.howToWuling = howToWuling;
        this.vehicleNoWuling = vehicleNoWuling;
        this.togetherPersonWuling = togetherPersonWuling;
        this.workUnitWuling = workUnitWuling;
        this.permanentWuling = permanentWuling;
        this.proveWuling = proveWuling;
        this.remark = remark;
        this.createTime = createTime;
        this.reporterName = reporterName;
        this.reporterPhone = reporterPhone;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterPhone() {
        return reporterPhone;
    }

    public void setReporterPhone(String reporterPhone) {
        this.reporterPhone = reporterPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getTravelLivingHubei() {
        return travelLivingHubei;
    }

    public void setTravelLivingHubei(String travelLivingHubei) {
        this.travelLivingHubei = travelLivingHubei;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getTouchPersonIsolation() {
        return touchPersonIsolation;
    }

    public void setTouchPersonIsolation(String touchPersonIsolation) {
        this.touchPersonIsolation = touchPersonIsolation;
    }

    public String getTouchHubei() {
        return touchHubei;
    }

    public void setTouchHubei(String touchHubei) {
        this.touchHubei = touchHubei;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDiscomfort() {
        return discomfort;
    }

    public void setDiscomfort(String discomfort) {
        this.discomfort = discomfort;
    }

    public String getWuhanAddress() {
        return wuhanAddress;
    }

    public void setWuhanAddress(String wuhanAddress) {
        this.wuhanAddress = wuhanAddress;
    }

    public String getLeaveHubeiDate() {
        return leaveHubeiDate;
    }

    public void setLeaveHubeiDate(String leaveHubeiDate) {
        this.leaveHubeiDate = leaveHubeiDate;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getStayPlace() {
        return stayPlace;
    }

    public void setStayPlace(String stayPlace) {
        this.stayPlace = stayPlace;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getFourteenDays() {
        return fourteenDays;
    }

    public void setFourteenDays(String fourteenDays) {
        this.fourteenDays = fourteenDays;
    }

    public String getOtherToWuling() {
        return otherToWuling;
    }

    public void setOtherToWuling(String otherToWuling) {
        this.otherToWuling = otherToWuling;
    }

    public String getWhereToWuling() {
        return whereToWuling;
    }

    public void setWhereToWuling(String whereToWuling) {
        this.whereToWuling = whereToWuling;
    }

    public String getHowToWuling() {
        return howToWuling;
    }

    public void setHowToWuling(String howToWuling) {
        this.howToWuling = howToWuling;
    }

    public String getVehicleNoWuling() {
        return vehicleNoWuling;
    }

    public void setVehicleNoWuling(String vehicleNoWuling) {
        this.vehicleNoWuling = vehicleNoWuling;
    }

    public String getTogetherPersonWuling() {
        return togetherPersonWuling;
    }

    public void setTogetherPersonWuling(String togetherPersonWuling) {
        this.togetherPersonWuling = togetherPersonWuling;
    }

    public String getWorkUnitWuling() {
        return workUnitWuling;
    }

    public void setWorkUnitWuling(String workUnitWuling) {
        this.workUnitWuling = workUnitWuling;
    }

    public String getPermanentWuling() {
        return permanentWuling;
    }

    public void setPermanentWuling(String permanentWuling) {
        this.permanentWuling = permanentWuling;
    }

    public String getProveWuling() {
        return proveWuling;
    }

    public void setProveWuling(String proveWuling) {
        this.proveWuling = proveWuling;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
