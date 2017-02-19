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
 * Created by Administrator on 2017/2/19.
 */
public abstract class SpiderBase
{


    private static Logger log = Logger.getLogger(SpiderBase.class);
    private String pageSourceCode ="";
    private Header[] responseHeaders = null;
    private static int connectTimeOut =  10000;
    private static int readTimeOut =  10000;
    private static int maxConnectTimes = 3;
    private static String charsetName = "iso-8859-1";

    private static MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
    private static HttpClient httpClient = new HttpClient(httpConnectionManager);

    static{
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectTimeOut);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(connectTimeOut);
        httpClient.getParams().setContentCharset("utf-8");
    }

    public boolean readPageByPost(String urlStr, HashMap<String,String> params,String charsetName)
    {
        PostMethod method = createPostMethod(urlStr,params);
        return readPage(method,charsetName,urlStr);
    }
    public boolean readPageByGet(String urlStr, HashMap<String,String> params,String charsetName)
    {
        GetMethod method = createGetMethod(urlStr,params);
         return readPage(method,charsetName,urlStr);
    }

    private boolean readPage(HttpMethod method,String defaultCharset,   String usrStr)
    {
        int  n = maxConnectTimes;
        while( n<0)
        {
            try{

                if(httpClient.executeMethod(method) != HttpStatus.SC_OK)
                {
                    log.info("can not connect " + usrStr + (maxConnectTimes - n +1));
                    n--;
                }else
                {
                    responseHeaders = method.getRequestHeaders();
                    InputStream inputStream = method.getResponseBodyAsStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,charsetName));
                    StringBuffer stringBuffer = new StringBuffer();

                    String lineString = "";
                    while((lineString = bufferedReader.readLine()) !=null)
                    {
                        stringBuffer.append(lineString);
                        stringBuffer.append("\n");
                    }
                    pageSourceCode = stringBuffer.toString();
                    InputStream in = new ByteArrayInputStream(pageSourceCode.getBytes(charsetName));
                    String charset = CharsetUtil.getStringCharset(in,defaultCharset);
                    if(!(charsetName.toLowerCase().equals(charset.toLowerCase())))
                    {
                        pageSourceCode = new String(pageSourceCode.getBytes(charsetName), charset);
                    }
                    return true;
                }

            }catch (Exception e){
                e.printStackTrace();
                log.error((usrStr) + "can not connect" + (maxConnectTimes -n +1));
                n--;
            }
        }
        return false;
    }

    private GetMethod createGetMethod(String urlStr, HashMap<String,String> params)
    {
        GetMethod method = new GetMethod(urlStr);
        if(params ==null) return method;

        Iterator<Map.Entry<String,String>> iterator = params.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry entry =(Map.Entry)iterator.next();
            String key =(String) entry.getKey();
            String value = (String)entry.getValue();
            method.setRequestHeader(key,value);
        }

        return method;
    }

    private PostMethod createPostMethod(String urlStr, HashMap<String,String> params)
    {
        PostMethod method = new PostMethod(urlStr);
        if(params ==null) return method;

        Iterator<Map.Entry<String,String>> iterator = params.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry entry =(Map.Entry)iterator.next();
            String key =(String) entry.getKey();
            String value = (String)entry.getValue();
            method.setRequestHeader(key,value);
        }

        return method;
    }

    public static void main(String []args)
    {
        System.out.println("123");
    }




    public static void setHttpClient(HttpClient httpClient) {
        SpiderBase.httpClient = httpClient;
    }

    public void setPageSourceCode(String pageSourceCode) {
        this.pageSourceCode = pageSourceCode;
    }



    public static void setConnectTimeOut(int connectTimeOut) {
        SpiderBase.connectTimeOut = connectTimeOut;
    }

    public static void setReadTimeOut(int readTimeOut) {
        SpiderBase.readTimeOut = readTimeOut;
    }

    public static void setMaxConnectTimes(int maxConnectTimes) {
        SpiderBase.maxConnectTimes = maxConnectTimes;
    }

    public static void setCharsetName(String charsetName) {
        SpiderBase.charsetName = charsetName;
    }

    public static void setHttpConnectionManager(MultiThreadedHttpConnectionManager httpConnectionManager) {
        SpiderBase.httpConnectionManager = httpConnectionManager;
    }

    public static void setLog(Logger log) {
        SpiderBase.log = log;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public static Logger getLog() {
        return log;
    }

    public String getPageSourceCode() {
        return pageSourceCode;
    }


    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public static int getConnectTimeOut() {
        return connectTimeOut;
    }

    public static int getReadTimeOut() {
        return readTimeOut;
    }

    public static int getMaxConnectTimes() {
        return maxConnectTimes;
    }

    public static String getCharsetName() {
        return charsetName;
    }

    public static MultiThreadedHttpConnectionManager getHttpConnectionManager() {
        return httpConnectionManager;
    }

    public static HttpClient getHttpClient() {
        return httpClient;
    }

}

