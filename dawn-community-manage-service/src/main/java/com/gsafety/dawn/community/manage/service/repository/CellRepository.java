package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.CellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * The interface Cell repository.
 */
@Repository
@Transactional
public interface CellRepository extends JpaRepository<CellEntity,String> {
    /**
     * Query by cell type id list.
     *
     * @param cellTypeId the cell type id
     * @return the list
     */
    @Query(nativeQuery=true,value = "select * from b_cell where extra_info like concat('%',?1,'%') ;")
    List<CellEntity> vagueQueryByCellTypeId(String cellTypeId);

    /**
     * Update one cell integer.
     *
     * @param id          the id
     * @param data        the data
     * @param layout      the layout
     * @param name        the name
     * @param rules       the rules
     * @param extraInfo   the extra info
     * @param widgetCount the widget count
     * @param widgets     the widgets
     * @param editTime    the edit time
     * @param conditions  the conditions
     * @param key         the key
     * @return the integer
     */
    @Modifying
    @Query(nativeQuery=true,value = "UPDATE b_cell SET data = ?2,layout = ?3,name = ?4,rules = ?5,extra_info = ?6,widget_count = ?7,widgets = ?8,edit_time = ?9,conditions = ?10,key = ?11 WHERE id = ?1 ;")
    Integer  updateOneCell(String id, String data, String layout, String name, String rules, String extraInfo, Integer widgetCount, String widgets, Date editTime, String conditions, String key);

    /**
     * Query by name integer.
     *
     * @param cellName the cell name
     * @return the integer
     */
    @Query(nativeQuery=true,value = "select count(*) from b_cell where name =?1 ;")
    Integer queryCountByName(String cellName);

    @Query
    List<CellEntity> queryById(String id);
}
