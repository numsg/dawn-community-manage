package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.DateUtil;
import com.gsafety.dawn.community.manage.contract.model.RequestModel;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.DailyTroubleshootRecordEntity;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.DailyTroubleshootRecordRepository;
import com.gsafety.java.common.utils.HttpClientUtil;
import com.gsafety.java.common.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.gsafety.java.common.utils.JsonUtil.toJson;

@Service
@Transactional
public class DataCollectionServiceImpl {
    @Value("${access.data-collection}")
    private String dataCollectionUrl;

    // 男
    private String maleId = "3f265ff3-75b8-49f1-9669-4506535a500c";
    //女
    private String femaleId = "2ae58f9e-65f2-4f2a-8244-ac01d668b7b5";

    // 其他症状
    //0:无
    private String noId = "26d8ca3b-8b7b-4109-a992-8052f9defb9d";
    //1:乏力
    private String feebleId = "5e647e8b-6396-4e56-854b-ed1be1c60ae3";
    //2:干咳
    private String hooseId = "e081de69-a984-4abb-a2a1-ed9996a63917";
    //3:肌痛
    private String musclePainId = "0e2adfab-bb9d-4ef9-a4db-6a5baa2d7788";
    //4:寒战
    private String aguedId = "be64af80-00ee-4ea5-94ba-3246d7d16230";
    //5:呼吸困难
    private String breathId = "b01b1538-31f3-428b-9e33-531af1f40f83";
    //6:咽痛
    private String pharyngalgiaId = "3dbbfced-ca40-437b-91d5-70f0c12b32ea";
    //7:头疼
    private String headacheId = "ee377b8b-220f-4e8b-a6ce-fe47082e227b";
    //8:眩晕
    private String dizzyId = "ce0902a6-e3ee-4055-b1c3-d41a365a5522";
    //9:腹痛
    private String bellyacheId = "e1ed1513-cfa1-4343-aa50-a4ab76c09c46";
    //10:腹泻
    private String diarrhoeaId = "5f98d8f5-40e9-427d-90c0-e1849a87ae19";
    //11:恶心
    private String nauseaId = "1720a8db-a0b4-43d2-8f52-3a4df8e3fca5";
    //12:呕吐
    private String vomitId = "2fdd7934-7823-4227-8712-7488ebc7704e";
    //13:鼻塞
    private String nasalObstructionId = "f0671f8a-233f-44a5-a785-9e9ab0c18fe8";

    //分类诊疗医疗意见：
    // 0:确诊患者，
    private String confirmedPatientId = "c9eedfbc-ae5a-40b7-8a62-c049c5678deb";
    //1:疑似患者，6293737c-5775-426d-9845-f919eafba1be
    private String suspectedPatientId = "6293737c-5775-426d-9845-f919eafba1be";
    //3:一版发热患者，c0bb07b2-db54-4fd1-89d3-20b0672a2779
    private String feverPatientId = "c0bb07b2-db54-4fd1-89d3-20b0672a2779";

    //2:CT诊断肺炎患者 c9eedfbc-ae5a-40b7-8a62-c049c5678deb
    private String CTPatientId = "c9eedfbc-ae5a-40b7-8a62-c049c5678deb";
    //4:密切接触者  6293737c-5775-426d-9845-f919eafba1be
    private String contractPatientId = "6293737c-5775-426d-9845-f919eafba1be";


    private String url = "/search/v2";

    // 杨桥湖社区id
    private static final String communityId = "a2e01f0e-6c86-4a41-bcf3-c07c1ffa2f82";
    // 其他状况id
    private static final String otherSymptomId = "582daff0-56a5-45a4-9ca7-dc098c688753";
    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    DailyTroubleshootRecordRepository recordRepository;

    @Autowired
    private DSourceDataRepository dSourceDataRepository;

    private Date startTimeDate = DateUtil.getDayStartDate();
    // 小区
    private List<DSourceDataEntity> plots = new ArrayList<>();
    // 其他诊断状况
    private List<DSourceDataEntity> otherSymptoms = new ArrayList<>();

    /**
     * 定时从外部服务查询数据。
     */
    public void timeQuery() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endTimeDate = DateUtil.getDayEndDate();

        System.out.println(formatter.format(startTimeDate));
        System.out.println(formatter.format(endTimeDate));

        RequestModel requestModel = new RequestModel();
        requestModel.setPageSize(20);
        requestModel.setKeyWords("");
        requestModel.setStartDate(startTimeDate);
        requestModel.setEndDate(DateUtil.getDayEndDate());

        Map map = httpClientUtil.httpPost(dataCollectionUrl + url, requestModel, Map.class);
        if (map.get("data") != null && map.get("success").equals(true)) {
            Map data = JsonUtil.fromJson(toJson(map.get("data")), Map.class);
            Integer total = Integer.parseInt(JsonUtil.fromJson(toJson(data.get("total")), String.class));
            int pageTotal = total / requestModel.getPageSize();

            plots = dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(communityId);
            otherSymptoms = dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(otherSymptomId);

            for (int i = 1; i <= pageTotal; i++) {
                requestModel.setPageNo(i);
                getDataFromAccess(requestModel);
            }

        }
    }

    public Boolean getDataFromAccess(RequestModel requestModel) {
        Map map = httpClientUtil.httpPost(dataCollectionUrl + url, requestModel, Map.class);
        if (map.get("data") == null || map.get("success").equals(false)) {
            return false;
        }
        // 数据转换
        Map data = JsonUtil.fromJson(toJson(map.get("data")), Map.class);
        List list = JsonUtil.fromJson(toJson(data.get("list")), List.class);
        if (list.isEmpty()) {
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Object obj : list) {
            Map objMap = JsonUtil.fromJson(toJson(obj), Map.class);
            DailyTroubleshootRecordEntity entity = new DailyTroubleshootRecordEntity();
            if (objMap.get("name") == null || objMap.get("phone") == null || objMap.get("residence") == null || objMap.get("sex") == null) {
                break;
            }
            entity.setId(objMap.get("id").toString());
            entity.setName(objMap.get("name").toString());
            entity.setPhone(objMap.get("phone").toString());
            entity.setAddress(objMap.get("residence").toString());

            entity.setAge(objMap.get("age") != null ? Integer.parseInt(objMap.get("age").toString()) : 0);
            entity.setBuilding(objMap.get("building") != null ? objMap.get("building").toString() : "");
            try {
                entity.setCreateTime(formatter.parse(objMap.get("createTime").toString()));  //字符串转换
            } catch (ParseException e) {
                e.printStackTrace();
            }
            entity.setIdentificationNumber(objMap.get("idNumber") != null ? objMap.get("idNumber").toString() : "");
            entity.setRoomNo(objMap.get("roomNumber") != null ? objMap.get("roomNumber").toString() : "");
            entity.setUnitNumber(objMap.get("unit") != null ? objMap.get("unit").toString() : "");
            entity.setNote(objMap.get("remark") != null ? objMap.get("remark").toString() : "");

            entity.setCode(UUID.randomUUID().toString());

            if (objMap.get("sex") != null) {
                entity.setSex(objMap.get("sex").toString().equals("0") ? maleId : femaleId);
            }
            if (objMap.get("fever") != null) {
                entity.setExceedTemp(objMap.get("fever").toString().equals("1"));
            }
            if (objMap.get("touchPersonIsolation") != null) {
                entity.setContact(objMap.get("touchPersonIsolation").toString().equals("1"));
            }
            if (objMap.get("symptom") != null) {
                entity.setOtherSymptoms(splitOtherSymptom(objMap.get("symptom").toString()));
                //entity.setOtherSymptoms(converOtherSymptoms(objMap.get("symptom").toString()));
            }
            if (objMap.get("medicalAdvice") != null) {
                entity.setConfirmed_diagnosis(convertMedicalOpinion(objMap.get("medicalAdvice").toString()));
                entity.setMedicalOpinion(convertMedicalOpinion(objMap.get("medicalAdvice").toString()));
            }
            entity.setMultiTenancy("123456");
            //entity.setMultiTenancy(objMap.get("currentVillage").toString());
            //entity.setLeaveArea();
            //entity.setPlot(objMap.get("communityCode").toString());

            Random random = new Random();
            entity.setPlot(plots.get(random.nextInt(plots.size())).getId());
//            System.out.println("++++++++++=");
//            System.out.println(entity.getId() + entity.getName());
//            System.out.println("++++++++++=");

            startTimeDate = entity.getCreateTime();

            if (!recordRepository.existsById(entity.getId())) {
                recordRepository.save(entity);
            }
        }
        return true;
    }

    private String splitOtherSymptom(String symptoms) {
        StringBuffer symptomIds = new StringBuffer();
        String[] arrays = symptoms.split("-");
        for (String arr : arrays) {
            otherSymptoms.forEach(data -> {
                if (data.getName().equals(arr)) {
                    symptomIds.append(data.getId());
                    symptomIds.append(",");
                }
            });
        }
        if (symptomIds.length() > 0) {
            symptomIds.deleteCharAt(symptomIds.length() - 1);
        }
        return symptomIds.toString();
    }


    private String convertMedicalOpinion(String number) {
        String result = null;
        if (number.equals("0")) {
            result = confirmedPatientId;
        } else if (number.equals("1")) {
            result = suspectedPatientId;
        } else if (number.equals("2")) {
            result = CTPatientId;
        } else if (number.equals("3")) {
            result = feverPatientId;
        } else if (number.equals("4")) {
            result = contractPatientId;
        }

        return result;
    }

    private String converOtherSymptoms(String number) {
        String result = null;
        if (number.equals("0")) {
            result = noId;
        } else if (number.equals("1")) {
            result = feebleId;
        } else if (number.equals("2")) {
            result = hooseId;
        } else if (number.equals("3")) {
            result = musclePainId;
        } else if (number.equals("4")) {
            result = aguedId;
        } else if (number.equals("5")) {
            result = breathId;
        } else if (number.equals("6")) {
            result = pharyngalgiaId;
        } else if (number.equals("7")) {
            result = headacheId;
        } else if (number.equals("8")) {
            result = dizzyId;
        } else if (number.equals("9")) {
            result = bellyacheId;
        } else if (number.equals("10")) {
            result = diarrhoeaId;
        } else if (number.equals("11")) {
            result = nauseaId;
        } else if (number.equals("12")) {
            result = vomitId;
        } else if (number.equals("13")) {
            result = nasalObstructionId;
        }


        return result;
    }
}
