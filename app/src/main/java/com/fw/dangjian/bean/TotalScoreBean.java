package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class TotalScoreBean {

    /**
     * result : [{"square_region":"北京市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","score":0,"times":null,"test_time":"2018-04-16 05:30:20","square_name":"《党章》题库","count":0,"id":1,"state":1,"square_time":5}]
     * result_msg : 请求成功！
     * result_code : 200
     */

    public String result_msg;
    public String result_code;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * square_region : 北京市
         * square_author : 马宝晶
         * square_photo : /upload/image/test.jpg
         * score : 0
         * times : null
         * test_time : 2018-04-16 05:30:20
         * square_name : 《党章》题库
         * count : 0
         * id : 1
         * state : 1
         * square_time : 5
         */

        public String square_region;
        public String square_author;
        public String square_photo;
        public int score;
        public String times;
        public String test_time;
        public String square_name;
        public int count;
        public int totleCount;
        public int id;
        public int state;
        public int square_time;
    }
}
