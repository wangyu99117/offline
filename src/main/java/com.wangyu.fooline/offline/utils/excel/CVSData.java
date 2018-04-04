package com.wangyu.fooline.offline.utils.excel;

import com.csvreader.CsvWriter;
import com.wangyu.fooline.offline.excel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by wangyu21 on 2016/9/23.
 */
public class CVSData implements ExcelUtil {

    private final static Logger LOG = LoggerFactory.getLogger(CVSData.class);

    private CsvWriter csvOutput;

    private List<String> FieldName = new LinkedList<>();

    private List<Method> getMethods = new LinkedList<>();

    @Override
    public <T> void export(Field[] fields, Class target, Collection<T> dataset, OutputStream out, boolean pager) {

    }

    @Override
    public <T> void export(LinkedHashMap<String, String> colName, Class target, Collection<T> dataset, OutputStream out, boolean pager) {
        csvOutput = new CsvWriter(out, ',', Charset.forName("GBK"));

        try{
            //表头
            for(Iterator ite = colName.entrySet().iterator(); ite.hasNext();){
                Map.Entry<String, String> entry = (Map.Entry) ite.next();
                FieldName.add(entry.getKey());

                String getMethodName = "get"
                        + entry.getKey().substring(0, 1).toUpperCase()
                        + entry.getKey().substring(1);

                getMethods.add(getMethodByName(getMethodName, target));
                csvOutput.write(entry.getValue());
            }
            csvOutput.endRecord();

            //表body
            Iterator<T> it = dataset.iterator();
            int index = 0;//导出记录数
            while (it.hasNext()) {
                index++;
                T t = (T) it.next();
                for(Method getMethod : getMethods){
                    Object value = getMethod.invoke(t, null);
                    csvOutput.write(value.toString());
                }
                csvOutput.endRecord();
            }
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void export(LinkedHashMap<String, String> colName, Collection<T> dataset, OutputStream out, boolean pager) {
        csvOutput = new CsvWriter(out, ',', Charset.forName("GBK"));

        try{
            //表头
            for(Iterator ite = colName.entrySet().iterator(); ite.hasNext();){
                Map.Entry<String, String> entry = (Map.Entry) ite.next();
                FieldName.add(entry.getKey());
                csvOutput.write(entry.getValue());
            }
            csvOutput.endRecord();

            //表body
            Iterator<T> it = dataset.iterator();
            int index = 0;//导出记录数
            while (it.hasNext()) {
                index++;
                if(index > 1000000){//大于1000000条 ，停止导出
                    csvOutput.close();
                    break;
                }
                Map<String, Object> t = (Map<String, Object>) it.next();

                for(String keyName : FieldName){
                    Object value = t.get(keyName);
                    if(value == null){
                        csvOutput.write("");
                    }else{
                        csvOutput.write(value.toString());
                    }
                }
                csvOutput.endRecord();
            }
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void export(List<Sheet<T>> sheets, OutputStream out) {

    }

    @Override
    public void exportHeader(LinkedHashMap<String, String> colName, OutputStream out) {
        csvOutput = new CsvWriter(out, ',', Charset.forName("GBK"));
        try{
            //表头
            for(Iterator ite = colName.entrySet().iterator(); ite.hasNext();){
                Map.Entry<String, String> entry = (Map.Entry) ite.next();
                csvOutput.write(entry.getValue());
            }
            csvOutput.endRecord();
            csvOutput.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Method getMethodByName(String methodName, Class target){
        Method method = null;
        try {
            method = target.getMethod(methodName, null);
        } catch (NoSuchMethodException e) {
            LOG.error("导出时获取 调用方法反射失败！", e);
        }
        return method;
    }
}
