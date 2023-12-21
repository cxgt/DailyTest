package com.example.dailytest.demos.Utils;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/21 16:06
 */

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.util.StringUtils;

public class MapUtil {
    public MapUtil() {
    }

    public static boolean checkItemType(Map map, Class c) {
        boolean fix = false;
        Iterator<String> it = map.keySet().iterator();

        while(it.hasNext()) {
            String key = (String)it.next();
            Object value = map.get(key);
            if (value != null && !c.isAssignableFrom(value.getClass())) {
                fix = true;
                break;
            }
        }

        return fix;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() <= 0;
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static String getString(Map map, String key, String defaultValue) {
        if (map != null && map.size() > 0 && map.get(key) != null) {
            String tmp = map.get(key).toString();
            return !StringUtils.isEmpty(tmp) ? tmp : defaultValue;
        } else {
            return defaultValue;
        }
    }

    public static List getList(Map map, String key) {
        return null;
    }

    public static String getString(Map map, String key) {
        return getString(map, key, "");
    }
}
