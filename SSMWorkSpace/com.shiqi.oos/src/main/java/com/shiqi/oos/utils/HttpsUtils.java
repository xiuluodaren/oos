package com.shiqi.oos.utils;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class HttpsUtils {

	private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;
    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", new PlainConnectionSocketFactory())
                    .register("https", sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getContent(String url,Map<String, String> params)
    {
		String respStr = null;  
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .build();
        
        try {  
        		if (params.entrySet().size() > 0) {
        			url += "?";
        			
        			for (Entry<String, String> param : params.entrySet()) {
						url = url + "&" + param.getKey() + "=" + param.getValue();
				}
        			
			}
        		
        		HttpGet httpGet = new HttpGet(url);
        		
            CloseableHttpResponse response = httpclient.execute(httpGet);  
            try {  
                System.out.println(response.getStatusLine());  
                HttpEntity resEntity = response.getEntity();  
                respStr = EntityUtils.toString(response.getEntity(), "utf-8");  
                EntityUtils.consume(resEntity);  
            } finally {  
                response.close();  
            }  
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return respStr; 
        
	}
	
    public static String postContent(String url,Map<String, String> params)
    {
		String respStr = null;  
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .build();
        
        try {  
        		
        		HttpPost httpPost = new HttpPost(url);

        		if (params.entrySet().size() > 0) {
        			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        			
        			for (Entry<String, String> param : params.entrySet()) {
        				nvps.add(new BasicNameValuePair(param.getKey(), param.getValue()));
				}
        			
        			httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			}
        		
            CloseableHttpResponse response = httpclient.execute(httpPost);  
            try {  
                System.out.println(response.getStatusLine());  
                HttpEntity resEntity = response.getEntity();  
                respStr = EntityUtils.toString(response.getEntity(), "utf-8");  
                EntityUtils.consume(resEntity);  
            } finally {  
                response.close();  
            }  
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return respStr; 
        
	}
    
}
