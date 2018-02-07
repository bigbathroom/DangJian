package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class DjeBean {

    /**
     * result : {"pageInfo":{"pageNum":1,"pageSize":10,"size":4,"orderBy":null,"startRow":1,"endRow":4,"total":4,"pages":1,"list":[{"comment_count":0,"post_title":"习近平在阿拉伯国家联盟总部的演讲（全文）　","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02134430wptt.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":26,"post_excerpt":"共同开创中阿关系的美好未来\n\u2014\u2014在阿拉伯国家联盟总部的演讲","post_column":17},{"comment_count":0,"post_title":"习近平：战区对维护国家安全战略和军事战略全局具有举足轻重的作用","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02165252mlhh.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":44,"post_excerpt":"习近平向各战区授予军旗发布训令","post_column":17},{"comment_count":0,"post_title":"准确把握和抓好我国发展战略重点 把\u201c十三五\u201d发展蓝图变为现实","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02165351ut8g.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":45,"post_excerpt":"习近平在中共中央政治局第三十次集体学习时发表重要讲话","post_column":17},{"comment_count":0,"post_title":"制定好方案是做好供给侧结构性改革的基础","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02165509tpda.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":46,"post_excerpt":"近平在中央财经领导小组第十二次会议上发表重要讲话","post_column":17}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]},"linksEntity":[{"link_url":"#","link_name":"banner图片","link_image":"http://112.126.73.158:80/images/upload/banner/201802/banner02_58.png","link_target":"1","link_description":"banner图片描述信息"},{"link_url":"#","link_name":"图片名称","link_image":"http://112.126.73.158:80/images/upload/banner/201802/banner05.png","link_target":"1","link_description":"描述信息"}]}
     * reason : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String reason;
    public String result_code;

    public static class ResultBean {
        /**
         * pageInfo : {"pageNum":1,"pageSize":10,"size":4,"orderBy":null,"startRow":1,"endRow":4,"total":4,"pages":1,"list":[{"comment_count":0,"post_title":"习近平在阿拉伯国家联盟总部的演讲（全文）　","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02134430wptt.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":26,"post_excerpt":"共同开创中阿关系的美好未来\n\u2014\u2014在阿拉伯国家联盟总部的演讲","post_column":17},{"comment_count":0,"post_title":"习近平：战区对维护国家安全战略和军事战略全局具有举足轻重的作用","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02165252mlhh.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":44,"post_excerpt":"习近平向各战区授予军旗发布训令","post_column":17},{"comment_count":0,"post_title":"准确把握和抓好我国发展战略重点 把\u201c十三五\u201d发展蓝图变为现实","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02165351ut8g.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":45,"post_excerpt":"习近平在中共中央政治局第三十次集体学习时发表重要讲话","post_column":17},{"comment_count":0,"post_title":"制定好方案是做好供给侧结构性改革的基础","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02165509tpda.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":46,"post_excerpt":"近平在中央财经领导小组第十二次会议上发表重要讲话","post_column":17}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
         * linksEntity : [{"link_url":"#","link_name":"banner图片","link_image":"http://112.126.73.158:80/images/upload/banner/201802/banner02_58.png","link_target":"1","link_description":"banner图片描述信息"},{"link_url":"#","link_name":"图片名称","link_image":"http://112.126.73.158:80/images/upload/banner/201802/banner05.png","link_target":"1","link_description":"描述信息"}]
         */

        public PageInfoBean pageInfo;
        public List<LinksEntityBean> linksEntity;

        public static class PageInfoBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * size : 4
             * orderBy : null
             * startRow : 1
             * endRow : 4
             * total : 4
             * pages : 1
             * list : [{"comment_count":0,"post_title":"习近平在阿拉伯国家联盟总部的演讲（全文）　","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02134430wptt.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":26,"post_excerpt":"共同开创中阿关系的美好未来\n\u2014\u2014在阿拉伯国家联盟总部的演讲","post_column":17},{"comment_count":0,"post_title":"习近平：战区对维护国家安全战略和军事战略全局具有举足轻重的作用","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02165252mlhh.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":44,"post_excerpt":"习近平向各战区授予军旗发布训令","post_column":17},{"comment_count":0,"post_title":"准确把握和抓好我国发展战略重点 把\u201c十三五\u201d发展蓝图变为现实","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02165351ut8g.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":45,"post_excerpt":"习近平在中共中央政治局第三十次集体学习时发表重要讲话","post_column":17},{"comment_count":0,"post_title":"制定好方案是做好供给侧结构性改革的基础","cover_url":"http://112.126.73.158:80/images/upload\\fengmian\\201802\\02165509tpda.jpg","post_name":null,"post_date":"2018-02-02","post_source":"新华社","id":46,"post_excerpt":"近平在中央财经领导小组第十二次会议上发表重要讲话","post_column":17}]
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
                 * post_title : 习近平在阿拉伯国家联盟总部的演讲（全文）　
                 * cover_url : http://112.126.73.158:80/images/upload\fengmian\201802\02134430wptt.jpg
                 * post_name : null
                 * post_date : 2018-02-02
                 * post_source : 新华社
                 * id : 26
                 * post_excerpt : 共同开创中阿关系的美好未来
                 ——在阿拉伯国家联盟总部的演讲
                 * post_column : 17
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
             * link_name : banner图片
             * link_image : http://112.126.73.158:80/images/upload/banner/201802/banner02_58.png
             * link_target : 1
             * link_description : banner图片描述信息
             */

            public String link_url;
            public String link_name;
            public String link_image;
            public String link_target;
            public String link_description;
        }
    }
}
