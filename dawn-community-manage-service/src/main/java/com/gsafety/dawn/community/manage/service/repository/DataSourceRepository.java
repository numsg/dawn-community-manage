package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.DataSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * The interface  data source  repository.
 */
@Repository
@Transactional
public interface DataSourceRepository  extends JpaRepository<DataSourceEntity,String> {

    /**
     * update the data source entity
     *
     * @param id          the data source entity id
     * @param name        the data source entity  name
     * @param description the data source entity description
     * @param tag         the data source entity tag
     * @param type        the data source entity type
     * @param image       the data source entity image
     * @return integer integer
     */
    @Modifying
    @Query(nativeQuery = true,value ="UPDATE b_data_source SET name = ?2 ,description=?3, tag=?4 , type=?5 , image=?6 WHERE id = ?1 ;")
    Integer updateOneDataSource(String id, String name, String description, String tag, int type, String image);

    /**
     * Query by id integer.
     *
     * @param dataSourceId the data source id
     * @return the integer
     */
    @Query
    DataSourceEntity queryById(String dataSourceId);

}
