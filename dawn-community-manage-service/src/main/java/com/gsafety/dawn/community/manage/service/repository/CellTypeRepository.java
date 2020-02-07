package com.gsafety.dawn.community.manage.service.repository;


import com.gsafety.dawn.community.manage.service.entity.CellTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The interface Cell type repository.
 */
@Repository
@Transactional
public interface CellTypeRepository extends JpaRepository<CellTypeEntity,String> {

    /**
     * Update cell type integer.
     *
     * @param cellTypeId          the cell type id
     * @param cellTypeName        the cell type name
     * @param cellTypeDescription the cell type description
     * @param cellTypePid         the cell type pid
     * @param image               the image
     * @param imageColor          the image color
     * @return the integer
     */
    @Modifying
    @Query(nativeQuery = true,value ="UPDATE b_cell_type SET name = ?2 ,description=?3, pid=?4 ,image=?5,img_color=?6  WHERE id = ?1 ;")
    Integer updateCellType(String cellTypeId, String cellTypeName, String cellTypeDescription, String cellTypePid, String image, String imageColor);

    /**
     * Find by pid list.
     *
     * @param pid the pid
     * @return the list
     */
    List<CellTypeEntity> findByPid(String pid);

    @Modifying
    @Query(nativeQuery = true,value ="UPDATE b_cell_type SET sort = ?2 , pid=?3  WHERE id = ?1 ;")
    Integer updateCellTypeSort(String districtLevelId, String sort, String pid);
}
