package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/4/17.
 */

public class NoteBean {

    /**
     * result : {"id":7,"managerid":10,"postId":135,"addtime":"2018-04-17 18:01:43","content":"哈哈哈哈"}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * id : 7
         * managerid : 10
         * postId : 135
         * addtime : 2018-04-17 18:01:43
         * content : 哈哈哈哈
         */

        public int id;
        public int managerid;
        public int postId;
        public String addtime;
        public String content;
    }
}
