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
     * @param param 使用json格式和web层进行通讯，而不使用通用的bean模式，
     *            主要是为了使web层和service层强行分离，强迫开发人员在开发过程中使用接口方式进行开发。
     * @return
     */
    public Result execute(JSONObject param) {
        long startTime = System.currentTimeMillis();
        Result result = null;
        try {
            if (Validate.checkParameter(param))
                throw new Exception("传入参数为空。");
            before(param);
            result = handle(param);
            after(param,result);
        } catch (Exception e) {
            logger.error("异常",e); //可以对异常进行分类处理，可以做日志格式统一化等等，暂不细做
            result = new Result().error(e.getMessage());  //可以在这做通用异常处理，比如监控报警什么的。本例之做了简单的示例
        }
        long stopTime = System.currentTimeMillis();
        logger.debug("service运行时间:" + String.valueOf(stopTime - startTime));
        return result;
    }

    /**
     * 方法执行前需要的操作
     *
     * @param param
     */
    protected  void before(JSONObject param) throws Exception{}

    /**
     * 方法执行后需要的操作
     *
     */
    protected  void after(JSONObject param,Result result) throws Exception{}

    /**
     * 业务处理
     *
     * @param param
     * @return
     * @throws Exception
     */
    protected abstract Result handle(JSONObject param) throws Exception;

    //通用业务层结果包装类
    public static class Result {
        //返回结果
        private JSONObject data;
        //状态 -1 系统异常 ；0 业务错误 ；1 成功
        private int state;
        //异常信息提示
        private String message;

        public Result() {
        }

        public Result(JSONObject data) {
            this.data = data;
        }

        public Result(Map data) {
            this.data = JSONObject.fromObject(data);
        }

        public Result succeeded(String message) {
            this.state = 1;
            this.message = message;
            return this;
        }

        public Result failed(String message) {
            this.state = 0;
            this.message = message;
            return this;
        }

        public Result error(String message) {
            this.state = -1;
            this.message = message;
            return this;
        }

        public JSONObject toJson() {
            JSONObject j = new JSONObject();
            j.put("message", message==null?"":message);
            j.put("state", state);
            if (data != null)
                j.put("data", data);
            return j;
        }

        public String toString(){
            return toJson().toString();
        }
    }
}
