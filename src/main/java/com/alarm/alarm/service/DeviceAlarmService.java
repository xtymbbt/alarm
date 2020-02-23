package com.alarm.alarm.service;

import com.alarm.alarm.dao.DeviceAlarmRepository;
import com.alarm.alarm.entity.DeviceAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeviceAlarmService {
    @Autowired
    private DeviceAlarmRepository deviceAlarmRepository;
    @Transactional
    public void insertTwo(){
        DeviceAlarm deviceAlarm = new DeviceAlarm();
        deviceAlarm.setAlarmCondition(1);
        deviceAlarmRepository.save(deviceAlarm);

    }
}
