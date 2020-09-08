package com.nxtele.did.client.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HandleFile {

    private static final Logger logger = LoggerFactory.getLogger(HandleFile.class);

    /**
     *  response 的参数设置
     * @param fileName  下载文件名
     * @param response
     */
    public static void setResponseParameter(String fileName,HttpServletResponse response){
        String fileNameURL = fileName;
        try {
            fileNameURL = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("文件名称：{} 转“UTF-8”编码时发送错误",fileName);
        }
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        response.setHeader("Content-disposition", "attachment;filename=" + fileNameURL);
//        response.setHeader("Content-disposition", "attachment;filename="+fileNameURL+";"+"filename*=utf-8''"+fileNameURL);

    }

    /**
     * Excel内容设置
     *
     * @param excelMap
     */
    public static HSSFWorkbook createExcel(Map<String, Object> excelMap) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = excelMap.get("sheetName").toString();
        HSSFSheet sheet = workbook.createSheet(sheetName);

        // 只考虑一个sheet的情况
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(3, 170 * 256);

        // 表头设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBoldweight((short) 14);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);
        // 是否生成序号，序号从1开始
        boolean isSerial = (boolean) excelMap.get("isSerial");
        // 获取表头列表
        List<String> headerList = (List<String>) excelMap.get("header");
        HSSFCell cell;

        //生成表头
        int headRow=0;
        if (isSerial){
            //设置第一个列为序号列
            cell = row.createCell(0);
            cell.setCellValue("序号");
            cell.setCellStyle(style);
            headRow = 1;

        }
        for (String header : headerList) {
            cell = row.createCell(headRow);
            cell.setCellValue(header);
            cell.setCellStyle(style);
            headRow++;
        }



        //往Excel中插入数据
        List<List<String>> data = (List<List<String>>) excelMap.get("data");

        int rowNum = 1;
        //需要生成序号
        if (isSerial){
            //表头字段包含序号一列
            int headSize = headerList.size() + 1;
            for (List<String> obj:data){
                HSSFRow currRow = sheet.createRow(rowNum);
                for (int i=1;i<headSize;i++){
                    currRow.createCell(0).setCellValue(rowNum);
                    currRow.createCell(i).setCellValue(obj.get(i-1));
                }
                rowNum++;
            }
            //无需生成序号
        }else{
            int headSize = headerList.size();
            for (List<String> obj:data){
                HSSFRow currRow = sheet.createRow(rowNum);
                for (int i=0;i<headSize;i++){
                    currRow.createCell(i).setCellValue(obj.get(i));
                }
                rowNum++;
            }
        }

        return workbook;
    }

    /**
     * 生成excel文件
     *
     * @param filename
     * @param workbook
     * @throws Exception
     */
    public static void buildExcelFile(String filename, HSSFWorkbook workbook) throws Exception {
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    /**
     * 浏览器下载excel
     *
     * @param filename
     * @param workbook
     * @param response
     * @throws Exception
     */
    public static void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception {
        response.setContentType("application/msexcel;charset=UTF-8");
//        response.setHeader("Content-disposition", new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * Excel内容设置_SXSSFWorkbook
     * @param excelMap              elecl数据
     * @param rowAccessWindowSize   可访问窗口大小(决定保留多少条数据在内存，其他数据会被写到磁盘里)
     * @return
     * @throws Exception
     */
    public static SXSSFWorkbook sxssfCreateExcel(Map<String, Object> excelMap, int rowAccessWindowSize) throws Exception{
        SXSSFWorkbook workbook = new SXSSFWorkbook(rowAccessWindowSize);
        // sheet名称
        String sheetName = excelMap.get("sheetName").toString();
        // 获取表头列表
        List<String> headerList = (List<String>) excelMap.get("header");

        // 获取数据
        List<List<Object>> dataList = (List<List<Object>>) excelMap.get("data");

        return exportExcel(workbook,0,sheetName,headerList,dataList);
    }

    /**
     * @Description: 导出Excel
     * @param workbook
     * @param sheetNum      (sheet的位置，0表示第一个表格中的第一个sheet)
     * @param sheetTitle    （sheet的名称）
     * @param headers       （表格的列标题）
     * @param result        （表格的数据）
     * @throws Exception
     */
    public static SXSSFWorkbook exportExcel(SXSSFWorkbook workbook,
                                            int sheetNum,
                                            String sheetTitle,
                                            List<String> headers,
                                            List<List<Object>> result){
        Sheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum,sheetTitle);

        // 设置表格默认列宽为20个字节
        sheet.setDefaultColumnWidth(20);

        // 生产一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 把字体应用到当前的样式
        style.setFont(font);

        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);

        // 产生表格标题行
        Row row = sheet.createRow(0);
        for (int i = 0 ; i < headers.size(); i++){
            Cell cell = row.createCell(i);
            ExcelUtil.setCell(cell,headers.get(i),style);
        }



        // 遍历集合数据，产生数据行
        if (result != null){
            int index = 1;
            for (List<Object> data : result){
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (Object obj : data){
                    Cell cell = row.createCell(cellIndex);
//                    ExcelUtil.setCell(cell,obj,style);
                    ExcelUtil.setCell(cell,obj);
                    cellIndex ++;
                }
                index++;
            }
        }
        return  workbook;
    }
}
