package WebRobot;


import Util.CharsetUtil;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/16.
 */
public class Spider {

    private static Logger log = Logger.getLogger(Spider.class);

    //网页源代码
    private String pageSourceCode = "";

    //返回头信息
    private Header[] reponseHeaders = null;

    private static int connectTimeOut = 10000;

    private static int readTimeOut = 10000;

    private static int maxConnectTimes = 3;

    private static String charsetName = "utf-8";

    //将HttpClient委托给MultiThreadedHttpConnectionManager，支持多线程
    private static MultiThreadedHttpConnectionManager httpConnectManager = new MultiThreadedHttpConnectionManager();
    private static HttpClient httpClient = new HttpClient(httpConnectManager);

    static {
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectTimeOut);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(readTimeOut);
        //设置请求的编码格式
        httpClient.getParams().setContentCharset("utf-8");
    }

    public boolean readPageByGet(String urlStr, HashMap<String, String> params, String charsetName) {
        GetMethod method = createGetMethod(urlStr, params);
        return  readPage(method, charsetName, urlStr);
    }

    public boolean readPageByPost(String urlStr, HashMap<String, String> params, String charsetName) {
        PostMethod method = createPostMethod(urlStr, params);
        return  readPage(method, charsetName, urlStr);
    }


    private boolean readPage(HttpMethod method, String defaultCharset, String urlStr) {
        int n = maxConnectTimes;
        while (n > 0) {
            try {
                //判断返回状态是否是200
                if (httpClient.executeMethod(method) != HttpStatus.SC_OK) {
                    log.info("can`t connect " + urlStr + (maxConnectTimes - n + 1));
                    n--;
                } else {
                    //获取头信息
                    reponseHeaders = method.getRequestHeaders();
                    //获取服务器的输出流
                    InputStream inputStream = method.getResponseBodyAsStream();
                    BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream, charsetName));
                    StringBuffer stringBuffer = new StringBuffer();
                    String lineString = "";
                    while ((lineString = bufferReader.readLine()) != null) {
                        stringBuffer.append(lineString);
                        stringBuffer.append("\n");
                    }
                    pageSourceCode = stringBuffer.toString();
                    //检测流的编码方式
                    InputStream in = new ByteArrayInputStream(pageSourceCode.getBytes(charsetName));
                    String charset = CharsetUtil.getStringCharset(in, defaultCharset);
                    //如果编码方式不同，则进行转码操作
                    if (!charsetName.toLowerCase().equals(charset.toLowerCase())) {
                        pageSourceCode = new String(pageSourceCode.getBytes(charsetName), charset);
                    }
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.error(urlStr + "can`t connect " + (maxConnectTimes - n + 1));
                n--;
            }
        }
        return false;
    }

    private GetMethod createGetMethod(String urlStr, HashMap<String, String> params){
        GetMethod method = new GetMethod(urlStr);
        if (params == null) {
            return method;
        }
        Iterator<Map.Entry<String, String>> itor = params.entrySet().iterator();
        while (itor.hasNext()) {
            Map.Entry entry = (Map.Entry) itor.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            method.setRequestHeader(key, val);
        }
        return method;
    }

    private PostMethod createPostMethod(String urlStr, HashMap<String, String> params) {
        PostMethod method = new PostMethod(urlStr);
        if (params == null) {
            return method;
        }
        Iterator<Map.Entry<String, String>> itor = params.entrySet().iterator();
        while (itor.hasNext()) {
            Map.Entry entry = (Map.Entry) itor.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            method.setRequestHeader(key, val);
        }
        return method;
    }


    /**
     * @return String
     * @Author: lulei
     * @Description: 获取网页源代码
     */
    public String getPageSourceCode(){
        return pageSourceCode;
    }

    /**
     * @return Header[]
     * @Author: lulei
     * @Description: 获取网页返回头信息
     */
    public Header[] getHeader(){
        return reponseHeaders;
    }


    public void setConnectTimeOut(int timeOut){
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeOut);
        Spider.connectTimeOut = timeOut;
    }


    public void setReadTimeOut(int timeOut){
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeOut);
        Spider.readTimeOut = timeOut;
    }


    public static void setMaxConnectTimes(int maxConnectTimes) {
        Spider.maxConnectTimes = maxConnectTimes;
    }


    public void setTimeout(int connectTimeout, int readTimeout){
        setConnectTimeOut(connectTimeout);
        setReadTimeOut(readTimeout);
    }


    public static void setCharsetName(String charsetName) {
        Spider.charsetName = charsetName;
    }
    public static void main(String[] args) {

    }

}
