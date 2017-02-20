package SchoolNews;

import WebRobot.PageListBase;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/20.
 */
public class NewsList extends PageListBase{

    private static HashMap<String,String> params;
/*
    static {
        params = new HashMap<String, String>();
        params.put("Referer", "http://book.zongheng.com");
       // params.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");
        params.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        params.put("Host", "book.zongheng.com");
    }
*/
    static {
        params = new HashMap<String, String>();
        params.put("Referer", "http://www.zhbit.com/");
        // params.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");
        params.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        params.put("Host", "www.zhbit.com");
    }

    public NewsList(String pageUrl) {
        super(pageUrl, "utf-8",params);
    }


    @Override
    public String getUrlRegexStr() {
        //return "<a class=\"fs14\"\n" +
            //    "\t\t\t\t\t\t\t\thref=\"(.*?)\"";

        //return "<a class=\"fs14\" href=\"(.*?)\"";
       return "<a href=\"(.*?)\"";
    }

    @Override
    public int getUrlRegexStrNum() {
        return 1;
    }

    public  static  void main(String[] args)
    {
        //NewsList   newsList = new NewsList("http://book.zongheng.com/store/c0/c0/b9/u0/p1/v0/s9/t0/ALL.html");
        NewsList   newsList = new NewsList("http://www.zhbit.com/meitiguanzhu/");

        for(String  s: newsList.getPageUrl())
        {
            System.out.println(s);
        }

    }

}
