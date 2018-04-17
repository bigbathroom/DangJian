package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

public class ActionBean {

    /**
     * result : {"pageNum":1,"pageSize":10,"size":3,"orderBy":null,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"square_region":"北京市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","score":null,"times":null,"square_name":"《党章》题库","count":null,"id":1,"state":null,"square_time":5},{"square_region":"北京市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","score":null,"times":null,"square_name":"党建知识竞赛试题库","count":null,"id":2,"state":null,"square_time":10},{"square_region":"北京市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","score":null,"times":null,"square_name":"党建及党的知识测试题","count":null,"id":3,"state":null,"square_time":3}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 3
         * orderBy : null
         * startRow : 1
         * endRow : 3
         * total : 3
         * pages : 1
         * list : [{"square_region":"北京市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","score":null,"times":null,"square_name":"《党章》题库","count":null,"id":1,"state":null,"square_time":5},{"square_region":"北京市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","score":null,"times":null,"square_name":"党建知识竞赛试题库","count":null,"id":2,"state":null,"square_time":10},{"square_region":"北京市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","score":null,"times":null,"square_name":"党建及党的知识测试题","count":null,"id":3,"state":null,"square_time":3}]
         * firstPage : 1
         * prePage : 0
         * nextPage : 0
         * lastPage : 1
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         */

        public int pageNum;
        public int pageSize;
        public int size;
        public Object orderBy;
        public int startRow;
        public int endRow;
        public int total;
        public int pages;
        public int firstPage;
        public int prePage;
        public int nextPage;
        public int lastPage;
        public boolean isFirstPage;
        public boolean isLastPage;
        public boolean hasPreviousPage;
        public boolean hasNextPage;
        public int navigatePages;
        public List<ListBean> list;
        public List<Integer> navigatepageNums;

        public static class ListBean {
            /**
             * square_region : 北京市
             * square_author : 马宝晶
             * square_photo : /upload/image/test.jpg
             * score : null
             * times : null
             * square_name : 《党章》题库
             * count : null
             * id : 1
             * state : null
             * square_time : 5
             */

            public String square_region;
            public String square_author;
            public String square_photo;
            public Object score;
            public String times;
            public String square_name;
            public Object count;
            public int id;
            public String state;
            public int square_time;
        }
    }
}
