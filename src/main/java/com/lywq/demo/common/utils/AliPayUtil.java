package com.lywq.demo.common.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lywq WED
 * @title: AliPayUtil
 * @projectName demo
 * @description: alipay工具类
 * @date 2019/12/9 13:50
 */
@Slf4j
@Configuration
@PropertySource("classpath:config/alipay.properties")
public class AliPayUtil {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID;

    // 商户私钥，您的PKCS8格式RSA2私钥，生成的应用私钥
    public static String APP_PRIVATE_KEY;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/appDaily.htm?tab=info 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url;

    // 签名方式
    public static String sign_type;

    // 字符编码格式
    public static String CHARSET;

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl;

    // 日志地址
    public static String log_path;

    // 实例化客户端
    public static AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, sign_type);

    @Resource
    private Environment env;

    @PostConstruct
    public void initParam() {
        APP_ID = env.getProperty("alipay.APP_ID");
        APP_PRIVATE_KEY = env.getProperty("alipay.APP_PRIVATE_KEY");
        ALIPAY_PUBLIC_KEY = env.getProperty("alipay.ALIPAY_PUBLIC_KEY");
        notify_url = env.getProperty("alipay.notify_url");
        return_url = env.getProperty("alipay.return_url");
        sign_type = env.getProperty("alipay.sign_type");
        CHARSET = env.getProperty("alipay.CHARSET");
        gatewayUrl = env.getProperty("alipay.gatewayUrl");
        log_path = env.getProperty("alipay.log_path");
    }

    /**
     * 转码
     *
     * @param param
     * @return
     */
    public static String getByte(String param) {
        try {
            return new String(param.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验签名
     *
     * @param request
     * @return
     */
    public static boolean rsaCheckV1(HttpServletRequest request) {
        // https://docs.open.alipay.com/54/106370
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, sign_type);
            return signVerified;
        } catch (AlipayApiException e) {
            log.debug("verify sigin error, exception is:{}", e);
            return false;
        }
    }

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
