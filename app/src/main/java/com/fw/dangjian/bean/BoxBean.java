package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class BoxBean {


    /**
     * result : [{"id":15,"name":"党务制度"},{"id":16,"name":"应知应会"}]
     * reason : 请求成功！
     * result_code : 200
     */

    public String reason;
    public String result_code;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 15
         * name : 党务制度
         */

        public int id;
        public String name;
    }
}
