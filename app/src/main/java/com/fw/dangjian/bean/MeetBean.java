package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MeetBean {

    /**
     * result : {"pageInfo":{"pageNum":1,"pageSize":10,"size":1,"orderBy":null,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"comment_count":0,"meeting_memberYD":12,"post_title":"攻打魁拔","cover_url":"http://dangjian.yaoyu-soft.com/dangjian/../dangjian/upload/201804/19173731ha98.jpg","meeting_memberSD":12,"meeting_memberNonY":"哇咔咔","post_excerpt":"去往涡流岛，攻打魁拔","post_column":50,"post_name":null,"post_date":"2018-04-19","post_source":"曲境一号","meeting_address":"窝窝村","meeting_recorder":"蛮吉","id":6,"meeting_author":"村长大人","meeting_date_gmt":"2018-04-19 17:25"}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * pageInfo : {"pageNum":1,"pageSize":10,"size":1,"orderBy":null,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"comment_count":0,"meeting_memberYD":12,"post_title":"攻打魁拔","cover_url":"http://dangjian.yaoyu-soft.com/dangjian/../dangjian/upload/201804/19173731ha98.jpg","meeting_memberSD":12,"meeting_memberNonY":"哇咔咔","post_excerpt":"去往涡流岛，攻打魁拔","post_column":50,"post_name":null,"post_date":"2018-04-19","post_source":"曲境一号","meeting_address":"窝窝村","meeting_recorder":"蛮吉","id":6,"meeting_author":"村长大人","meeting_date_gmt":"2018-04-19 17:25"}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
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
             * list : [{"comment_count":0,"meeting_memberYD":12,"post_title":"攻打魁拔","cover_url":"http://dangjian.yaoyu-soft.com/dangjian/../dangjian/upload/201804/19173731ha98.jpg","meeting_memberSD":12,"meeting_memberNonY":"哇咔咔","post_excerpt":"去往涡流岛，攻打魁拔","post_column":50,"post_name":null,"post_date":"2018-04-19","post_source":"曲境一号","meeting_address":"窝窝村","meeting_recorder":"蛮吉","id":6,"meeting_author":"村长大人","meeting_date_gmt":"2018-04-19 17:25"}]
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
                 * meeting_memberYD : 12
                 * post_title : 攻打魁拔
                 * cover_url : http://dangjian.yaoyu-soft.com/dangjian/../dangjian/upload/201804/19173731ha98.jpg
                 * meeting_memberSD : 12
                 * meeting_memberNonY : 哇咔咔
                 * post_excerpt : 去往涡流岛，攻打魁拔
                 * post_column : 50
                 * post_name : null
                 * post_date : 2018-04-19
                 * post_source : 曲境一号
                 * meeting_address : 窝窝村
                 * meeting_recorder : 蛮吉
                 * id : 6
                 * meeting_author : 村长大人
                 * meeting_date_gmt : 2018-04-19 17:25
                 */

                public int comment_count;
                public int meeting_memberYD;
                public String post_title;
                public String cover_url;
                public int meeting_memberSD;
                public String meeting_memberNonY;
                public String post_excerpt;
                public int post_column;
                public Object post_name;
                public String post_date;
                public String post_source;
                public String meeting_address;
                public String meeting_recorder;
                public int id;
                public String meeting_author;
                public String meeting_date_gmt;
            }
        }
    }
}
