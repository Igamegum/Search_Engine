package Util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Administrator on 2017/2/19.
 */
public class JsonUtil {

    private static final String noData = "{\"result\" : null}";

    private static ObjectMapper mapper;

    static
    {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public  static  String parseJson(Object object)
    {
        if(object == null)
        {
            return noData;
        }

        try{
            return mapper.writeValueAsString(object);
        }catch(Exception    e){
            e.printStackTrace();
            return noData;
        }
    }

    public JsonNode json20Object (String json)
    {
        try{
            return mapper.readTree(json);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
