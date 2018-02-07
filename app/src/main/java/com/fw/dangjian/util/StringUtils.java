package com.fw.dangjian.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by @战士 on 2017/5/17
 * 邮箱752721794@qq.com.
 */

public class StringUtils {

    /**
     * 判断手机号
     *
     * @param mobiles 手机号码
     * @return boolean
     */
    public static boolean isMobileNo(String mobiles) {
            /*
            b.号段：13[0-9]、14[5,7,9]、15[012356789]、17[0,1,3,5,6,7,8]、 18[0-9]
		    */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (mobiles.length() == 11) {
            return mobiles.matches(telRegex);
        }
        return false;

    }

    /**
     * 判断密码
     *
     * @param psw
     * @return
     */
    //以前的  ^[A-Za-z0-9]+$
    public static boolean isPassword(String psw) {
        Pattern p = Pattern.compile("^[A-Za-z0-9]{6,18}$");
        Matcher m = p.matcher(psw);
        return m.matches();
    }
    /**
     * 身份证正则表达判断
     */
    public static boolean isCard(String string) {
        Pattern pattern = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * 判断姓名
     *
     * @param name
     * @return
     */
    public static boolean isName(String name) {
        Pattern p = Pattern
                .compile("^([\u4E00-\u9FA5]{1,20}|[a-zA-Z]{1,10})$");
        Matcher m = p.matcher(name);
        return m.matches();
    }


    /**
     * @param plain 明文
     * @return 32位小写密文
     */
    public static String encryption(String plain) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plain.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public static String encryption2(String s) {
        String re1_md5 = encryption(s);
        String re2_md5 = encryption(re1_md5);

        return re2_md5;
    }

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim()) && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 将long类型的转化为当前时间
     *
     * @param l
     * @return
     */
    public static String getDataFromLong(long l) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date(l));
        return format;
    }

    public static String getDataFromLong1(long l) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String format = "zhiyuan-test/"+sdf.format(new Date(l)) + l+".png";
        return format;
    }

}
