package com.gsafety.dawn.community.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @create 2020-02-08 14:23
 */
public class ExcelUtil {

    // 日志
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    // xls
    private static final String XLS = "xls";
    // xlsx
    private static final String XLSX = "xlsx";

    private ExcelUtil(){

    }

    /**
     * 根据文件后缀名获取工作簿对象
     *
     * @param inputStream
     * @param fileType
     * @return
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) {
        Workbook workbook = null;
        try {
            if (fileType.equalsIgnoreCase(XLS)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileType.equalsIgnoreCase(XLSX)) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            logger.error( e.getMessage() , e);
        }
        return workbook;
    }


    // 把单元格类容转换成字符串
    public static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            // 字符串 STRING
            case Cell.CELL_TYPE_STRING:
                returnValue = cell.getStringCellValue();
                break;
            // 数字  NUMERIC
            case Cell.CELL_TYPE_NUMERIC:
                Double doubleValue = cell.getNumericCellValue();
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            // boolean
            case Cell.CELL_TYPE_BOOLEAN:
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            // 公式  FORMULA
            case Cell.CELL_TYPE_FORMULA:
                returnValue = cell.getCellFormula();
                break;
            default:
                break;
        }
        return returnValue;
    }


    /**
     * 获取sheet
     */
    public static List<Sheet> getExcelSheet(InputStream inputStream, String fileType) {
        List<Sheet> sheets = new ArrayList<>();
        Workbook workbook = getWorkbook(inputStream, fileType);
        if (workbook == null) {
            return Collections.emptyList();
        }
        int sheetCount = workbook.getNumberOfSheets();
        if (sheetCount == 0) {
            return Collections.emptyList();
        }
        for (int sheetNumber = 0; sheetNumber < sheetCount; sheetNumber++) {
            sheets.add(workbook.getSheetAt(sheetNumber));
        }
        return sheets;
    }

    /**
     * 判断是否是合并单元格
     */
    public static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress cra = sheet.getMergedRegion(i);
            int firstColumn = cra.getFirstColumn();
            int lastColumn = cra.getLastColumn();
            int firstRow = cra.getFirstRow();
            int lastRow = cra.getLastRow();
            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取合并单元格的值
     */
    public static String getMergeRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress cra = sheet.getMergedRegion(i);
            int firstColumn = cra.getFirstColumn();
            int lastColumn = cra.getLastColumn();
            int firstRow = cra.getFirstRow();
            int lastRow = cra.getLastRow();
            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                Row fRow = sheet.getRow(firstRow);
                Cell cell = fRow.getCell(firstColumn);
                return convertCellValueToString(cell);
            }
        }
        return null;
    }

    /**
     * 获取合并单元格 的cra
     */
    public static CellRangeAddress getMergeCRA(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress cra = sheet.getMergedRegion(i);
            int firstRow = cra.getFirstRow();
            int lastRow = cra.getLastRow();
            int firstColumn = cra.getFirstColumn();
            int lastColumn = cra.getLastColumn();
            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                return cra;
            }
        }
        return null;
    }

    /**
     * 获取合并单元格 跨的行数
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static int getMergeRegionCount(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress cra = sheet.getMergedRegion(i);
            int firstColumn = cra.getFirstColumn();
            int lastColumn = cra.getLastColumn();
            int firstRow = cra.getFirstRow();
            int lastRow = cra.getLastRow();
            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                return lastRow - firstRow + 1;
            }
        }
        return 0;
    }


    /**
     * 获取的excel所有的行数
     *
     * @param file
     * @return
     */
    public static int getExcelRowCount(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
        int rowCounts = 0;
        try {
            List<Sheet> sheets = getExcelSheet(file.getInputStream(), fileType);
            for (Sheet sheet : sheets) {
                rowCounts += sheet.getLastRowNum();
            }
        } catch (IOException e) {
            logger.error( e.getMessage() , e);
        }
        return rowCounts;
    }

}
