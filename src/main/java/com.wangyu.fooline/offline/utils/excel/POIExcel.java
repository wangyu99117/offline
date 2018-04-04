package com.wangyu.fooline.offline.utils.excel;

import com.wangyu.fooline.offline.excel.Sheet;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by wangyu21 on 2016/7/28.
 */
public class POIExcel implements ExcelUtil{

    private final static Logger LOG = LoggerFactory.getLogger(POIExcel.class);

    private XSSFWorkbook workbook;

    private XSSFCellStyle theadStyle;
    private XSSFFont theadCellFont;

    private XSSFCellStyle tbodyStyle;
    private XSSFFont tbodyCellFont;

    private XSSFFont stringFont;

    private static final short COLUMNWIDTH = 25;


    public POIExcel() {
        this.workbook = new XSSFWorkbook();
        this.theadStyle = workbook.createCellStyle();

        //theadStyle.setFillForegroundColor(HSSFColor.BLACK.index);
        //theadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        theadStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        theadStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        theadStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        theadStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        theadStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        theadCellFont = workbook.createFont();
        //theadCellFont.setColor(HSSFColor.WHITE.index);
        theadCellFont.setFontHeightInPoints((short) 12);
        theadCellFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

        theadStyle.setFont(theadCellFont);

        tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        tbodyStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        tbodyStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        tbodyStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        tbodyStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        tbodyStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        tbodyStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        tbodyStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

        tbodyCellFont = workbook.createFont();
        tbodyCellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        tbodyStyle.setFont(tbodyCellFont);

        /*stringFont = workbook.createFont();
        stringFont.setColor(HSSFColor.BLUE.index);*/
    }


    @Override
    public <T> void export(List<Sheet<T>> sheets, OutputStream out) throws IOException {
        if(CollectionUtils.isEmpty(sheets) || null == out){
            throw new IllegalArgumentException();
        }

        for(Sheet dataSheet : sheets){
            LinkedHashMap<String, String> colNames = dataSheet.getColName();
            List<T> resList = dataSheet.getResList();
            List<String> colNamesKey = new LinkedList<>();

            XSSFSheet sheet = workbook.createSheet(dataSheet.getName());
            sheet.setDefaultColumnWidth(COLUMNWIDTH);

            XSSFRow row = sheet.createRow(0);

            int i = 0;//列标
            for(Iterator ite = colNames.entrySet().iterator(); ite.hasNext();){
                XSSFCell cell = row.createCell(i);
                cell.setCellStyle(theadStyle);

                Map.Entry<String, String> entry = (Map.Entry) ite.next();
                colNamesKey.add(entry.getKey());
                XSSFRichTextString text = new XSSFRichTextString(entry.getValue());
                cell.setCellValue(text);
                i++;
            }

            //表body
            Iterator<T> it = resList.iterator();
            int index = 0;//导出记录数
            while (it.hasNext()) {
                index++;
                Map<String, Object> t = (Map<String, Object>) it.next();
                XSSFRow tempRow = sheet.createRow(index);

                int colIndex = 0;
                for(String keyName : colNamesKey){
                    XSSFCell cell = tempRow.createCell(colIndex);
                    colIndex++;
                    Object value = t.get(keyName);
                    String textValue = null;

                    if(value == null || "".equals(value)){
                        textValue = "";
                    }else if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        if(bValue){
                            textValue = "是";
                        }else{
                            textValue = "否";
                        }
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        textValue = DateUtils.getDateStr(date);
                    } else{
                        textValue = value.toString();
                    }

                    XSSFRichTextString richString = new XSSFRichTextString(textValue);
                    /*richString.applyFont(stringFont);*/
                    cell.setCellValue(richString);
                }
            }
            LOG.info("export data " + index +" 条");
        }

        workbook.write(out);
    }


    @Override
    public <T> void export(Field[] fields, Class target, Collection<T> dataset, OutputStream out, boolean pager) {

    }

    @Override
    public <T> void export(LinkedHashMap<String, String> colName, Class target, Collection<T> dataset, OutputStream out, boolean pager) {

    }

    @Override
    public <T> void export(LinkedHashMap<String, String> colName, Collection<T> dataset, OutputStream out, boolean pager) {

    }

    @Override
    public void exportHeader(LinkedHashMap<String, String> colName, OutputStream out) {

    }


}
