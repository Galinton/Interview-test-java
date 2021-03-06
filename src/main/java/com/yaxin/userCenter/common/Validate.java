package com.yaxin.userCenter.common;

import net.sf.json.JSONObject;

import java.util.Collection;
import java.util.Map;

public class Validate {

    /**
     * 现在支持对象null值判断，String的空内容判断，Integer为0判断
     *
     * @param objects
     * @return
     */
    public static boolean batchCheckParameter(Object... objects) throws Exception {
        for (Object object : objects) {
            if (checkObject(object))
                return true;
            if (object instanceof String) {
                return checkParameter((String) object);
            } else if (object instanceof Integer) {
                return checkParameter((Integer) object);
            } else {
                throw new Exception("不支持的类型:" + object.getClass().getSimpleName() + "类型" + object.getClass().getTypeName());
            }
        }
        return false;
    }

    /**
     * 判断 keys中的参数是否都在map中
     *
     * @param param
     * @param keys
     * @return
     */
    public static void existByJson(JSONObject param, String... keys) throws Exception {
        if (Validate.checkObject(param) || Validate.checkObject(keys))
            throw new Exception("传入的参数有为空.请检查传入的参数. " + param.toString());
        for (String key : keys) {
            if (!param.containsKey(key))
                throw new Exception("传入的参数有误.请检查传入的参数. " + param.toString());
        }
    }


    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static boolean checkObject(Object obj) {
        return obj == null ? true : false;
    }

    /**
     * 校验字符串，判断是否 == null 或者 equest("")
     *
     * @param str
     * @return
     */
    public static boolean checkParameter(Object str) {
        return str == null || "".equals(str) ? true : false;
    }

    /**
     * 校验Integer，判断是否 == 0
     *
     * @param integer
     * @return
     */
    public static boolean checkParameter(Integer integer) {
        return integer == 0 ? true : false;
    }

    /**
     * 判断集合是否为空，或者长度小于1
     * @param collection
     * @return
     */
    public static boolean checkParameter(Collection collection){
        return collection == null || collection.size()<1 ? true : false;
    }
}
