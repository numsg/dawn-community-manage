package com.gsafety.dawn.community.manage.controller;


import com.gsafety.dawn.community.common.annotation.LimitIPRequestAnnotation;
import com.gsafety.dawn.community.common.exception.HttpError;
import com.gsafety.dawn.community.manage.contract.model.CellModel;
import com.gsafety.dawn.community.manage.contract.service.CellService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Cell controller.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "Cell Api")
public class CellController {
    @Autowired
    private CellService cellService;

    /**
     * Add one cell response entity.
     *
     * @param cellModel the cell model
     * @return the response entity
     */
    @PostMapping(value = "/cells", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增一个元件", notes = "addOneCell(cellModel)")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = CellModel.class), @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class), @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<CellModel> addOneCell(@RequestBody @ApiParam(value = "元件", required = true) CellModel cellModel) {
        cellModel.setId(UUID.randomUUID().toString());
        cellModel.setEditTime(new Date());
//        if (cellModel.getIsTemplate() == null) {
//            cellModel.setIsTemplate(false);
//        }
        // 保存模板
//        if (cellModel.getIsTemplate()) {
//            cellTemplateService.saveCellTemplateByCellConvert(cellModel);
//        }
        CellModel result = cellService.addOneCell(cellModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * Add one cell response entity.
     *
     * @param cellModels the cell models
     * @return the response entity
     */
    @PostMapping(value = "/cells/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导入元件", notes = "batchCells(cellModels)")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = CellModel.class), @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class), @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<CellModel>> batchCells(@RequestBody @ApiParam(value = "元件", required = true) List<CellModel> cellModels) {

        List<CellModel> result = new ArrayList<>();
        cellModels.forEach(cellModel -> {
            if (cellModel.getIsTemplate() == null) {
                cellModel.setIsTemplate(false);
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            String dateNow = df.format(calendar.getTime());
            Timestamp ts = Timestamp.valueOf(dateNow);
            cellModel.setEditTime(ts);
            CellModel oneCell = cellService.addOneCell(cellModel);
            result.add(oneCell);
        });
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Update one cell response entity.
     *
     * @param cellModel the cell model
     * @return the response entity
     */
    @PutMapping(value = "/cells", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改一个元件", notes = "updateOneCell(cellModel)")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = CellModel.class), @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class), @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<CellModel> updateOneCell(@RequestBody @ApiParam(value = "元件", required = true) CellModel cellModel) {
        // 保存模板
//        if (cellModel.getIsTemplate()) {
//            cellTemplateService.saveCellTemplateByCellConvert(cellModel);
//        }
        if (cellModel.getIsTemplate() == null) {
            cellModel.setIsTemplate(false);
        }
        CellModel result = cellService.updateOneCell(cellModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Delete one cell response entity.
     *
     * @param cellId the cell id
     * @return the response entity
     */
    @DeleteMapping(value = "/cells/{cellId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除一个元件", notes = "deleteOneCell(cellId)")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Boolean.class), @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class), @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> deleteOneCell(@PathVariable @ApiParam(value = "元件id", required = true) String cellId) {
        Boolean result = cellService.deleteOneCell(cellId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Judge name is repeat response entity.
     *
     * @param name the name
     * @return the response entity
     */
    @GetMapping(value = "/cells/{name}/check-repeat", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "判断元件是否重复", notes = "checkNameIsRepeat(name)")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Boolean.class), @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class), @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> checkNameIsRepeat(@PathVariable @ApiParam(value = "名称", required = true) String name) {
        Boolean result = cellService.judgeNameIsRepeat(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping(value = "/cells/batch/{count}/{cellId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "批量新增元件，测试性能使用", notes = "addManyCells(count)")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Boolean.class), @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class), @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
//    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
//    public ResponseEntity<Boolean> addManyCells(@PathVariable @ApiParam(value = "数量", required = true) Integer count,
//            @PathVariable @ApiParam(value = "数量", required = true) String cellId) {
//        if (count<= 0){
//            return new ResponseEntity<>(false, HttpStatus.OK);
//        }
//        Boolean result = cellService.addManyCells(count,cellId);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }


}
