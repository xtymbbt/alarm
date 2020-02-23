package com.alarm.alarm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class DeviceAlarm {
    @Id
    @GeneratedValue
    private Integer alarmID;
    private Integer deviceID;
    private String deviceName;
    private String deviceAddress;
    private BigDecimal deviceLatitude;
    private BigDecimal deviceLongitude;
    private int alarmCondition;
    private Date staffAlarmDate;
    private Integer staffID;
    private int alarmLevel;
    private int troubleType;
    private Integer alarmThreshold;
    private Integer alarmValue;
    private String description;
    private Date managerHandleDate;
    private Integer managerID;
    private String managerHandleDescription;
    private Integer orderNum;
    private String alarmFileName;
    private String imageUrl;
    private String videoUrl;
    private String audioUrl;

    /**
     * 发起此次维修请求的用户ID
     */
    @Column(name = "user_id")
    private Long user_id;

    /**
     * 任务名称
     */
    @Column(name = "title")
    private String title;

    /**
     * 用户负责人（领导）ID
     */
    @Column(name = "principal_id")
    private Long principalId;

    /**
     * 任务对应的项目ID
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 服务商ID
     */
    @Column(name = "facilitator_id")
    private Long facilitatorId;

    /**
     * 维修工ID
     */
    @Column(name = "maintainer_id")
    private Long maintainerId;

    /**
     * 预计完成时间
     */
    @Column(name = "scheduled_finish_time")
    private Date scheduledFinishTime;

    /**
     * 实际完成时间
     */
    @Column(name = "actual_finish_time")
    private Date actualFinishTime;

    /**
     * 预计开始时间
     */
    @Column(name = "scheduled_start_time")
    private Date scheduledStartTime;

    /**
     * 实际开始时间
     */
    @Column(name = "actual_start_time")
    private Date actualStartTime;

    /**
     * 最迟完成时间
     */
    private Date deadline;

    /**
     * 请求维修的地点，纬度
     */
    @Column(name = "request_latitude")
    private BigDecimal requestLatitude;

    /**
     * 请求维修的地点，经度
     */
    @Column(name = "request_longitude")
    private BigDecimal requestLongitude;

    /**
     * 当前任务的进度状态
     */
    private Integer status;

    /**
     * 维修总花费
     */
    @Column(name = "total_cost")
    private BigDecimal totalCost;

    /**
     * 结算方式
     */
    @Column(name = "clearing_form")
    private Integer clearingForm;

    public DeviceAlarm() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public BigDecimal getDeviceLatitude() {
        return deviceLatitude;
    }

    public void setDeviceLatitude(BigDecimal deviceLatitude) {
        this.deviceLatitude = deviceLatitude;
    }

    public BigDecimal getDeviceLongitude() {
        return deviceLongitude;
    }

    public void setDeviceLongitude(BigDecimal deviceLongitude) {
        this.deviceLongitude = deviceLongitude;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(Long facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public Long getMaintainerId() {
        return maintainerId;
    }

    public void setMaintainerId(Long maintainerId) {
        this.maintainerId = maintainerId;
    }

    public Date getScheduledFinishTime() {
        return scheduledFinishTime;
    }

    public void setScheduledFinishTime(Date scheduledFinishTime) {
        this.scheduledFinishTime = scheduledFinishTime;
    }

    public Date getActualFinishTime() {
        return actualFinishTime;
    }

    public void setActualFinishTime(Date actualFinishTime) {
        this.actualFinishTime = actualFinishTime;
    }

    public Date getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(Date scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getRequestLatitude() {
        return requestLatitude;
    }

    public void setRequestLatitude(BigDecimal requestLatitude) {
        this.requestLatitude = requestLatitude;
    }

    public BigDecimal getRequestLongitude() {
        return requestLongitude;
    }

    public void setRequestLongitude(BigDecimal requestLongitude) {
        this.requestLongitude = requestLongitude;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getClearingForm() {
        return clearingForm;
    }

    public void setClearingForm(Integer clearingForm) {
        this.clearingForm = clearingForm;
    }

    public String getAlarmFileName() {
        return alarmFileName;
    }

    public void setAlarmFileName(String alarmFileName) {
        this.alarmFileName = alarmFileName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public Integer getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(Integer alarmID) {
        this.alarmID = alarmID;
    }

    public Integer getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Integer deviceID) {
        this.deviceID = deviceID;
    }

    public int getAlarmCondition() {
        return alarmCondition;
    }

    public void setAlarmCondition(int alarmCondition) {
        this.alarmCondition = alarmCondition;
    }

    public Date getStaffAlarmDate() {
        return staffAlarmDate;
    }

    public void setStaffAlarmDate(Date staffAlarmDate) {
        this.staffAlarmDate = staffAlarmDate;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public int getTroubleType() {
        return troubleType;
    }

    public void setTroubleType(int troubleType) {
        this.troubleType = troubleType;
    }

    public Integer getAlarmThreshold() {
        return alarmThreshold;
    }

    public void setAlarmThreshold(Integer alarmThreshold) {
        this.alarmThreshold = alarmThreshold;
    }

    public Integer getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(Integer alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getManagerHandleDate() {
        return managerHandleDate;
    }

    public void setManagerHandleDate(Date managerHandleDate) {
        this.managerHandleDate = managerHandleDate;
    }

    public Integer getManagerID() {
        return managerID;
    }

    public void setManagerID(Integer managerID) {
        this.managerID = managerID;
    }

    public String getManagerHandleDescription() {
        return managerHandleDescription;
    }

    public void setManagerHandleDescription(String managerHandleDescription) {
        this.managerHandleDescription = managerHandleDescription;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
