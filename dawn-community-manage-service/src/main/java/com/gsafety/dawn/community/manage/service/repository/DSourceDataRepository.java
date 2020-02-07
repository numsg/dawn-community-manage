package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The interface  data-source data repository.
 */
@Repository
@Transactional
public interface DSourceDataRepository extends JpaRepository<DSourceDataEntity,String> {

    /**
     * query all childs(1,2,3 floors)  when pid is id;
     *
     * @param id pid
     * @return the data-source data list
     */
    @Query(nativeQuery = true, value = "WITH RECURSIVE cp AS(\n" +
            "\tSELECT  t1.* from b_d_source_data t1 \n" +
            "\tWHERE t1.id=:id \n" +
            "\n" +
            "\tUNION \n" +
            "\n" +
            "\tSELECT t2.*  from b_d_source_data t2,cp\n" +
            "\twhere t2.pid = cp.id\n" +
            ")  SELECT * from cp;")
    List<DSourceDataEntity> getChildDSourceData(@Param("id") String id);

    /**
     * updata the data-source data entity;
     *
     * @param id           the data-source data entity id
     * @param name         the data-source data entity name
     * @param dataSourceId the data-source data entity dataSourceId
     * @param pid          the data-source data entity pid
     * @param image        the image
     * @param imgColor     the img color
     * @return integer integer
     */
    @Modifying
    @Query(nativeQuery = true,value ="UPDATE b_d_source_data SET name = ?2 ,b_d_source_id=?3 ,pid=?4,image=?5,img_color=?6  WHERE id = ?1 ;")
    Integer updateOneDSourceData(String id, String name, String dataSourceId, String pid, String image, String imgColor);

    /**
     * Query by data source entity list.
     *
     * @param dataSourceId the data source id
     * @return the list
     */
    @Query
    List<DSourceDataEntity> queryByDataSourceIdOrderBySortAsc(String dataSourceId);


    /**
     * Update district level sort integer.
     *
     * @param districtLevelId the district level id
     * @param sort            the sort
     * @param pid             the pid
     * @return the integer
     */
    @Modifying
    @Query(nativeQuery = true,value ="UPDATE b_d_source_data SET sort = ?2 , pid=?3  WHERE id = ?1 ;")
    Integer updateDSourceDataSort(String districtLevelId, String sort, String pid);
}
