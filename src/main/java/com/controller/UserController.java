package com.controller;

import com.alibaba.fastjson.JSON;
import com.common.HttpRequest;
import com.model.User;
import com.param.InvitationDocParam;
import com.poi.CustomXWPFDocument;
import com.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by honor on 2017/9/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/exceldata" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void excelData(HttpServletResponse res) throws Exception{
        FileInputStream in = new FileInputStream("E:/Desktop/达人累计数据-1103.xls");
        Workbook wb = new HSSFWorkbook(in);
        Sheet sheet = wb.getSheetAt(0);
        List<Long> feedList = new ArrayList<>();
        for(int i=1;i<13634;i++){
            Row row = sheet.getRow(i);
            Long feedId = Long.parseLong(row.getCell(0).getStringCellValue());
            if(feedList.contains(feedId)){
                sheet.removeRow(row);
            }else {
                feedList.add(feedId);
            }
        }
        if(in != null){
            in.close();
        }
        exportExcel(wb, res, "exceldata" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

    }

    @RequestMapping(value = "/test" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String test(String id) throws Exception{

        return id;
    }

    @RequestMapping(value = "/insert" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String insert(String userName,String password) throws Exception{
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        userService.insert(user);
        return "success";
    }

    @RequestMapping(value = "/get" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public User get(Integer id) throws Exception{
        User user = userService.getById(id);
        return user;
    }

    @RequestMapping(value = "/invitationdoc",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String invitationDoc(InvitationDocParam param, HttpServletResponse res) throws Exception {
        XWPFDocument doc = invitationToDoc(param);
        String title = param.getTitle();
        title = title.replaceAll("[/\\\\:*?<>|]","");
        File file = new File("E:/Desktop/invitationdoc/"+param.getId()+title+".doc");
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        doc.write(fileOutputStream);
        fileOutputStream.close();
        return "success";
        //exportDoc(doc,res,param.getId()+param.getTitle());
    }
    private void exportDoc(XWPFDocument doc, HttpServletResponse res, String fileName)throws Exception {
        res.reset();
        res.setContentType("application/msword;charset=utf-8");
        res.setHeader("Content-Disposition", "attachment;filename="
                + new String((fileName + ".doc").getBytes(), "iso-8859-1"));
        res.setHeader("Access-Control-Allow-Origin","*");
        res.setHeader("Access-Control-Allow-Methods","POST,GET");
        res.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        ServletOutputStream out = null;
        try {
            out= res.getOutputStream();
            doc.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out != null){
                out.close();
            }
        }



    }

    public XWPFDocument invitationToDoc(InvitationDocParam param) throws Exception {
        CustomXWPFDocument doc = new CustomXWPFDocument();
        String title = param.getTitle();
        String contentText = param.getContent();

        List<Map> contentList = JSON.parseObject(contentText, List.class);

        XWPFParagraph titleParagraph = doc.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setText(title);
        titleRun.setFontSize(20);
        titleRun.setBold(true);

        XWPFParagraph next = doc.createParagraph();
        XWPFRun nextRun = next.createRun();
        nextRun.setText("\r");

        for (Map map : contentList
                ) {
            String type = (String) map.get("type");
            XWPFParagraph contentParagraph = doc.createParagraph();
            if ("text".equalsIgnoreCase(type.trim())) {

                XWPFRun contentRun = contentParagraph.createRun();
                contentRun.setText((String) map.get("content"));
                contentRun.setFontSize(16);
            } else {

                String picUrl = (String) map.get("picUrl");
                if(!picUrl.contains("http")){
                    picUrl = "http://"+picUrl;
                }
                byte[] picBytes = HttpRequest.getImgData(picUrl);
                //InputStream inputStream = HttpRequest.getImgDataInputStream(picUrl);
                BufferedImage image = null;
                try {
                    image = ImageIO.read(new ByteArrayInputStream(picBytes));
                } catch (Exception e) {
                    e.printStackTrace();

                }

               /* ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(image,"jpg",out);*/
                //byte[] picBytes = out.toByteArray();
                //int imageType = image.getType();
                int width = 450;
                int height = 450;
                if (image != null) {
                    width = image.getWidth();
                    height = image.getHeight();
                }

                Double dw = (double) width;
                Double dh = (double) height;
                Double dnh = 450 * (dh / dw);
                DecimalFormat df = new DecimalFormat("######0");
                int newHeight = Integer.parseInt(df.format(dnh));
                doc.addPictureData(picBytes, Document.PICTURE_TYPE_JPEG);
                doc.createPicture(contentParagraph, doc.getAllPictures().size() - 1, 450, newHeight, " ");

                //ImageIO.write(image,"jpg",new File("E:/Desktop/goods"+doc.getAllPictures().size()+".jpg"));
                contentParagraph.setAlignment(ParagraphAlignment.CENTER);
                contentParagraph.setVerticalAlignment(TextAlignment.CENTER);

                XWPFParagraph linkParagraph = doc.createParagraph();
                String itemLink = "";
                if (map.get("itemNumIid") != null) {
                    itemLink = "https://detail.tmall.com/item.htm?id=" + ((String) map.get("itemNumIid")).trim();
                    String id = linkParagraph
                            .getDocument()
                            .getPackagePart()
                            .addExternalRelationship(itemLink,
                                    XWPFRelation.HYPERLINK.getRelation()).getId();
                    // Append the link and bind it to the relationship
                    CTHyperlink cLink = linkParagraph.getCTP().addNewHyperlink();
                    cLink.setId(id);

                    // Create the linked text
                    CTText ctText = CTText.Factory.newInstance();
                    ctText.setStringValue(itemLink);
                    CTR ctr = CTR.Factory.newInstance();
                    CTRPr rpr = ctr.addNewRPr();

                    //设置超链接样式
                    CTColor color = CTColor.Factory.newInstance();
                    color.setVal("0000FF");
                    rpr.setColor(color);
                    rpr.addNewU().setVal(STUnderline.SINGLE);

                    //设置字体
                    CTFonts fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
                    fonts.setAscii("微软雅黑");
                    fonts.setEastAsia("微软雅黑");
                    fonts.setHAnsi("微软雅黑");

                    //设置字体大小
                    CTHpsMeasure sz = rpr.isSetSz() ? rpr.getSz() : rpr.addNewSz();
                    sz.setVal(new BigInteger("24"));

                    ctr.setTArray(new CTText[]{ctText});
                    // Insert the linked text into the link
                    cLink.setRArray(new CTR[]{ctr});

                    //设置段落居中
                    linkParagraph.setAlignment(ParagraphAlignment.CENTER);
                    linkParagraph.setVerticalAlignment(TextAlignment.CENTER);
                }

            }
        }
        return doc;
    }

    private void exportExcel(Workbook wb, HttpServletResponse res, String fileName) throws Exception{

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        res.reset();
        res.setContentType("application/vnd.ms-excel;charset=utf-8");
        res.setHeader("Content-Disposition", "attachment;filename="
                + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        ServletOutputStream out = res.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if(bis != null){
                bis.close();
            }

            if(bos != null){
                bos.close();
            }

        }
    }
}
