package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/2/7.
 */

public class VideoBean {

    /**
     * result : {"id":18,"post_date":1517760000000,"post_excerpt":"","post_content":"http://112.126.73.158:80/images/upload/video/20180130/1517290045205019332.mp4","post_column":13,"author":"管理员","photo":""}
     * reason : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String reason;
    public String result_code;

    public static class ResultBean {
        /**
         * id : 18
         * post_date : 1517760000000
         * post_excerpt :
         * post_content : http://112.126.73.158:80/images/upload/video/20180130/1517290045205019332.mp4
         * post_column : 13
         * author : 管理员
         * photo :
         */

        public int id;
        public long post_date;
        public String post_excerpt;
        public String post_content;
        public int post_column;
        public String author;
        public String photo;
    }
}
