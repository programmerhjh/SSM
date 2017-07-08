package tool;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by acer on 2017/6/27.
 */
public class JsonToMap {
    public static Map<String, Object> toHashMap(String str)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = (JSONObject) JSONObject.parse(str);
        Iterator key = jsonObject.keySet().iterator();
        Iterator value = jsonObject.values().iterator();
        // 遍历jsonObject数据，添加到Map对象
        while (key.hasNext() && value.hasNext())
        {
            String mapValue = String.valueOf(value.next());
            System.out.println(mapValue);
            String mapKey = String.valueOf(key.next());
            System.out.println(mapKey);
            data.put(mapKey, mapValue);
        }
        System.out.println(data.get("name"));
        return data;
    }
}
