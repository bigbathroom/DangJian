package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

public class StudyBean {
    public String result_msg;
    public String result_code;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 2
         * name : 要闻
         */

        public String id;
        public String name;
    }
}
