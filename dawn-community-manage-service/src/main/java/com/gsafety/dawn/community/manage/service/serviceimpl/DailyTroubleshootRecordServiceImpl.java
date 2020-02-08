package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.DateUtil;
import com.gsafety.dawn.community.common.util.ExcelUtil;
import com.gsafety.dawn.community.common.util.FileUtil;
import com.gsafety.dawn.community.manage.contract.model.DailyTroubleshootRecordModel;
import com.gsafety.dawn.community.manage.contract.service.DailyTroubleshootRecordService;
import com.gsafety.dawn.community.manage.service.datamappers.DailyTroubleshootRecordMapper;
import com.gsafety.dawn.community.manage.service.entity.DailyTroubleshootRecordEntity;
import com.gsafety.dawn.community.manage.service.repository.DailyTroubleshootRecordRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-08 12:53
 */
@Service
@Transactional
public class DailyTroubleshootRecordServiceImpl implements DailyTroubleshootRecordService {

    @Value("${app.multiTenancy}")
    private String multiTenancy;

    @Autowired
    DailyTroubleshootRecordMapper recordMapper;

    @Autowired
    DailyTroubleshootRecordRepository recordRepository;

    // 日志
    private static Logger logger = LoggerFactory.getLogger(DailyTroubleshootRecordServiceImpl.class);

    // 时间
    private static final String TS = DateUtil.convertNowDate().toString();

    @Override
    public DailyTroubleshootRecordModel addDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel) {
        DailyTroubleshootRecordEntity recordEntity = recordMapper.modelToEntity(dailyTroubleshootRecordModel);
        recordEntity.setId(UUID.randomUUID().toString());
        recordEntity.setCode(UUID.randomUUID().toString());
        recordEntity.setCreateTime(DateUtil.convertNowDate().toString());
        recordEntity.setMultiTenancy(multiTenancy);
        return recordMapper.entityToModel(recordRepository.save(recordEntity));
    }

    @Override
    public DailyTroubleshootRecordModel updateDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel) {
        boolean exists = recordRepository.existsById(dailyTroubleshootRecordModel.getId());
        if(exists){
            DailyTroubleshootRecordEntity recordEntity = recordRepository.saveAndFlush(recordMapper.modelToEntity(dailyTroubleshootRecordModel));
            return  recordMapper.entityToModel(recordEntity);
        }
        return null;
    }

    @Override
    public List<DailyTroubleshootRecordModel> importTroubleshootRecord(MultipartFile multipartFile) {
        List<DailyTroubleshootRecordEntity> recordEntities = new ArrayList<>();
        try {
            String fileName = multipartFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
            List<Sheet> sheets = ExcelUtil.getExcelSheet(multipartFile.getInputStream(), fileType);
            for (int i = 0 ; i < sheets.size() ; i++ ){
                Sheet sheet = sheets.get(i);
                List<DailyTroubleshootRecordEntity> recordEntityList = parseSheetRecord(sheet);
                if(!recordEntityList.isEmpty())
                    recordEntities.addAll(recordEntityList);
            }
        } catch (IOException e) {
            logger.error(e.getMessage() , e);
        }
        return recordMapper.entitiesToModels(recordRepository.saveAll(recordEntities));
    }

    // 解析每个tab中的内容
    public List<DailyTroubleshootRecordEntity> parseSheetRecord(Sheet sheet){
        if (sheet == null) {
            logger.error("tab页内容为空");
            return Collections.emptyList();
        }
        List<DailyTroubleshootRecordEntity> recordEntities = new ArrayList<>();
        int endRow = sheet.getPhysicalNumberOfRows();
        int firstSheetNumber = sheet.getFirstRowNum();
        for (int rowNum = firstSheetNumber + 1; rowNum < endRow; rowNum++) {
            Row row = sheet.getRow(rowNum);

            String name = ExcelUtil.convertCellValueToString(row.getCell(0));
            String idCard = ExcelUtil.convertCellValueToString(row.getCell(1));
            String sex = ExcelUtil.convertCellValueToString(row.getCell(2));
            String phone = ExcelUtil.convertCellValueToString(row.getCell(3));
            String address = ExcelUtil.convertCellValueToString(row.getCell(4));
            String plot = ExcelUtil.convertCellValueToString(row.getCell(5));
            String build = ExcelUtil.convertCellValueToString(row.getCell(6));
            String unit = ExcelUtil.convertCellValueToString(row.getCell(7));
            String roomNo = ExcelUtil.convertCellValueToString(row.getCell(8));
            String tempture = ExcelUtil.convertCellValueToString(row.getCell(9));
            if("".equals(name) || "".equals(phone) || "".equals(address))
                continue;
            DailyTroubleshootRecordEntity recordEntity = new DailyTroubleshootRecordEntity();
            recordEntity.setId(UUID.randomUUID().toString());
            recordEntity.setCreateTime(TS);
            recordEntity.setMultiTenancy(multiTenancy);
            recordEntity.setAddress(address);
            recordEntity.setBodyTemperature(tempture);
            recordEntity.setBodyTemperature(tempture);
            recordEntity.setBuilding(build);
            recordEntity.setPlot(plot);
            recordEntity.setCode(UUID.randomUUID().toString());
            recordEntity.setIdentificationNumber(idCard);
            recordEntity.setSex(sex);
            recordEntity.setUnitNumber(unit);
            recordEntity.setRoomNo(roomNo);
            recordEntity.setName(name);
            recordEntity.setPhone(phone);
            // 体温大于37.5 以及 已有相同数据不添加 未过滤
            recordEntities.add(recordEntity);
        }
        return recordEntities;
    }

}
