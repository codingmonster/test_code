package testtttt;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class Post {

    private static final String borui_uri = "http://lecanbo.sifude.com/market/query";

    // appid和key分别对应工程师爸爸POST请求中的app_id和secret参数
    private static final String appid = "pi2e000000000001";
    private static final String key = "sknI3O4YUXcCZP1hEe6wx9ydVKMJaLNT";
    
    public HttpEntity QueryIdaddy(String keyword) {
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setIntParameter("http.socket.timeout", 60000);
        
        String url = "http://open.idaddy.cn/audio/v2/query?";
        HttpPost httppost = new HttpPost(url);

        long timestamp = Calendar.getInstance().getTimeInMillis() / 1000;
        
        ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("app_id", appid));
        list.add(new BasicNameValuePair("device_id", "xxx"));
        // list.add(new BasicNameValuePair("app_uid", ""));
        list.add(new BasicNameValuePair("timestamp", String.valueOf(timestamp)));
        list.add(new BasicNameValuePair("nonce", create_nonce_str()));
        list.add(new BasicNameValuePair("signature", GenSignature(timestamp)));
        list.add(new BasicNameValuePair("content", keyword));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        
        try {
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    try {
                        InputStream is = httpEntity.getContent();
                        if (is != null)
                            is.close();
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                }
                Header ceheader = httpEntity.getContentEncoding();
                if (ceheader != null) {
                    HeaderElement[] codecs = ceheader.getElements();
                    for (int i = 0; i < codecs.length; i++) {
                        if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                            return new GzipDecompressingEntity(httpEntity);

                        }
                    }
                }
            }
            return httpEntity;
        } catch (Throwable t) {
        	
        }
        return null;
    	
    }

    // POST方法中，body里面需要signature参数。
    // sha1（app_id+secret+timestamp）
    private static String GenSignature(long timestamp) {
        String signature = "";

        String code = appid + key + timestamp;
        signature = DigestUtils.shaHex(code);
        return signature;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    
	public HttpEntity queryStoreApi(String keyword) {
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setIntParameter("http.socket.timeout", 60000);
        
        HttpPost httppost = new HttpPost(borui_uri);

        ArrayList<NameValuePair> httpparams = new ArrayList<NameValuePair>();
        httpparams.add(new BasicNameValuePair("type", "app"));
        httpparams.add(new BasicNameValuePair("word", keyword));
        httpparams.add(new BasicNameValuePair("bdi_imei", "aaaa"));
        httpparams.add(new BasicNameValuePair("bdi_loc", "北京"));
        httpparams.add(new BasicNameValuePair("bdi_bear", "WF"));
        httpparams.add(new BasicNameValuePair("resolution", "720_1280"));
        // httpparams.add(new BasicNameValuePair("dpi", "320"));
        httpparams.add(new BasicNameValuePair("apilevel", "18"));
        httpparams.add(new BasicNameValuePair("os_version", "4.3"));
        httpparams.add(new BasicNameValuePair("brand", "OPSSON"));
        httpparams.add(new BasicNameValuePair("model", "Q1"));
        httpparams.add(new BasicNameValuePair("pver", "3"));
        httpparams.add(new BasicNameValuePair("uid", "3766746263f03d994f8a"));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(httpparams, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        
        try {
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    try {
                        InputStream is = httpEntity.getContent();
                        if (is != null)
                            is.close();
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                }
                Header ceheader = httpEntity.getContentEncoding();
                if (ceheader != null) {
                    HeaderElement[] codecs = ceheader.getElements();
                    for (int i = 0; i < codecs.length; i++) {
                        if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                            return new GzipDecompressingEntity(httpEntity);

                        }
                    }
                }
            }
            return httpEntity;
        } catch (Throwable t) {
        	
        }
        return null;
    }
	
	public String EntityToString(HttpEntity entity) {
		if (entity == null)
            return "";
        try {
            return EntityUtils.toString(entity, HTTP.UTF_8);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return "";
        }
	}

	public static void main(String[] args) {
		Post post = new Post();
		// HttpEntity entity = post.queryStoreApi("美图");
		HttpEntity entity = post.QueryIdaddy("美图");
		String result = post.EntityToString(entity);
		System.out.println(result);
	}
}
