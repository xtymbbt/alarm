package com.alarm.alarm.controller;

import com.alarm.alarm.dao.DeviceAlarmRepository;
import com.alarm.alarm.entity.DeviceAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ContentController {
    @Autowired
    DeviceAlarmRepository deviceAlarmRepository;
    @GetMapping(value = "/deviceAlarmSubmit")
    private String deviceAlarm(){
        return "deviceAlarmSubmit";
    }
    @GetMapping(value = "/deviceAlarmContentChoose")
    private String deviceAlarmContentChoose(){
        return "deviceAlarmContentChoose";
    }
    @GetMapping(value = "/deviceAlarmManagerHandle")
    private String deviceAlarmManagerHandle(){
        return "managerHandlePage";
    }
    @GetMapping(value = "/deviceAlarmContent")
    private String deviceAlarmContent(Model model) {
        List<DeviceAlarm> deviceAlarmList = deviceAlarmRepository.findAll();
        model.addAttribute("deviceAlarmList",deviceAlarmList);
        return "deviceAlarmDisplay";
    }
}
