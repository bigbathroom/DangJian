package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HomeBean {
    /**
     * result : {"pageInfo":{"pageNum":1,"pageSize":10,"size":2,"orderBy":null,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"comment_count":0,"post_title":"习近平主持中共中央政治局会议","cover_url":"upload\\fengmian\\201802\\010101489pcx.jpg","post_name":null,"post_date":"2018-02-02","post_source":"党建网","id":19,"post_excerpt":"中共中央政治局召开会议审议《中央政治局常委会听取和研究全国人大常委会、国务院、全国政协、最高人民法院、最高人民检察院党组工作汇报和中央书记处工作报告的综合情况报告》","post_column":13},{"comment_count":0,"post_title":"内容标题1","cover_url":"dto.cover_url","post_name":null,"post_date":"2018-02-01","post_source":"党建网","id":21,"post_excerpt":"","post_column":13}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]},"linksEntity":[]}
     * reason : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String reason;
    public String result_code;

    public static class ResultBean {
        /**
         * pageInfo : {"pageNum":1,"pageSize":10,"size":2,"orderBy":null,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"comment_count":0,"post_title":"习近平主持中共中央政治局会议","cover_url":"upload\\fengmian\\201802\\010101489pcx.jpg","post_name":null,"post_date":"2018-02-02","post_source":"党建网","id":19,"post_excerpt":"中共中央政治局召开会议审议《中央政治局常委会听取和研究全国人大常委会、国务院、全国政协、最高人民法院、最高人民检察院党组工作汇报和中央书记处工作报告的综合情况报告》","post_column":13},{"comment_count":0,"post_title":"内容标题1","cover_url":"dto.cover_url","post_name":null,"post_date":"2018-02-01","post_source":"党建网","id":21,"post_excerpt":"","post_column":13}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
         * linksEntity : []
         */

        public PageInfoBean pageInfo;
        public List<LinksEntityBean> linksEntity;

        public static class PageInfoBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * size : 2
             * orderBy : null
             * startRow : 1
             * endRow : 2
             * total : 2
             * pages : 1
             * list : [{"comment_count":0,"post_title":"习近平主持中共中央政治局会议","cover_url":"upload\\fengmian\\201802\\010101489pcx.jpg","post_name":null,"post_date":"2018-02-02","post_source":"党建网","id":19,"post_excerpt":"中共中央政治局召开会议审议《中央政治局常委会听取和研究全国人大常委会、国务院、全国政协、最高人民法院、最高人民检察院党组工作汇报和中央书记处工作报告的综合情况报告》","post_column":13},{"comment_count":0,"post_title":"内容标题1","cover_url":"dto.cover_url","post_name":null,"post_date":"2018-02-01","post_source":"党建网","id":21,"post_excerpt":"","post_column":13}]
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
                 * post_title : 习近平主持中共中央政治局会议
                 * cover_url : upload\fengmian\201802\010101489pcx.jpg
                 * post_name : null
                 * post_date : 2018-02-02
                 * post_source : 党建网
                 * id : 19
                 * post_excerpt : 中共中央政治局召开会议审议《中央政治局常委会听取和研究全国人大常委会、国务院、全国政协、最高人民法院、最高人民检察院党组工作汇报和中央书记处工作报告的综合情况报告》
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

        public static class LinksEntityBean {
            /**
             * link_url : ###
             * link_name : Banner图片
             * link_image : /upload/image/test.jpg
             * link_target : 1
             * link_description : 图片描述信息
             */

            public String link_url;
            public String link_name;
            public String link_image;
            public String link_target;
            public String link_description;
        }
    }

}
