package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/3/28.
 */

public class BoardBean {


    /**
     * result : {"todayInfoCount":"13","weekInfoCount":{"day6":"64","day7":"70","day4":"47","day5":"54","day2":"23","day3":"35","day1":"10"},"memberCount":"35","committeeCount":"15","branchCount":"2","totleInfoCount":"495"}
     * reason : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String reason;
    public String result_code;

    public static class ResultBean {
        /**
         * todayInfoCount : 13
         * weekInfoCount : {"day6":"64","day7":"70","day4":"47","day5":"54","day2":"23","day3":"35","day1":"10"}
         * memberCount : 35
         * committeeCount : 15
         * branchCount : 2
         * totleInfoCount : 495
         */

        public String todayInfoCount;
        public WeekInfoCountBean weekInfoCount;
        public String memberCount;
        public String committeeCount;
        public String branchCount;
        public String totleInfoCount;

        public static class WeekInfoCountBean {
            /**
             * day6 : 64
             * day7 : 70
             * day4 : 47
             * day5 : 54
             * day2 : 23
             * day3 : 35
             * day1 : 10
             */

            public String day6;
            public String day7;
            public String day4;
            public String day5;
            public String day2;
            public String day3;
            public String day1;
        }
    }
}
