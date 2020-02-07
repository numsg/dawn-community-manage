package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.CellModel;
import com.gsafety.dawn.community.manage.contract.service.CellService;
import com.gsafety.dawn.community.manage.service.datamappers.CellMapper;
import com.gsafety.dawn.community.manage.service.entity.CellEntity;
import com.gsafety.dawn.community.manage.service.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * The type Cell service.
 */
@Service
@Transactional
public class CellServiceImpl implements CellService {
    @Autowired
    private CellMapper cellMapper;
    @Autowired
    private CellRepository cellRepository;

      /**
     * Add one cell cell model.
     *
     * @param cellModel the cell model
     * @return the cell model
     */
    @Override
    public CellModel addOneCell(CellModel cellModel) {
        CellEntity cellEntity = cellMapper.cellModelToEntity(cellModel);
        cellEntity = cellRepository.saveAndFlush(cellEntity);
        return cellMapper.cellEntityToModel(cellEntity);
    }

    /**
     * Update one cell cell model.
     *
     * @param cellModel the cell model
     * @return the cell model
     */
    @Override
    public CellModel updateOneCell(CellModel cellModel) {
        CellModel result = null;

        Integer ok = cellRepository.updateOneCell(cellModel.getId(), cellModel.getData(), cellModel.getLayout(), cellModel.getName(), cellModel.getRules(), cellModel.getExtraInfo(), cellModel.getWidgetCount(),
                cellModel.getWidgets(), cellModel.getEditTime(), cellModel.getConditions(), cellModel.getKey());
        if (ok > 0) {
            result = cellMapper.cellEntityToModel(cellRepository.getOne(cellModel.getId()));
        }
        return result;
    }

    /**
     * Delete one cell boolean.
     *
     * @param cellId the cell id
     * @return the boolean
     */
    @Override
    public Boolean deleteOneCell(String cellId) {
        Boolean result = false;
        if(cellRepository.existsById(cellId)){
            cellRepository.deleteById(cellId);
            result=true;
        }
        return result;
    }

    /**
     * Judge name is repeat boolean.
     *
     * @param name the name
     * @return the boolean
     */
    @Override
    public Boolean judgeNameIsRepeat(String name) {
        Boolean result = false;
        if (cellRepository.queryCountByName(name)>0){
            result=true;
        }
        return result;
    }

//    @Override
//    public Boolean addManyCells(Integer count,String cellId) {
//        Random random=new Random();
//        CellEntity cellEntity=new CellEntity() ;
//        if (cellRepository.existsById(cellId)){
//             cellEntity=cellRepository.queryById(cellId).get(0);
//        }
//        if ("".equals(cellEntity.getId())){
//            return false;
//        }
//        List<CellEntity> cellEntities= new ArrayList<>();
//        for (Integer i = 0; i < count; i++) {
//            CellEntity newCellEntity=new CellEntity();
//            BeanUtil.copyPropertiesIgnoreNull(cellEntity,newCellEntity);
//
//            newCellEntity.setId(UUID.randomUUID().toString());
//            newCellEntity.setKey(UUID.randomUUID().toString());
//            Integer index=random.nextInt(nameArr.length);
//            newCellEntity.setName(nameArr[index]+"_"+i);
//            cellEntities.add(newCellEntity);
//        }
//        Integer temp = count / 100;
//        if (count % 100 >0){
//            temp = count / 100 +1;
//         }
//
//        List<List<CellEntity>> avgCellsList=ListUtil.averageAssign(cellEntities,temp);
//        avgCellsList.forEach(list-> cellRepository.saveAll(list));
//        return  true;
//
//    }
}
