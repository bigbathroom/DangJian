package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class BookBean {

    /**
     * result : {"pageInfo":{"pageNum":1,"pageSize":10,"size":1,"orderBy":null,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"comment_count":0,"post_title":"大事记1","cover_url":"http://dangjian.yaoyu-soft.com/dangjian/../dangjian/upload/201803/28005833ivf6.jpg","post_name":null,"post_date":"2018-03-28","post_source":"大事记3","id":145,"post_excerpt":"大事记2","post_column":13}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}}
     * reason : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String reason;
    public String result_code;

    public static class ResultBean {
        /**
         * pageInfo : {"pageNum":1,"pageSize":10,"size":1,"orderBy":null,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"comment_count":0,"post_title":"大事记1","cover_url":"http://dangjian.yaoyu-soft.com/dangjian/../dangjian/upload/201803/28005833ivf6.jpg","post_name":null,"post_date":"2018-03-28","post_source":"大事记3","id":145,"post_excerpt":"大事记2","post_column":13}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
         */

        public PageInfoBean pageInfo;

        public static class PageInfoBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * size : 1
             * orderBy : null
             * startRow : 1
             * endRow : 1
             * total : 1
             * pages : 1
             * list : [{"comment_count":0,"post_title":"大事记1","cover_url":"http://dangjian.yaoyu-soft.com/dangjian/../dangjian/upload/201803/28005833ivf6.jpg","post_name":null,"post_date":"2018-03-28","post_source":"大事记3","id":145,"post_excerpt":"大事记2","post_column":13}]
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
                 * comment_count : 0
                 * post_title : 大事记1
                 * cover_url : http://dangjian.yaoyu-soft.com/dangjian/../dangjian/upload/201803/28005833ivf6.jpg
                 * post_name : null
                 * post_date : 2018-03-28
                 * post_source : 大事记3
                 * id : 145
                 * post_excerpt : 大事记2
                 * post_column : 13
                 */

                public int comment_count;
                public String post_title;
                public String cover_url;
                public Object post_name;
                public String post_date;
                public String post_source;
                public int id;
                public String post_excerpt;
                public int post_column;
            }
        }
    }
}
