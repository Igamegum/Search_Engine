package Util;

/**
 * Created by Administrator on 2017/2/19.
 */
public class ClassUtil  {

    public static String getClassPath(Class<?> c)
    {
        return c.getResource("").getPath().replace("%20"," ");
    }

    public static String getClassRootPath(Class<?> c)
    {
        return c.getResource("/").getPath().replace("%20"," ");
    }
}
