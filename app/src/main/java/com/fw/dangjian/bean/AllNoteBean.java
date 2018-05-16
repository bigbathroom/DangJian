package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class AllNoteBean {

    /**
     * result : [{"id":9,"managerid":10,"postId":135,"addtime":"2018-04-17 18:14:53","content":"坎坎坷坷"},{"id":7,"managerid":10,"postId":135,"addtime":"2018-04-17 18:01:43","content":"哈哈哈哈"},{"id":1,"managerid":10,"postId":135,"addtime":"2018-04-16 17:29:33","content":"哈哈哈"}]
     * result_msg : 请求成功！
     * result_code : 200
     */

    public String result_msg;
    public String result_code;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 9
         * managerid : 10
         * postId : 135
         * addtime : 2018-04-17 18:14:53
         * content : 坎坎坷坷
         */

        public int id;
        public int managerid;
        public int postId;
        public String post_title;
        public String post_excerpt;
        public String addtime;
        public String content;
        public String post_content;
        //     时间
        public String meeting_date_gmt;
        //    地址
        public String meeting_address;
        //    主持人
        public String meeting_author;
        //    记录人
        public String meeting_recorder;
        //    应到人数
        public String meeting_memberYD;
        //    实到人数
        public String meeting_memberSD;
        //    评论人
        public String name;
    }
}
