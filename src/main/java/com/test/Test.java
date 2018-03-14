package com.test;


import com.service.AllContentService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


/**
 * Created by honor on 2017/10/26.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"classpath*:spring.xml","classpath*:spring-mybatis.xml"});
        AllContentService allContentService = ac.getBean(AllContentService.class);
        /*for (int i = 0; i <100 ; i++) {
            allContentService.sendAllContent();
        }*/
        Integer id = 0;
        while (true){
            List<Integer> idList = allContentService.sendAllContent(id);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){

            }
            if(idList.size() == 0){
                break;
            }
            id = idList.get(idList.size()-1);
        }

        //ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"classpath*:spring.xml","classpath*:spring-mybatis.xml"});
       /* SaveData20171018Service saveData20171018Service = ac.getBean(SaveData20171018Service.class);
        List<SaveData20171018> list =  saveData20171018Service.getAll();
        for (SaveData20171018 data:list
             ) {
            System.out.println(data.getLink());
        }*/
        //InvitationService invitationService = ac.getBean(InvitationService.class);
        //invitationService.addOldInvitation();
        //invitationService.addOAId();
       /* String xml = "<Request service='OrderService' lang='zh-CN'> <Head>BSPdevelop</Head> <Body> <Order orderid ='XJFS_07116033' j_company='华为' j_contact='任先生' j_tel='010-1111112' j_mobile='13800138000' j_province='北京' j_city='北京' j_county='朝阳区' j_address='北京市朝阳区科学园科健路338号' d_company='顺丰速运' d_contact='西门俊宇' d_tel='无' d_mobile='17002930913' d_province='广东省' d_city='深圳市' d_county='福田区' d_address='广东省深圳市福田区新洲十一街万基商务大厦10楼' express_type ='1' pay_method ='2' custid ='7551878519' parcel_quantity ='1' cargo_total_weight ='2.35' sendstarttime ='2015-01-16 10:26:43' order_source ='西门府' remark =''> <Cargo Name='扇子' count='2' unit='台' weight='0.02' amount='100' currency='CNY' source_area='中国'></Cargo> </Order> </Body></Request>";
        String checkword = "j8DzkIFgmlomPt0aLuwU";
        String verifyCode = xml+checkword;
        try {
            verifyCode = MD5Util.EncoderByMd5(verifyCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        String postUrl = "https://bsp-ois.sit.sf-express.com:9443/bsp-ois/sfexpressService";
        //String postUrl = "http://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";
        String param = "xml="+xml+"&verifyCode="+verifyCode;
        String result = HttpRequest.sendPost(postUrl,param);
        System.out.println(result);*/
    }
}
