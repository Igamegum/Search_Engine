package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/19.
 */
public class RegexUtil {

    public static String getFirstString(String dealStr,String regexStr,int n)
    {
        if(dealStr == null || regexStr ==null || n < 1) return "";
        Pattern pattern = Pattern.compile(regexStr,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

        Matcher matcher = pattern.matcher(dealStr);
        while(matcher.find()){
            return matcher.group(n).trim();
        }
        return "";
    }

    public static List<String> getList(String dealStr,String regexStr,int n)
    {
        List<String> list =new ArrayList<String>();
        if(dealStr == null || regexStr ==null || n < 1) return list;

        Pattern pattern = Pattern.compile(regexStr,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

        Matcher matcher = pattern.matcher(dealStr);
        while(matcher.find()){
            list.add( matcher.group(n).trim());
        }

        return list;
    }

    public static List<String[]> getList(String dealStr,String regexStr,int[] array)
    {
        List<String[]> list = new ArrayList<String[]>();
        if(dealStr == null || regexStr == null || array ==null) return list;

        for(int i=0;i<array.length;i++){
            if(array[i]<1) return list;
        }

        Pattern pattern = Pattern.compile(regexStr,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

        Matcher matcher = pattern.matcher(dealStr);
        while(matcher.find()){
           String []ss = new String[array.length];

            for(int i=0;i<array.length;i++){
                ss[i] = matcher.group(array[i]).trim();
            }
            list.add(ss);
        }

        return list;
    }

    public static  void main(String []args)
    {
        String dealStr = "asdas123dev";
        String regexStr = "a(.*?)a";
        System.out.println(RegexUtil.getFirstString(dealStr,regexStr,1));
    }
}
