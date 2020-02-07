package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.DSourceDataModel;
import com.gsafety.dawn.community.manage.contract.service.DSourceDataService;
import com.gsafety.dawn.community.manage.service.datamappers.DSourceDataMapper;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.DataSourceEntity;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The type D source data service.
 */
@Service
@Transactional
public class DSourceDataServiceImpl  implements DSourceDataService {

    @Autowired
    private DSourceDataMapper dSourceDataMapper;
    @Autowired
    private DSourceDataRepository dSourceDataRepository;
    @Autowired
    private DataSourceRepository dataSourceRepository;
    @Autowired
    private DataSourceServiceImpl dataSourceService;

    /**
     * Add one data-source data entity.
     *
     * @param dSourceDataModel the data-source data model.
     * @return the data-source data model.
     */
    @Override
    public DSourceDataModel addOneDSourceData(DSourceDataModel dSourceDataModel) {
        DSourceDataEntity dSourceDataEntity = dSourceDataMapper.modelToEntity(dSourceDataModel);
        DataSourceEntity dataSourceEntity = dataSourceRepository.getOne(dSourceDataModel.getDataSourceId());
        dSourceDataEntity.setDataSourceEntity(dataSourceEntity);
        return dSourceDataMapper.entityToModel(dSourceDataRepository.save(dSourceDataEntity));
    }

    /**
     * Add batch data-source data entity.
     *
     * @param dSourceDataModels the data-source data model.
     * @return the data-source data model.
     */
    @Override
    public List<DSourceDataModel> addBatchDSourceData(List<DSourceDataModel> dSourceDataModels , boolean isResource) {
        List<DSourceDataModel> result = new ArrayList<>();
            dSourceDataModels.forEach(dSourceDataModel -> {
                DSourceDataEntity dSourceDataEntity = addDataSource(dSourceDataModel);
                result.add(dSourceDataMapper.entityToModel(dSourceDataEntity));
//                if(!isResource){
//                    DSourceDataEntity dSourceDataEntity = addDataSource(dSourceDataModel);
//                    result.add(dSourceDataMapper.entityToModel(dSourceDataEntity));
//                } else {
//                    // 判断数据源数据是否属于资源
//                    boolean flag = dataSourceService.isResource(dSourceDataModel.getDataSourceId());
//                    if(flag){
                        // 往资源中新增数据源数据  1.查询数据源数据是否存在，处理多租户字段  2.新增
//                      String url = externalUrl + GETDSOURCEDATAFROMRMS + "id/" + dSourceDataModel.getId();
//                      DSourceDataModel rmsDataSOurceModel =  httpClientUtil.httpGet(url , DSourceDataModel.class);
//                      if(rmsDataSOurceModel == null){
//                       httpClientUtil.httpPost(externalUrl + GETDSOURCEDATAFROMRMS , dSourceDataModel , DataSourceModel.class);
//                      } else {
//                        String tendency = rmsDataSOurceModel.getMultiTenancy();
//                        if(tendency != null){
//                            String tendencyElements[] = tendency.split(";");
//                            if(!Arrays.stream(tendencyElements).collect(toList()).contains(dSourceDataModel.getMultiTenancy()))
//                                tendency += ";" + dSourceDataModel.getMultiTenancy();
//                            dSourceDataModel.setMultiTenancy(tendency);
//                        }
//                        String updateDSourceDataUrl = externalUrl + GETDSOURCEDATAFROMRMS + "/" + dSourceDataModel.getId();
//                        httpClientUtil.httpPut(updateDSourceDataUrl , dSourceDataModel , DSourceDataModel.class , true);
//                      }
//                    } else {
//                        // 不属于资源则添加到pms数据库
//                        addDataSource(dSourceDataModel);
//                    }
//                    result.add(dSourceDataModel);
//                }
            });
        return result;
    }

    /**
     * 导入数据源数据到pms数据库
     * @param dSourceDataModel data source data model
     * @return data source data entity
     */
    public DSourceDataEntity addDataSource(DSourceDataModel dSourceDataModel){
        DSourceDataEntity dSourceDataEntity=new DSourceDataEntity();
        if (dSourceDataRepository.existsById(dSourceDataModel.getId())){
            dSourceDataEntity=dSourceDataRepository.getOne(dSourceDataModel.getId());
        }
       // DSourceDataEntity dSourceDataEntity = dSourceDataRepository.getOne(dSourceDataModel.getId()).orElse(new DSourceDataEntity());
        if(dSourceDataEntity.getId() == null){
            dSourceDataModel.setMultiTenancy(dSourceDataModel.getMultiTenancy() + ";");
            dSourceDataEntity = dSourceDataRepository.saveAndFlush(dSourceDataMapper.modelToEntity(dSourceDataModel));
        } else{
            String tendency = dDSourceTenancy(dSourceDataEntity , dSourceDataModel);
            dSourceDataModel.setMultiTenancy( tendency);
            dSourceDataEntity = dSourceDataRepository.saveAndFlush(dSourceDataMapper.modelToEntity(dSourceDataModel));
        }
        return dSourceDataEntity;
    }


    /**
     *  重组数据源数据多租户字段
     * @param dSourceDataEntity entity
     * @param dSourceDataModel model
     * @return string
     */
    public String  dDSourceTenancy(DSourceDataEntity dSourceDataEntity , DSourceDataModel dSourceDataModel){
            String alreadyTendency = dSourceDataEntity.getMultiTenancy();
            if(alreadyTendency == null){
                return dSourceDataModel.getMultiTenancy() + ";";
            }
            String[] tendencies = alreadyTendency.split(";");
            boolean isAlready =  Arrays.stream(tendencies).collect(toList()).contains(dSourceDataModel.getMultiTenancy());
            if(isAlready)
                return alreadyTendency;
            return alreadyTendency  + dSourceDataModel.getMultiTenancy() + ";";
//            return alreadyTendency + ";" + dSourceDataModel.getMultiTenancy();
    }

    /**
     * Delete  data-source data entity by id.
     *
     * @param dSourceDataId the data-source data id
     * @return boolean
     */
    @Override
    public Boolean deleteDSourceData(String dSourceDataId) {
        Boolean result = false;
        if (dSourceDataRepository.existsById(dSourceDataId)) {
            List<DSourceDataEntity> dSourceDataEntities = dSourceDataRepository.getChildDSourceData(dSourceDataId);
            dSourceDataRepository.deleteAll(dSourceDataEntities);
            result=true;
        }
        return result;
    }

    /**
     * update  data-source data entity.
     *
     * @param dSourceDataModel the data-source data model.
     * @return the data-source data model
     */
    @Override
    public DSourceDataModel updateOneDSourceData(DSourceDataModel dSourceDataModel) {
        DSourceDataModel result = null;
        if (0 < dSourceDataRepository.updateOneDSourceData(dSourceDataModel.getId(), dSourceDataModel.getName(), dSourceDataModel.getDataSourceId(), dSourceDataModel.getPid(), dSourceDataModel.getImage(), dSourceDataModel.getImgColor())) {
            DSourceDataEntity dSourceDataEntity = dSourceDataRepository.getOne(dSourceDataModel.getId());
            result = dSourceDataMapper.entityToModel(dSourceDataEntity);
        }
        return result;
    }

    /**
     * Gets one d source data by id.
     *
     * @param dSourceDataIds the d source data ids
     * @return the one d source data by id
     */
    @Override
    public List<DSourceDataModel> getSomeDSourceDataByIds(List<String> dSourceDataIds) {
        List<DSourceDataModel> dSourceDataModels = new ArrayList<>();
        if(dSourceDataIds!=null && !dSourceDataIds.isEmpty()){
            List<DSourceDataEntity> dSourceDataEntities=dSourceDataRepository.findAllById(dSourceDataIds);
            dSourceDataModels=dSourceDataMapper.entitiesToModels(dSourceDataEntities);
        }
        return dSourceDataModels;
    }

    /**
     * Gets one d source data by id.
     *
     * @param id the id
     * @return the one d source data by id
     */
    @Override
    public DSourceDataModel getOneDSourceDataById(String id) {
        DSourceDataModel dSourceDataModel= null;
        if (dSourceDataRepository.existsById(id)){
            DSourceDataEntity dSourceDataEntity=dSourceDataRepository.getOne(id);
            dSourceDataModel=dSourceDataMapper.entityToModel(dSourceDataEntity);
        }
        return dSourceDataModel;
    }

    /**
     * Gets d source data by data source id.
     *
     * @param dataSourceId the data source id
     * @return the d source data by data source id
     */
    @Override
    public List<DSourceDataModel> getDSourceDataByDataSourceId(String dataSourceId) {
        List<DSourceDataEntity> dSourceDataEntities = new ArrayList<>();
        if (dataSourceRepository.existsById(dataSourceId)) {
            dSourceDataEntities = dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(dataSourceId);
        }
        return dSourceDataMapper.entitiesToModels(dSourceDataEntities);
    }

    /**
     * Gets all d source data by data source id.
     *
     * @param originalId the origin id
     * @return the all d source data by data source id
     */
    @Override
    public List<DSourceDataModel> getAllDSourceDataByDataSourceId(String originalId,Boolean isUseRMS) {
        List<DSourceDataModel>  result =new ArrayList<>();
        List<DSourceDataEntity> dSourceDataEntities=dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(originalId);
       if (!dSourceDataEntities.isEmpty()){
           result=dSourceDataMapper.entitiesToModels(dSourceDataEntities);
       }
//       if ( isUseRMS){
//           List temp = httpClientUtil.httpGet(EXTERNAL_ACCESS_URL + GETDSOURCEDATAFROMRMS + originalId ,List.class);
//           for (Object object : temp) {
//               DSourceDataModel dSourceDataModel= JsonUtil.fromJson(JsonUtil.toJson(object),DSourceDataModel.class);
//               if (dSourceDataModel.getPid().equals(originalId)){
//                   dSourceDataModel.setPid("-1");
//               }
//               result.add(dSourceDataModel);
//           }
//       }
        return result;
    }

    /**
     * Update d source data sort list.
     *
     * @param dSourceDataModels the d source data models
     * @return the list
     */
    @Override
    public List<DSourceDataModel> updateDSourceDataSort(List<DSourceDataModel> dSourceDataModels) {
        List<DSourceDataModel> result= new ArrayList<>();
        for (DSourceDataModel dSourceDataModel : dSourceDataModels) {
            if (dSourceDataRepository.updateDSourceDataSort(dSourceDataModel.getId(),dSourceDataModel.getSort(),dSourceDataModel.getPid())>0){
                result.add(dSourceDataMapper.entityToModel(dSourceDataRepository.getOne(dSourceDataModel.getId())));
            }else{
                // 修改失败
                result = new ArrayList<>();
                break;
            }
        }
        return result;
    }


}
