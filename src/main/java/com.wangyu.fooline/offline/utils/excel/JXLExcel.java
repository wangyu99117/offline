package com.wangyu.fooline.offline.utils.excel;/*
package com.jd.pop.form.offline.utils.excel;

import com.jd.pop.form.offline.excel.Sheet;
import com.jd.pop.form.utils.DateUtils;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

*/
/**
 * Created by wangyu21 on 2017/9/7.
 *//*

public class JXLExcel implements ExcelUtil{

    private final static Logger LOG = Logger.getLogger(JXLExcel.class);

    public JXLExcel() {

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
    public <T> void export(List<Sheet<T>> sheets, OutputStream out) throws IOException {
        WritableWorkbook writeBook = Workbook.createWorkbook(out);
        if(CollectionUtils.isEmpty(sheets) || null == out){
            throw new IllegalArgumentException();
        }



        for(Sheet dataSheet : sheets){
            LinkedHashMap<String, String> colNames = dataSheet.getColName();
            List<T> resList = dataSheet.getResList();
            List<String> colNamesKey = new LinkedList<>();

            WritableSheet sheet = writeBook.createSheet(dataSheet.getName(), );
            //sheet.setDefaultColumnWidth(COLUMNWIDTH);

            HSSFRow row = sheet.createRow(0);

            int i = 0;//列标
            for(Iterator ite = colNames.entrySet().iterator(); ite.hasNext();){
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(theadStyle);

                Map.Entry<String, String> entry = (Map.Entry) ite.next();
                colNamesKey.add(entry.getKey());
                HSSFRichTextString text = new HSSFRichTextString(entry.getValue());
                cell.setCellValue(text);
                i++;
            }

            //表body
            Iterator<T> it = resList.iterator();
            int index = 0;//导出记录数
            while (it.hasNext()) {
                index++;
                Map<String, Object> t = (Map<String, Object>) it.next();
                HSSFRow tempRow = sheet.createRow(index);

                int colIndex = 0;
                for(String keyName : colNamesKey){
                    HSSFCell cell = tempRow.createCell(colIndex);
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

                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                    */
/*richString.applyFont(stringFont);*//*

                    cell.setCellValue(richString);
                }
            }
            LOG.info("export data " + index +" 条");
        }

    }

    @Override
    public void exportHeader(LinkedHashMap<String, String> colName, OutputStream out) {

    }
}
*/
