package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class CommentBean {


    /**
     * result : [{"id":76,"comment_postid":null,"comment_author":"张三","comment_author_email":null,"comment_author_url":null,"comment_author_ip":null,"comment_date":null,"comment_content":"一心一意","comment_approved":null,"comment_agent":null,"comment_type":null,"comment_parent":null,"manager_id":null},{"id":75,"comment_postid":null,"comment_author":"张三","comment_author_email":null,"comment_author_url":null,"comment_author_ip":null,"comment_date":null,"comment_content":"  一心一意","comment_approved":null,"comment_agent":null,"comment_type":null,"comment_parent":null,"manager_id":null}]
     * result_msg : 请求成功！
     * result_code : 200
     */

    public String result_msg;
    public String result_code;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 76
         * comment_postid : null
         * comment_author : 张三
         * comment_author_email : null
         * comment_author_url : null
         * comment_author_ip : null
         * comment_date : null
         * comment_content : 一心一意
         * comment_approved : null
         * comment_agent : null
         * comment_type : null
         * comment_parent : null
         * manager_id : null
         */

        public int id;
        public Object comment_postid;
        public String comment_author;
        public Object comment_author_email;
        public Object comment_author_url;
        public Object comment_author_ip;
        public Object comment_date;
        public String comment_content;
        public Object comment_approved;
        public Object comment_agent;
        public Object comment_type;
        public Object comment_parent;
        public Object manager_id;
    }
}
