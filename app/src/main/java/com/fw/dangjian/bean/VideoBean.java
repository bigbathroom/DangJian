package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/2/7.
 */

public class VideoBean {

    /**
     * result : {"id":136,"post_date":"2018-03-25 16:00:00","post_excerpt":"日前，中央办公厅印发《关于在全体党员中开展\u201c学党章党规、学系列讲话，做合格党员\u201d学习教育方案》并发出通知。这是落实党章关于加强党员教育管理要求、面向全体党员深化党内教育的重要实践，是推动党内教育从\u201c关键少数\u201d向广大党员拓展、从集中性教育向经常性教育延伸的重要举措。在党的群众路线教育实践活动和\u201c三严三实\u201d专题教育取得显著成效基础上，开展\u201c两学一做\u201d学习教育，是加强党的思想政治建设、推动全面从严治党的重要部署。","post_content":"upload/video/20180209/1518146283448028970.mp4","post_column":10,"author":"管理员","photo":""}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * id : 136
         * post_date : 2018-03-25 16:00:00
         * post_excerpt : 日前，中央办公厅印发《关于在全体党员中开展“学党章党规、学系列讲话，做合格党员”学习教育方案》并发出通知。这是落实党章关于加强党员教育管理要求、面向全体党员深化党内教育的重要实践，是推动党内教育从“关键少数”向广大党员拓展、从集中性教育向经常性教育延伸的重要举措。在党的群众路线教育实践活动和“三严三实”专题教育取得显著成效基础上，开展“两学一做”学习教育，是加强党的思想政治建设、推动全面从严治党的重要部署。
         * post_content : upload/video/20180209/1518146283448028970.mp4
         * post_column : 10
         * author : 管理员
         * photo :
         */

        public int id;
        public String post_date;
        public String post_excerpt;
        public String post_content;
        public int post_column;
        public String author;
        public String photo;
    }
}
