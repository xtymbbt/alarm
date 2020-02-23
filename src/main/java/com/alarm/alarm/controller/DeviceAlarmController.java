package com.alarm.alarm.controller;

import com.alarm.alarm.dao.DeviceAlarmRepository;
import com.alarm.alarm.entity.DeviceAlarm;
import com.alarm.alarm.service.DeviceAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.UUID;

@RestController
public class DeviceAlarmController {
    @Autowired
    DeviceAlarmRepository deviceAlarmRepository;
    @Autowired
    private DeviceAlarmService deviceAlarmService;

    /**
     * 查询所有设备报警列表
     *
     * @return
     */
    @GetMapping(value = "/deviceAlarm")
    private List<DeviceAlarm> deviceAlarmList(){return deviceAlarmRepository.findAll();}

    /**
     * 添加设备所用
     *
     * @param deviceID 设备ID
     * @param deviceName 设备名称
     * @param devicePosition 设备地址
     * @return 保存到数据库
     */
    @PostMapping(value = "/device")
    public DeviceAlarm deviceAdd(@RequestParam("deviceID") Integer deviceID,
                                 @RequestParam("deviceName") String deviceName,
                                 @RequestParam("devicePosition") String devicePosition){
        DeviceAlarm deviceAlarm = new DeviceAlarm();
        deviceAlarm.setDeviceID(deviceID);
        deviceAlarm.setDeviceName(deviceName);
        deviceAlarm.setDeviceAddress(devicePosition);
        return deviceAlarmRepository.save(deviceAlarm);
    }

    /**
            * 文件上传配置
     *
             * @return
             */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.parse("1024MB")); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse("10240MB"));
        return factory.createMultipartConfig();
    }

    /**
     * 基层员工报警，添加设备报警信息
     *
     * @param deviceID 用于对应设备
     * @param staffID 用于记录报警人员
     * @param alarmLevel 警报等级
     * @param alarmType 警报类型
     * @param alarmThreshold 警报阈值
     * @param alarmValue 警报值
     * @param staffAlarmDescription 警报的详细描述
     * @return 记录到数据库中
     */
    @PostMapping(value = "/deviceAlarm")
    public DeviceAlarm deviceAlarmAdd(@RequestParam("deviceID") Integer deviceID,
                                      @RequestParam("staffID") Integer staffID,
                                      @RequestParam("alarmLevel") int alarmLevel,
                                      @RequestParam("alarmType") int alarmType,
                                      @RequestParam("alarmThreshold") Integer alarmThreshold,
                                      @RequestParam("alarmValue") Integer alarmValue,
                                      @RequestParam("staffAlarmDescription") String staffAlarmDescription,
                                      @RequestParam("file") MultipartFile file,
                                      HttpServletRequest req
                                      ){
        List<DeviceAlarm> deviceAlarmList = deviceAlarmFindOneByDeviceID(deviceID);
        DeviceAlarm deviceAlarm = deviceAlarmList.get(0);
        deviceAlarm.setAlarmCondition(1);
        deviceAlarm.setStaffAlarmDate(new Date());
        deviceAlarm.setManagerID(-1);
        deviceAlarm.setOrderNum(-1);
        // 分界线。上面由系统设置初始值，下面由基层员工输入值。
        deviceAlarm.setAlarmLevel(alarmLevel);
        deviceAlarm.setAlarmThreshold(alarmThreshold);
        deviceAlarm.setAlarmValue(alarmValue);
        deviceAlarm.setTroubleType(alarmType);
        deviceAlarm.setStaffID(staffID);
        deviceAlarm.setDescription(staffAlarmDescription);
        // 上传文件
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        File dir = new File(realPath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
        }
        try {
            String fileName = file.getOriginalFilename();
            String pikId = UUID.randomUUID().toString().replaceAll("-", "");
            String newFileName = pikId + "." + fileName;
            //服务端保存的文件对象
            File fileServer = new File(dir, newFileName);
            System.out.println("file文件真实路径:" + fileServer.getAbsolutePath());
            //2，实现上传
            file.transferTo(fileServer);
            String filePath = req.getScheme() + "://" +
                    req.getServerName() + ":"
                    + req.getServerPort()
                    + "/uploadFile/" + newFileName;
            deviceAlarm.setAlarmFileName(newFileName);
            assert fileName != null;
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            switch (suffix) {
                case "jpg":
                case "png":
                case "bmp":
                case "tif":
                case "gif":
                case "pcx":
                case "tga":
                case "exif":
                case "fpx":
                case "svg":
                case "psd":
                case "cdr":
                case "pcd":
                case "dxf":
                case "ufo":
                case "eps":
                case "ai":
                case "raw":
                case "wmf":
                case "webp":
                    deviceAlarm.setImageUrl(filePath);
                    break;
                case "mp4":
                case "mkv":
                case "wmv":
                case "avi":
                case "dat":
                case "asf":
                case "mpeg":
                case "mpg":
                case "rm":
                case "rmvb":
                case "ram":
                case "flv":
                case "3gp":
                case "mov":
                case "divx":
                case "dv":
                case "vob":
                case "qt":
                case "cpk":
                case "fli":
                case "flc":
                case "f4v":
                case "m4v":
                case "mod":
                case "m2t":
                case "swf":
                case "webm":
                case "mts":
                case "m2ts":
                case "3g2":
                case "mpe":
                case "ts":
                case "div":
                case "lavf":
                case "dirac":
                    deviceAlarm.setVideoUrl(filePath);
                    break;
                case "mp3":
                case "wav":
                case "midi":
                case "cda":
                case "wma":
                    deviceAlarm.setAudioUrl(filePath);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deviceAlarmRepository.save(deviceAlarm);
    }

    /**
     * 交由设备总负责人处理详细报警信息，确认报警
     *
     * @param deviceID 用于寻找报警设备
     * @param managerID 用于记录处理人员
     * @param managerHandleDescription 用于描述警报
     * @param alarmConfirmed 用于确认警报
     * @return 更新到数据库中
     */
    @PostMapping(value = "/deviceAlarm/deviceID/managerHandle")
    public List<DeviceAlarm> deviceAlarmUpdate(@RequestParam("deviceID") Integer deviceID,
                                         @RequestParam("managerID") Integer managerID,
                                         @RequestParam("managerHandleDescription") String managerHandleDescription,
                                         @RequestParam("alarmConfirmed") boolean alarmConfirmed
                                         ) throws JSONException {
        List<DeviceAlarm> deviceAlarmList = deviceAlarmFindOneByDeviceID(deviceID);
        for(DeviceAlarm deviceAlarm:deviceAlarmList){
            deviceAlarm.setDeviceID(deviceID);
            deviceAlarm.setManagerID(managerID);
            deviceAlarm.setManagerHandleDate(new Date());
            deviceAlarm.setManagerHandleDescription(managerHandleDescription);
            if (alarmConfirmed){
                deviceAlarm.setAlarmCondition(2);
            }
            else {
                deviceAlarm.setAlarmCondition(0);
            }
            deviceAlarmRepository.save(deviceAlarm);
        }
        /**
         * 自动生成工单信息：
         */

        if (alarmConfirmed){
            DeviceAlarm deviceAlarm = deviceAlarmList.get(0);
            //生成维修维护工单的接口地址,需要加http://
            String url="http://127.0.0.1:8300/ananops/api/v1/task/submit/{taskId}";
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("uId",deviceAlarm.getUser_id());
            jsonObject.put("principalId",deviceAlarm.getManagerID());
            jsonObject.put("projectId",deviceAlarm.getProjectId());
            jsonObject.put("facilitatorId",deviceAlarm.getFacilitatorId());
            jsonObject.put("latitude",deviceAlarm.getRequestLatitude());
            jsonObject.put("longitude",deviceAlarm.getRequestLongitude());
            jsonObject.put("totalCost",deviceAlarm.getTotalCost());
            jsonObject.put("payMode",deviceAlarm.getClearingForm());
            jsonObject.put("taskItems",deviceAlarmList);
            doPost2(url,jsonObject);
        }

        return deviceAlarmList;
    }


    public static String doPost2(String url, JSONObject param) {
        HttpPost httpPost = null;
        String result = null;
        try {
            HttpClient client = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            if (param != null) {
                StringEntity se = new StringEntity(param.toString(), "utf-8");
                httpPost.setEntity(se); // post方法中，加入json数据
                httpPost.setHeader("Content-Type", "application/json");
            }

            HttpResponse response = client.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 查询一个设备报警
     *
     * @param deviceID 设备ID，由设备ID找到该报警
     * @return 根据设备ID返回该设备的报警信息
     */
    @GetMapping(value = "/deviceAlarm/deviceID/{deviceID}")
    public List<DeviceAlarm> deviceAlarmFindOneByDeviceID(@PathVariable("deviceID") Integer deviceID) {
        return deviceAlarmRepository.findByDeviceID(deviceID);
    }

    /**
     * 查询一个设备报警
     *
     * @param troubleType 警报类型
     * @return 根据警报类型返回所有的报警信息
     */
    @GetMapping(value = "/deviceAlarm/deviceID/{troubleType}")
    public List<DeviceAlarm> deviceAlarmFindOneByAlarmType(@PathVariable("troubleType") int troubleType) {
        return deviceAlarmRepository.findByTroubleType(troubleType);
    }

    /**
     * 查询一个设备报警
     *
     * @param alarmLevel 警报等级
     * @return 根据警报等级返回所有报警的信息
     */
    @GetMapping(value = "/deviceAlarm/deviceID/{alarmLevel}")
    public List<DeviceAlarm> deviceAlarmFindOneByAlarmLevel(@PathVariable("alarmLevel") int alarmLevel) {
        return deviceAlarmRepository.findByAlarmLevel(alarmLevel);
    }

    /**
     * 查询一个设备报警
     *
     * @param alarmCondition 警报状态
     * @return 根据警报状态返回相应的所有报警的信息
     */
    @GetMapping(value = "/deviceAlarm/deviceID/{alarmCondition}")
    public List<DeviceAlarm> deviceAlarmFindOneByAlarmCondition(@PathVariable("alarmCondition") int alarmCondition) {
        return deviceAlarmRepository.findByAlarmCondition(alarmCondition);
    }

    /**
     * 查询一个设备报警
     *
     * @param staffID 基层员工ID
     * @return 返回该基层员工上报的所有报警信息
     */
    @GetMapping(value = "/deviceAlarm/deviceID/{staffID}")
    public List<DeviceAlarm> deviceAlarmFindOneByStaffID(@PathVariable("staffID") Integer staffID) {
        return deviceAlarmRepository.findByStaffID(staffID);
    }

    /**
     * 查询一个设备报警
     *
     * @param managerID 负责人ID
     * @return 返回该负责人所负责的所有报警信息
     */
    @GetMapping(value = "/deviceAlarm/deviceID/{managerID}")
    public List<DeviceAlarm> deviceAlarmFindOneByManagerID(@PathVariable("managerID") Integer managerID) {
        return deviceAlarmRepository.findByManagerID(managerID);
    }

    /**
     * 查询一个设备报警
     *
     * @param orderNum 工单号。
     * @return 根据工单号返回相应设备的报警信息
     */
    @GetMapping(value = "/deviceAlarm/deviceID/{orderNum}")
    public List<DeviceAlarm> deviceAlarmFindOneByOrderNum(@PathVariable("orderNum") Integer orderNum) {
        return deviceAlarmRepository.findByOrderNum(orderNum);
    }

    /**
     * 查询一个设备报警
     *
     * @param alarmID 报警ID，由报警ID找到该报警
     * @return 根据报警ID返回相应报警的信息
     */
    @GetMapping(value = "/deviceAlarm/alarmID/{alarmID}")
    public DeviceAlarm deviceAlarmFindOneByAlarmID(@PathVariable("alarmID") Integer alarmID) {
        return deviceAlarmRepository.findById(alarmID).get();
    }

    /**
     * 删除一个设备报警
     *
     * @param alarmID 由报警ID查找，删除该报警
     */
    @DeleteMapping(value = "/deviceAlarm/alarmID/{alarmID}")
    public void deviceAlarmDeleteByAlarmID(@PathVariable("alarmID") Integer alarmID) {
        deviceAlarmRepository.deleteById(alarmID);
    }


}
