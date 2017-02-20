package Util;

import org.dom4j.DocumentHelper;
import org.dom4j.jaxb.JAXBModifier;

import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by Administrator on 2017/2/19.
 */
public class XmlUtil {

    private  static final String noResult = "<result> no result </result>";

    public static org.dom4j.Document createFromString(String xml)
    {
        try{
            return DocumentHelper.parseText(xml);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String parseObject2XmlString(Object object)
    {
        if(object == null) return noResult;
        StringWriter sw = new StringWriter();
        JAXBContext jaxbContext;
        Marshaller  marshaller;
        try{
            jaxbContext = JAXBContext.newInstance(object.getClass());
            marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(object,sw);
            return sw.toString();
        }catch(Exception e){
            e.printStackTrace();
            return noResult;
        }
    }
}
