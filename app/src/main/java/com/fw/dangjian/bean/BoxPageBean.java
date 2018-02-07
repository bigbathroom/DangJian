package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class BoxPageBean {


    /**
     * result : {"pageInfo":{"pageNum":1,"pageSize":10,"size":3,"orderBy":null,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"comment_count":0,"post_title":"人民社召开\u201c两学一做\u201d专题大会","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02161144zuxq.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":32,"post_excerpt":"中国共产党以马克思列宁主义、毛泽东思想、邓小平理论、\u201c三个代表\u201d重要思想和科学发展观作为自己的行动指南。","post_column":15},{"comment_count":0,"post_title":"中国共产党领导人民发展社会主义先进文化。","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02161402931d.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":33,"post_excerpt":"中国共产党领导人民发展社会主义民主政治。","post_column":15},{"comment_count":0,"post_title":"中国共产党领导人民构建社会主义和谐社会。","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\0216151027p7.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":34,"post_excerpt":"党的领导主要是政治、思想和组织的领导。","post_column":15}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]},"linksEntity":[{"link_url":"#","link_name":"Banner图片","link_image":"http://112.126.73.158:80/images/upload/banner/201802/banner01.png","link_target":"1","link_description":"图片描述信息"},{"link_url":"#","link_name":"图片名称","link_image":"http://112.126.73.158:80/images/upload/banner/201802/banner03.png","link_target":"1","link_description":"描述信息"}]}
     * reason : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String reason;
    public String result_code;

    public static class ResultBean {
        /**
         * pageInfo : {"pageNum":1,"pageSize":10,"size":3,"orderBy":null,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"comment_count":0,"post_title":"人民社召开\u201c两学一做\u201d专题大会","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02161144zuxq.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":32,"post_excerpt":"中国共产党以马克思列宁主义、毛泽东思想、邓小平理论、\u201c三个代表\u201d重要思想和科学发展观作为自己的行动指南。","post_column":15},{"comment_count":0,"post_title":"中国共产党领导人民发展社会主义先进文化。","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02161402931d.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":33,"post_excerpt":"中国共产党领导人民发展社会主义民主政治。","post_column":15},{"comment_count":0,"post_title":"中国共产党领导人民构建社会主义和谐社会。","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\0216151027p7.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":34,"post_excerpt":"党的领导主要是政治、思想和组织的领导。","post_column":15}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
         * linksEntity : [{"link_url":"#","link_name":"Banner图片","link_image":"http://112.126.73.158:80/images/upload/banner/201802/banner01.png","link_target":"1","link_description":"图片描述信息"},{"link_url":"#","link_name":"图片名称","link_image":"http://112.126.73.158:80/images/upload/banner/201802/banner03.png","link_target":"1","link_description":"描述信息"}]
         */

        public PageInfoBean pageInfo;
        public List<LinksEntityBean> linksEntity;

        public static class PageInfoBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * size : 3
             * orderBy : null
             * startRow : 1
             * endRow : 3
             * total : 3
             * pages : 1
             * list : [{"comment_count":0,"post_title":"人民社召开\u201c两学一做\u201d专题大会","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02161144zuxq.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":32,"post_excerpt":"中国共产党以马克思列宁主义、毛泽东思想、邓小平理论、\u201c三个代表\u201d重要思想和科学发展观作为自己的行动指南。","post_column":15},{"comment_count":0,"post_title":"中国共产党领导人民发展社会主义先进文化。","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02161402931d.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":33,"post_excerpt":"中国共产党领导人民发展社会主义民主政治。","post_column":15},{"comment_count":0,"post_title":"中国共产党领导人民构建社会主义和谐社会。","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\0216151027p7.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":34,"post_excerpt":"党的领导主要是政治、思想和组织的领导。","post_column":15}]
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
                 * post_title : 人民社召开“两学一做”专题大会
                 * cover_url : http://112.126.73.158:80/images/upload\fengmian\201802\02161144zuxq.jpg
                 * post_name : null
                 * post_date : 2018-02-02
                 * post_source : 新华社
                 * id : 32
                 * post_excerpt : 中国共产党以马克思列宁主义、毛泽东思想、邓小平理论、“三个代表”重要思想和科学发展观作为自己的行动指南。
                 * post_column : 15
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
             * link_url : #
             * link_name : Banner图片
             * link_image : http://112.126.73.158:80/images/upload/banner/201802/banner01.png
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

