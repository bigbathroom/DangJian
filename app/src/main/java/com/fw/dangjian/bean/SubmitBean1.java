package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/4/17.
 */

public class SubmitBean1 {

    /**
     * result : {"result":{"id":35,"managerid":10,"postId":13,"addtime":"2018-04-17 20:13:20","content":"\u201c生生世世是\u201d"},"result_msg":"请求成功！","result_code":"200"}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBeanX result;
    public String result_msg;
    public String result_code;

    public static class ResultBeanX {
        /**
         * result : {"id":35,"managerid":10,"postId":13,"addtime":"2018-04-17 20:13:20","content":"\u201c生生世世是\u201d"}
         * result_msg : 请求成功！
         * result_code : 200
         */

        public ResultBean result;
        public String result_msg;
        public String result_code;

        public static class ResultBean {
            /**
             * id : 35
             * managerid : 10
             * postId : 13
             * addtime : 2018-04-17 20:13:20
             * content : “生生世世是”
             */

            public int id;
            public int managerid;
            public int postId;
            public String addtime;
            public String content;
        }
    }
}
