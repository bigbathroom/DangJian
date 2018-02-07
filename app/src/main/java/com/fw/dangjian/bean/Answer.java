package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */

public class Answer {

    /**
     * subject_id : 1
     * option_opt : ["a"]
     */

    private int subject_id;
    private List<String> option_opt;

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public List<String> getOption_opt() {
        return option_opt;
    }

    public void setOption_opt(List<String> option_opt) {
        this.option_opt = option_opt;
    }
}
