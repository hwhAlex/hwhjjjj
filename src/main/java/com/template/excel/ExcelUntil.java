package com.template.excel;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by honor on 2018/2/11.
 */
public class ExcelUntil {
    public static void export(OutputStream outputStream,List target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        long startTime = System.currentTimeMillis();

        StringTemplateGroup stGroup = new StringTemplateGroup("stringTemplate");
        //解决可能发生的中文乱码
        stGroup.setFileCharEncoding("UTF-8");
        //写入excel文件头部信息
        StringTemplate head =  stGroup.getInstanceOf("com/template/tpl/head");

        PrintWriter writer = new PrintWriter(new BufferedOutputStream(outputStream));
        writer.print(head.toString());
        writer.flush();

        int totalRowNum = target.size();
        int maxRowNum = 60000;
        int sheets = totalRowNum % 60000 == 0 ? (totalRowNum/maxRowNum) : (totalRowNum/maxRowNum +1);
        //int sheets = 3;
        //excel单表最大行数是65535

        List record = target;
        List<String> title = new ArrayList<String>();
        List<Method> getMethods = new ArrayList<Method>();
        Class<?> clazz = record.get(0).getClass();

        Field[] fields = clazz.getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field : fields){
                if(!"serialVersionUID".equals(field.getName())) {
                    title.add(field.getName());
                    getMethods.add(clazz.getDeclaredMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)));
                }
            }
        }
//        BeanInfo beanInfo=Introspector.getBeanInfo(clazz,Object.class);
//        PropertyDescriptor[] proDescrtptors=beanInfo.getPropertyDescriptors();
//        for(PropertyDescriptor propertyDescriptor : proDescrtptors){
//            title.add(propertyDescriptor.getName());
//            getMethods.add(propertyDescriptor.getReadMethod());
//        }
        int columnLength = title.size();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //写入excel文件数据信息
        for(int i=0;i<sheets;i++){
            StringTemplate body =  stGroup.getInstanceOf("com/template/tpl/body");
            Worksheet worksheet = new Worksheet();
            worksheet.setTitle(title);
            worksheet.setSheet(" "+(i+1)+" ");
            worksheet.setColumnNum(columnLength);
            worksheet.setRowNum(maxRowNum+1);
            List<Row> rows = new ArrayList<Row>();
            int startIndex = i*maxRowNum;
            int endIndex = Math.min((i+1)*maxRowNum -1,totalRowNum-1);
            /*int startIndex = 0;
            int endIndex = record.size()-1;*/
            for(int j=startIndex;j<=endIndex;j++){
                Row row = new Row();
                List<String> result = new ArrayList<String>(columnLength);
                for(int n=0;n<columnLength;n++){
                    Object value = getMethods.get(n).invoke(record.get(j));
                    if(value == null){
                        result.add("");
                    }else{
                        if(value instanceof Date){
                            result.add(sdf.format((Date)value));
                        }else{
                            result.add(value.toString());
                        }
                    }

                }
                row.setResult(result);
                rows.add(row);
            }
            worksheet.setRows(rows);
            body.setAttribute("worksheet", worksheet);
            writer.print(body.toString());
            writer.flush();
            rows.clear();
            rows = null;
            worksheet = null;
            body = null;
            Runtime.getRuntime().gc();
            System.out.println("正在生成excel文件的 sheet"+(i+1));
        }

        //写入excel文件尾部
        writer.print("</Workbook>");
        writer.flush();
        writer.close();
        System.out.println("生成excel文件完成");
        long endTime = System.currentTimeMillis();
        System.out.println("用时="+((endTime-startTime)/1000)+"秒");
    }


    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        List<Sample> result = new ArrayList<Sample>();
        for(int i=0;i<80000;i++){
            result.add(new Sample("放大双方的"+String.valueOf(i),String.valueOf(i)));
        }
        //OutputStream outputStream = new FileOutputStream("D:/output2.xls");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ExcelUntil.export(byteArrayOutputStream,result);
        //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        //解决可能发生的中文乱码
        //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toString().getBytes("UTF-8"));

        File file = new File("E:/output5.xls");
        OutputStream output = new FileOutputStream(file);
        BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
        //bufferedOutput.write(byteArrayOutputStream.toByteArray());
        bufferedOutput.write(byteArrayOutputStream.toString().getBytes("UTF-8"));
        bufferedOutput.flush();
        bufferedOutput.close();

    }

}
