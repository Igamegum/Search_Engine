package WebRobot;

import Util.RegexUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */
public abstract class PageListBase extends SpiderBase{
    private String pageUrl;

    public  PageListBase(String pageUrl,String charsetName)
    {
        readPageByGet(pageUrl,null,charsetName);
        this.pageUrl = pageUrl;
    }

    public PageListBase(String pageUrl, String charsetName, HashMap<String,String> params)
    {
        readPageByGet(pageUrl,params,charsetName);
        this.pageUrl = pageUrl;

    }

    public abstract String getUrlRegexStr();

    public abstract int getUrlRegexStrNum();

    public List<String> getPageUrl()
    {
        return RegexUtil.getArrayList(getPageSourceCode(),getUrlRegexStr(),this.pageUrl,getUrlRegexStrNum());
    }

}
