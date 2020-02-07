package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.DSourceDataModel;
import com.gsafety.dawn.community.manage.contract.model.DataSourceModel;
import com.gsafety.dawn.community.manage.contract.service.DSourceDataService;
import com.gsafety.dawn.community.manage.contract.service.DataSourceService;
import com.gsafety.dawn.community.manage.service.datamappers.DataSourceMapper;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.DataSourceEntity;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface data source service impl.
 */
@Service
@Transactional
public class DataSourceServiceImpl  implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;
    @Autowired
    private DataSourceRepository dataSourceRepository;
    @Autowired
    private DSourceDataRepository dSourceDataRepository;
    @Autowired
    private DSourceDataService dSourceDataService;

    /**
     * Add one data source entity.
     *
     * @param dataSourceModel the data source model.
     * @return the data source model.
     */
    @Override
    public DataSourceModel addOneDataSource(DataSourceModel dataSourceModel) {
        DataSourceEntity dataSourceEntity = dataSourceMapper.modelToEntity(dataSourceModel);
        List<DSourceDataModel> dSourceDataModels = dataSourceModel.getdSourceDataModels();
        List<DSourceDataModel> resultDSourceData = new ArrayList<>();
        DataSourceModel result = dataSourceMapper.entityToModel(dataSourceRepository.save(dataSourceEntity));
        for (DSourceDataModel dSourceDataModel : dSourceDataModels) {
            dSourceDataModel.setDataSourceId(result.getId());
            dSourceDataModel = dSourceDataService.addOneDSourceData(dSourceDataModel);
            resultDSourceData.add(dSourceDataModel);
        }
        result.setdSourceDataModels(resultDSourceData);
        return result;
    }

    /**
     * Add batch data source list.
     *
     * @param dataSourceModels the data source models
     * @param isResource       the is resource
     * @return the list
     */
    @Override
    public List<DataSourceModel> addBatchDataSource(List<DataSourceModel> dataSourceModels, boolean isResource) {
        List<DataSourceModel> result = new ArrayList<>();
        dataSourceModels.forEach(dataSourceModel -> {
            result.add(addOneDataSource(dataSourceModel));
//            if(!isResource){
//               result.add(addOneDataSource(dataSourceModel));
//            } else {
                //判断数据源id的来源 , 来自资源系统则新增到资源系统，否则新增到pms
//                boolean flag = isResource(dataSourceModel.getId());
//                if(!flag){
//                    addOneDataSource(dataSourceModel);
//                }
//                result.add(dataSourceModel);
 //           }
        });
        return result;
    }

    /**
     * Delete one data source entity
     *
     * @param dataSourceId the data source id.
     * @return boolean
     */
    @Override
    public Boolean deleteOneDataSource(String dataSourceId) {
        Boolean result = false;
        if (dataSourceRepository.existsById(dataSourceId)) {
            List<DSourceDataEntity> dSourceDataEntities = dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(dataSourceId);
            dSourceDataRepository.deleteAll(dSourceDataEntities);
            dataSourceRepository.deleteById(dataSourceId);
            result = true;
        }
        return result;
    }

    /**
     * Update one data source entity.
     *
     * @param dataSourceModel the data source model.
     * @return the data source model.
     */
    @Override
    public DataSourceModel updateOneDataSource(DataSourceModel dataSourceModel) {
        DataSourceModel result = null;
        if (0 < dataSourceRepository.updateOneDataSource(dataSourceModel.getId(), dataSourceModel.getName(), dataSourceModel.getDescription(), dataSourceModel.getTag(), dataSourceModel.getType(), dataSourceModel.getImage())) {
            result = dataSourceMapper.entityToModel(dataSourceRepository.getOne(dataSourceModel.getId()));
        }
        return result;
    }

    /**
     * Gets all data sources.
     *
     * @return the all data sources
     */
    @Override
    public List<DataSourceModel> getAllDataSources(Boolean isUseRMS) {
     //   List<DataSourceModel> result = new ArrayList<>();
//        if (isUseRMS) {
//            List temp = httpClientUtil.httpGet(EXTERNAL_ACCESS_URL + GETDATASOURCEFROMRMS, List.class);
//            for (Object object : temp) {
//                DataSourceModel dataSourceModel = JsonUtil.fromJson(JsonUtil.toJson(object), DataSourceModel.class);
//                result.add(dataSourceModel);
//            }
//        }
        // 查询本系统的数据源
        List<DataSourceEntity> dataSourceEntities = dataSourceRepository.findAll();
        List<DataSourceModel> dataSourceModels = dataSourceMapper.entitiesToModels(dataSourceEntities);
        dataSourceModels.forEach(item->{
            List<DSourceDataModel> dSourceDataModels=dSourceDataService.getDSourceDataByDataSourceId(item.getId());
            item.setdSourceDataModels(dSourceDataModels);
        });
        // 由于资源系统查询的数据源有originalId，所以从本系统查询也附上值
       // dataSourceModels.forEach(item -> item.setOriginalId(item.getId()));
       // result.addAll(dataSourceModels);
        // 如果有重复的，需要去掉本系统的数据源，留下资源系统的
       // result = result.stream().filter(StreamUtils.distinctByKey(DataSourceModel::getId)).collect(Collectors.toList());
        return dataSourceModels;
    }
}
