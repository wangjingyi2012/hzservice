package com.wjy.hz.helper;

import java.util.regex.Pattern;

public class IPHelper {

    // IPv4的正则表达式
    private static final String IPV4_REGEX = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$";
    // IPv6的正则表达式
    private static final String IPV6_REGEX = "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$";

    private static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    private static final Pattern IPV6_PATTERN = Pattern.compile(IPV6_REGEX);

    /**
     * 验证IP地址格式。
     *
     * @param ip 需要验证的IP地址。
     * @return 如果IP地址格式正确，返回"格式正确"；如果格式有误，返回"IP格式有误"。
     */
    public static String getIPType(String ip) {
        if (IPV4_PATTERN.matcher(ip).matches()) {
            return "IPv4";
        } else if (IPV6_PATTERN.matcher(ip).matches()) {
            return "IPv6";
        } else {
            return "无效";
        }
    }


}
