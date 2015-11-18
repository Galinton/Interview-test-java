package com.yaxin.userCenter.common;

import com.yaxin.userCenter.service.BaseService;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.yaxin.userCenter.service.BaseService.*;

/**
 * Created by wang on 15/11/18.
 */
public class ParamWarper {
    private final Map<String, Object> datas = new HashMap<String, Object>();

    private ParamWarper() {
    }

    public static ParamWarper getInstence() {
        return new ParamWarper();
    }

    public ParamWarper warp(String key, Object value) {
        datas.put(key, value);
        return this;
    }

    public JSONObject toJson() {
        return JSONObject.fromObject(datas);
    }

}
