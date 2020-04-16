package com.lywq.demo.common.modle;

import com.lywq.demo.common.constant.RetCode;

/**
 * @description: 将结果转换为封装后的对象
 * @author: lywq WED
 * @time: 2019/11/5 21:26
 */
public class RetResponse {

    private final static String SUCCESS = "success";

    private final static String FAIL = "fail";

    public static <T> RetResult<T> makeOKRsp() {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
    }

    public static <T> RetResult<T> makeOKRsp(T data) {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> RetResult<T> makeErrRsp(T data) {
        return new RetResult<T>().setCode(RetCode.FAIL).setMsg(FAIL).setData(data);
    }

    public static <T> RetResult<T> makeRsp(int code, String msg) {
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> RetResult<T> makeRsp(int code, String msg, T data) {
        return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}
