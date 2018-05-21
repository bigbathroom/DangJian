package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/2/2.
 */

public class LoginBean {


    /**
     * result : {"managerid":10,"account":"15538293786","lastlogintime":"2018-04-13 05:07:19","issuper":false}
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
         * lastlogintime : 2018-04-13 05:07:19
         * issuper : false
         */

        public int managerid;
        public String account;
        public String lastlogintime;
        public boolean issuper;
        public int phone;
    }
}
