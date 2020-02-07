package com.gsafety.dawn.community.manage.controller;


import com.gsafety.dawn.community.common.annotation.LimitIPRequestAnnotation;
import com.gsafety.dawn.community.common.exception.HttpError;
import com.gsafety.dawn.community.manage.contract.model.CellTypeModel;
import com.gsafety.dawn.community.manage.contract.service.CellTypeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Cell type controller.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "Cell Type Api")
public class CellTypeController {

    @Autowired
    private CellTypeService cellTypeService;

    /**
     * Add one cell type response entity.
     *
     * @param cellTypeModel the cell type model
     * @return the response entity
     */
    @PostMapping(value = "/cell-types", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增一个元件类型", notes = "addOneCellType(cellTypeModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CellTypeModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<CellTypeModel> addOneCellType(@RequestBody @ApiParam(value = "元件类型", required = true) CellTypeModel cellTypeModel) {
        CellTypeModel result=cellTypeService.addCellType(cellTypeModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Update one cell type response entity.
     *
     * @param cellTypeModel the cell type model
     * @return the response entity
     */
    @PutMapping(value = "/cell-types", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改一个元件类型", notes = "updateOneCellType(cellTypeModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CellTypeModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<CellTypeModel> updateOneCellType(@RequestBody @ApiParam(value = "元件类型", required = true) CellTypeModel cellTypeModel) {
        CellTypeModel result=cellTypeService.updateCellType(cellTypeModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Delete one cell type response entity.
     *
     * @param cellTypeId the cell type id
     * @return the response entity
     */
    @DeleteMapping(value = "/cell-types/{cellTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除一个元件类型", notes = "updateOneCellType(cellTypeModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CellTypeModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> deleteOneCellType(@PathVariable @ApiParam(value = "元件类型id", required = true)  String cellTypeId) {
        Boolean result=cellTypeService.deleteCellType(cellTypeId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * Update cell type sort response entity.
     *
     * @param cellTypeModels the cell type models
     * @return the response entity
     */
    @PutMapping(value = "/cell-types/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改元件的顺序", notes = "updateCellTypeSort(cellTypeModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CellTypeModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    public ResponseEntity<List<CellTypeModel>> updateCellTypeSort(@RequestBody @ApiParam(value = "元件类型集合", required = true) List<CellTypeModel> cellTypeModels) {
        List<CellTypeModel> result=cellTypeService.updateCellTypeSort(cellTypeModels);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
