package com.xyf.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author 木木
 * @date 2018/8/30 13:37
 * @description
 */
public class SMSUtils {
    private static final Logger log = LoggerFactory.getLogger(SMSUtils.class);

    private static final String username = "zfxm1";
    private static final String psd = "fangu888";
    private static final String suffix = "，将在五分钟内失效，如非本人操作请忽略此短信，泄露验证码有资金被盗风险，请妥善保管！";
    private static final String prefix = "【鑫银付】您的验证码为：";


    /*
     * 发送方法信息
     */
    public static int sendSMS(String Mobile, String Content, String send_time) {
        URL url = null;
        int inputLine = 0;
        BufferedReader in = null;
        try {
            Content = prefix+Content+suffix;
            String send_content = URLEncoder.encode(Content.replaceAll("<br/>", " "), "GBK");//发送内容
            url = new URL("  http://47.104.193.175/ws/Send.aspx?CorpID=" + username + "&Pwd=" + psd +
                    "&Mobile=" + Mobile + "&Content=" + send_content + "&Cell=&SendTime=" + send_time);
            log.info("开始发送短信手机号码为 ：" + Mobile);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = new Integer(in.readLine()).intValue();
        } catch (Exception e) {
            log.error("网络异常,发送短信失败！");
            inputLine = -2;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }

        log.info("结束发送短信返回值：  " + inputLine);
        return inputLine;
    }

    /**
     * 随机生成6位随机验证码
     */
    public static String createRandomVcode() {
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }

}
