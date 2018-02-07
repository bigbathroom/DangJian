package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class StudyPageBean {

    /**
     * result : {"pageInfo":{"pageNum":1,"pageSize":10,"size":1,"orderBy":null,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"comment_count":0,"post_title":"人心向背决定中国命运\u2014\u2014解放战争胜利的启示","cover_url":"upload\\fengmian\\201801\\3013280362cj.jpg","post_name":null,"post_date":"2018-01-30","post_source":"《党建》杂志","id":18,"post_excerpt":null,"post_column":10}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]},"linksEntity":[{"link_url":"###","link_name":"Banner图片","link_image":"/uploadl/image/test.jsp","link_target":"1","link_description":"图片信息"}]}
     * reason : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String reason;
    public String result_code;

    public static class ResultBean {
        /**
         * pageInfo : {"pageNum":1,"pageSize":10,"size":1,"orderBy":null,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"comment_count":0,"post_title":"人心向背决定中国命运\u2014\u2014解放战争胜利的启示","cover_url":"upload\\fengmian\\201801\\3013280362cj.jpg","post_name":null,"post_date":"2018-01-30","post_source":"《党建》杂志","id":18,"post_excerpt":null,"post_column":10}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
         * linksEntity : [{"link_url":"###","link_name":"Banner图片","link_image":"/uploadl/image/test.jsp","link_target":"1","link_description":"图片信息"}]
         */

        public PageInfoBean pageInfo;
        public List<LinksEntityBean> linksEntity;

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
             * list : [{"comment_count":0,"post_title":"人心向背决定中国命运\u2014\u2014解放战争胜利的启示","cover_url":"upload\\fengmian\\201801\\3013280362cj.jpg","post_name":null,"post_date":"2018-01-30","post_source":"《党建》杂志","id":18,"post_excerpt":null,"post_column":10}]
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
                 * post_title : 人心向背决定中国命运——解放战争胜利的启示
                 * cover_url : upload\fengmian\201801\3013280362cj.jpg
                 * post_name : null
                 * post_date : 2018-01-30
                 * post_source : 《党建》杂志
                 * id : 18
                 * post_excerpt : null
                 * post_column : 10
                 */

                public int comment_count;
                public String post_title;
                public String cover_url;
                public Object post_name;
                public String post_date;
                public String post_source;
                public int id;
                public Object post_excerpt;
                public int post_column;
            }
        }

        public static class LinksEntityBean {
            /**
             * link_url : ###
             * link_name : Banner图片
             * link_image : /uploadl/image/test.jsp
             * link_target : 1
             * link_description : 图片信息
             */

            public String link_url;
            public String link_name;
            public String link_image;
            public String link_target;
            public String link_description;
        }
    }
}
