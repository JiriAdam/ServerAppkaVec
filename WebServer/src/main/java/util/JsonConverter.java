package util;

import flexjson.JSONSerializer;
import web.model.map.MapDataFO;

import java.util.Collection;

/**
 * Created by Irrielde on 30.3.2015.
 */
public class JsonConverter {


    public static String toJsonArray(Collection collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }

    public static String mapDataToJson(MapDataFO object) {

        JSONSerializer serializer = new JSONSerializer();
        String json = "{}";
        try{
            json = serializer.include("events").exclude("*.class").serialize(object);
        }catch(Exception ex){
            System.err.println(ex.toString());
        }


        return json;
     //   return new JSONSerializer().exclude("*.class").serialize(object);
    }

}
