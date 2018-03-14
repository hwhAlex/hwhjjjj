package com.common;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by honor on 2018/1/15.
 */
public class SFHttpClient {




    public static HttpClient getHttpClient(int port) {
        PoolingClientConnectionManager pcm = new PoolingClientConnectionManager();
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            X509TrustManager x509 = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string)
                        throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string)
                        throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{x509}, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme sch = new Scheme("https", port, ssf);
        pcm.getSchemeRegistry().register(sch);
        return new DefaultHttpClient(pcm);
    }

    public static String doPost(String url,Map param) {


        List<NameValuePair> nvps = new ArrayList<>();
        Iterator entries = param.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            nvps.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));

        }


        HttpClient httpclient = getHttpClient(443);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            String resultString = EntityUtils.toString(response.getEntity());
            return resultString;
        } catch (IOException e) {
            return "0";
        }



    }


    public static Map<String, String> doOrderService(String xmlString) throws Exception {
        Map<String, String> resultMap = new HashMap<String, String>();
        int port = 443;
        String checkword = "j8DzkIFgmlomPt0aLuwU";//"PAYHZH    Hi9go87nCbFEuRWC";
        String url = "https://bsp-ois.sit.sf-express.com:9443/bsp-ois/sfexpressService";
        String verifyCode = md5EncryptAndBase64(xmlString + checkword);

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("xml", xmlString));
        nvps.add(new BasicNameValuePair("verifyCode", verifyCode));

        HttpClient httpclient = getHttpClient(port);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        HttpResponse response = httpclient.execute(httpPost);

        String resultXmlStr = "";
        if (response.getStatusLine().getStatusCode() == 200) {
            resultXmlStr = EntityUtils.toString(response.getEntity());
            System.out.println(resultXmlStr);
        } else {
            EntityUtils.consume(response.getEntity());
            throw new RuntimeException("response status error: " + response.getStatusLine().getStatusCode());
        }

        return resultMap;
    }


    public static String md5EncryptAndBase64(String str) {
        return encodeBase64(md5Encrypt(str));
    }

    private static byte[] md5Encrypt(String encryptStr) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(encryptStr.getBytes("utf8"));
            return md5.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String encodeBase64(byte[] b) {
        BASE64Encoder base64Encode = new BASE64Encoder();
        String str = base64Encode.encode(b);
        return str;
    }



}
