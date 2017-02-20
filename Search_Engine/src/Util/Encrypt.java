package Util;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2017/2/19.
 */
public class Encrypt {

    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";

    public static String encrypt(String str,String  encName)
    {
        String resStr = null;
        try
        {

            MessageDigest digest = MessageDigest.getInstance(encName);
            byte[] bytes = digest.digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();

            for(byte b: bytes)
            {
                int bt = b&0xff;
                if(bt < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            resStr = stringBuffer.toString();
        }catch (Exception e){
            e.printStackTrace();;
        }

        return resStr;
    }

}
