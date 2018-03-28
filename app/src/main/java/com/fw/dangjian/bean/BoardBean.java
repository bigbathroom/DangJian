package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/3/28.
 */

public class BoardBean {

    /**
     * result : {"todayInfoCount":"13","memberCount":"35","committeeCount":"15","branchCount":"2","totleInfoCount":"495"}
     * reason : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String reason;
    public String result_code;

    public static class ResultBean {
        /**
         * todayInfoCount : 13
         * memberCount : 35
         * committeeCount : 15
         * branchCount : 2
         * totleInfoCount : 495
         */

        public String todayInfoCount;
        public String memberCount;
        public String committeeCount;
        public String branchCount;
        public String totleInfoCount;
    }
}
