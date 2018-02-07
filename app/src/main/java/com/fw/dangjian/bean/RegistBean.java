package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/2/7.
 */

public class RegistBean {
    /**
     * result : {"managerid":10,"account":"15538293786","lastlogintime":1517539956000,"issuper":false}
     * result_msg : 登录成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * managerid : 10
         * account : 15538293786
         * lastlogintime : 1517539956000
         * issuper : false
         */

        public int managerid;
        public String account;
        public long lastlogintime;
        public boolean issuper;
    }
}
