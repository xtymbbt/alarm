package com.alarm.alarm.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "devicealarm")
public class DeviceAlarmProperties {
}
