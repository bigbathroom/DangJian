package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

public class ActionBean {

    /**
     * result : {"pageNum":1,"pageSize":10,"size":2,"orderBy":null,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"square_region":"甘肃省白银市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","square_name":"中共中央总书记、国家主席","id":1,"square_time":5},{"square_region":"甘肃省白银市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","square_name":"中共中央总书记、国家主席","id":2,"square_time":10}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
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
         * size : 2
         * orderBy : null
         * startRow : 1
         * endRow : 2
         * total : 2
         * pages : 1
         * list : [{"square_region":"甘肃省白银市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","square_name":"中共中央总书记、国家主席","id":1,"square_time":5},{"square_region":"甘肃省白银市","square_author":"马宝晶","square_photo":"/upload/image/test.jpg","square_name":"中共中央总书记、国家主席","id":2,"square_time":10}]
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
             * square_region : 甘肃省白银市
             * square_author : 马宝晶
             * square_photo : /upload/image/test.jpg
             * square_name : 中共中央总书记、国家主席
             * id : 1
             * square_time : 5
             */

            public String square_region;
            public String square_author;
            public String square_photo;
            public String square_name;
            public int id;
            public int square_time;
        }
    }
}
