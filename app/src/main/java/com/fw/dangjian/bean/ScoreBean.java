package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/4/16.
 */

public class ScoreBean {


    /**
     * result : {"square_region":"北京市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","score":0,"times":"66","square_name":"党建知识竞赛试题库","totleCount":10,"count":0,"id":2,"state":1,"square_time":10}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * square_region : 北京市
         * square_author : 马宝晶
         * square_photo : /upload/image/test.jpg
         * score : 0
         * times : 66
         * square_name : 党建知识竞赛试题库
         * totleCount : 10
         * count : 0
         * id : 2
         * state : 1
         * square_time : 10
         */

        public String square_region;
        public String square_author;
        public String square_photo;
        public int score;
        public String times;
        public String square_name;
        public int totleCount;
        public int count;
        public int id;
        public int state;
        public int square_time;
    }
}
