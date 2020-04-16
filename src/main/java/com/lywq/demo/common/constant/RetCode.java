package com.lywq.demo.common.constant;

/**
 * @description: 响应码枚举，参考HTTP状态码的语义
 * @author: lywq WED
 * @time: 2019/11/5 21:20
 */
public enum RetCode {

    // 成功
    SUCCESS(200),

    // 失败
    FAIL(400),

    // 未认证（签名错误）
    UNAUTHORIZED(401),

    /**
     * 未登录
     */
    UNAUTHEN(4401),

    /**
     * 未授权，拒绝访问
     */
    UNAUTHZ(4403),

    // 接口不存在
    NOT_FOUND(404),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;

    RetCode(int code) {
        this.code = code;
    }
}
