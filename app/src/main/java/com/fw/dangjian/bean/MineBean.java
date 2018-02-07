package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/1/17.
 */

public class MineBean {


    /**
     * result : {"managerid":1,"name":"管理员","photo":"http://112.126.73.158:80/images/","issuper":true}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * managerid : 1
         * name : 管理员
         * photo : http://112.126.73.158:80/images/
         * issuper : true
         */

        public int managerid;
        public String name;
        public String photo;
        public boolean issuper;
    }
}
