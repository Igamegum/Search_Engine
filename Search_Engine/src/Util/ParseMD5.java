package Util;



/**
 * Created by Administrator on 2017/2/19.
 */
public class ParseMD5 extends Encrypt{

    public static String parseStrtoMD5(String str)
    {
        return encrypt(str,MD5);
    }
    public static String parseStrToUpperMD5(String str)
    {
        return parseStrtoMD5(str).toUpperCase();
    }
    public static void main(String []args)
    {
        System.out.println(ParseMD5.parseStrtoMD5("abc"));
    }
}
