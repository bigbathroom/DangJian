package com.fw.dangjian.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/14.
 */

public class PrintBean implements Serializable {
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
//    会议标题
    public String post_title;
//    会议主题
    public String post_excerpt;
    public String addtime;
//    评论内容
    public String content;
//      会议内容
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


    public void setMeeting_date_gmt(String meeting_date_gmt) {
        this.meeting_date_gmt = meeting_date_gmt;
    }

    public void setMeeting_address(String meeting_address) {
        this.meeting_address = meeting_address;
    }

    public void setMeeting_author(String meeting_author) {
        this.meeting_author = meeting_author;
    }

    public void setMeeting_recorder(String meeting_recorder) {
        this.meeting_recorder = meeting_recorder;
    }

    public void setMeeting_memberYD(String meeting_memberYD) {
        this.meeting_memberYD = meeting_memberYD;
    }

    public void setMeeting_memberSD(String meeting_memberSD) {
        this.meeting_memberSD = meeting_memberSD;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setManagerid(int managerid) {
        this.managerid = managerid;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public void setPost_excerpt(String post_excerpt) {
        this.post_excerpt = post_excerpt;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public void setContent(String content) {
        this.content = content;
    }




}
