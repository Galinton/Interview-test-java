package com.yaxin.userCenter.service;

import com.yaxin.userCenter.common.Validate;
import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * service 通用父类，用于记录一些通用的信息和通用的业务处理,比如logger日志打印，运行时间监控，异常处理等。
 * 本例只做了一个抽象父类
 */
public abstract class BaseService {

    private static Logger logger = LogManager.getLogger(BaseService.class);

    /**
     * 外部调用执行接口
     *
     * @param req 使用json格式和web层进行通讯，而不使用通用的bean模式，
     *            主要是为了使web层和service层强行分离，强迫开发人员在开发过程中使用接口方式进行开发。
     * @return
     */
    public Result execute(String req) {
        long startTime = System.currentTimeMillis();
        Result resp = null;
        try {
            if (Validate.checkParameter(req))
                throw new Exception("传入参数为空。");
            Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(req), Map.class);
            validate(map);
            resp = handle(req);
        } catch (Exception e) {
            resp = new Result().error( e.getMessage());  //可以在这做通用异常处理，比如监控报警什么的。本例之做了简单的示例
        }
        long stopTime = System.currentTimeMillis();
        logger.debug("service运行时间:" + String.valueOf(stopTime - startTime));
        return resp;
    }

    /**
     * 参数校验
     *
     * @param map
     */
    protected abstract void validate(Map<String, Object>  map) throws Exception;

    /**
     * 业务处理
     *
     * @param req
     * @return
     * @throws Exception
     */
    protected abstract Result handle(String req) throws Exception;

    //通用业务层结果包装类
    public class Result {
        //返回结果
        public String data;
        //状态 -1 系统异常 ；0 业务错误 ；1 成功
        public int state;
        //异常信息提示
        public String message;
        public Result() {
        }
        public Result(String data) {
            this.data = data;
        }
        public Result succeeded(String message){
            this.state = 1;
            this.message = message;
            return this;
        }
        public Result failed(String message){
            this.state = 0;
            this.message = message;
            return this;
        }
        public Result error(String message){
            this.state = -1;
            this.message = message;
            return this;
        }
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
