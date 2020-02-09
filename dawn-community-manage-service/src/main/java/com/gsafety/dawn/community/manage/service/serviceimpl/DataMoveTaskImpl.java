//package com.gsafety.dawn.community.manage.service.serviceimpl;
//
//import com.gsafety.dawn.community.common.util.DateUtil;
//import com.gsafety.dawn.community.manage.contract.model.RequestModel;
//import com.gsafety.dawn.community.manage.service.entity.DailyTroubleshootRecordEntity;
//import com.gsafety.dawn.community.manage.service.repository.DailyTroubleshootRecordRepository;
//import com.gsafety.dawn.community.manage.service.repository.ResourceRepository;
//import com.gsafety.java.common.utils.HttpClientUtil;
//import com.gsafety.java.common.utils.HttpClientUtilImpl;
//import com.gsafety.java.common.utils.JsonUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//import java.text.ParsePosition;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//import static com.gsafety.java.common.utils.JsonUtil.toJson;
//
//
///**
// * description:
// *
// * @outhor liujian
// * @create 2020-02-09 4:00
// */
//@Component
//public class DataMoveTaskImpl implements ServletContextListener {
//
//    private Timer timer = null;
//
//    @Autowired
//    DailyTroubleshootRecordRepository recordRepository;
//
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        timer = new Timer(true);
//        sce.getServletContext().log("定时器已启动");
//        timer.schedule(new OneTask(), 60000, 3600000);//延迟60秒，定时1小时
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//
//    }
//
//    class OneTask extends TimerTask {//继承TimerTask类
//
//        @Override
//        public void run() {
//            System.out.println("1小时一次数据迁移");
//
//            String url = "http://39.105.209.108:8090/api/search/v2";
//
//            RequestModel requestModel = new RequestModel();
//            requestModel.setPageNo(0);
//            requestModel.setPageSize(10);
//
//            Date startTimeDate = DateUtil.getStartTimeDate();
//
//            Date endTimeDate = DateUtil.getEndTimeDate();
//
//
//            requestModel.setStartDate(startTimeDate);
//            requestModel.setEndDate(endTimeDate);
//            requestModel.setKeyWords("");
//
//            HttpClientUtil httpClientUtil = new HttpClientUtilImpl();
//
////            Object list = httpClientUtil.httpPost(url, requestModel, Object.class);
//
//            // 转换
//
//            Map map = httpClientUtil.httpPost(url, requestModel, Map.class);
//            Map data = JsonUtil.fromJson(toJson(map.get("data")), Map.class);
//            List list = JsonUtil.fromJson(toJson(data.get("list")), List.class);
//
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            ParsePosition pos = new ParsePosition(8);
//
//            for (Object obj : list) {
//                Map objMap = JsonUtil.fromJson(toJson(obj), Map.class);
//                DailyTroubleshootRecordEntity entity = new DailyTroubleshootRecordEntity();
//                entity.setId(objMap.get("id").toString());
//                entity.setName(objMap.get("name").toString());
//                entity.setPhone(objMap.get("phone").toString());
//                entity.setSex(objMap.get("sex").toString());
//                entity.setSex(objMap.get("sex").toString());
//                entity.setAddress(objMap.get("currentCity").toString());
//                entity.setCreateTime(formatter.parse(objMap.get("createTime").toString(), pos));  //字符串转换
//                entity.setMedicalOpinion(objMap.get("medicalAdvice").toString());
//
//                entity.setCode(UUID.randomUUID().toString());
//
//                System.out.println("++++++++++=");
//                System.out.println(entity.getId() + entity.getName());
//                System.out.println("++++++++++=");
//
//                recordRepository.save(entity);
////                if(!recordRepository.existsById(entity.getId())){
////                    recordRepository.save(entity);
////                }
//
//            }
//        }
//
//
////    public static void main(String args){
////
////    }
//
//
//    }
//
//}
