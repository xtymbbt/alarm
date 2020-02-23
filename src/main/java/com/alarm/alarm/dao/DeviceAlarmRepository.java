package com.alarm.alarm.dao;

import com.alarm.alarm.entity.DeviceAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceAlarmRepository extends JpaRepository<DeviceAlarm,Integer> {
    List<DeviceAlarm> findByDeviceID(Integer deviceID);
    List<DeviceAlarm> findByAlarmCondition(int alarmCondition);
    List<DeviceAlarm> findByTroubleType(int troubleType);
    List<DeviceAlarm> findByAlarmLevel(int alarmLevel);
    List<DeviceAlarm> findByStaffID(Integer staffID);
    List<DeviceAlarm> findByManagerID(Integer managerID);
    List<DeviceAlarm> findByOrderNum(Integer orderNum);
}
