package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class ColumnBean {

    /**
     * result : [{"id":2,"name":"要闻"},{"id":3,"name":"工委要闻"},{"id":4,"name":"工作信息"},{"id":5,"name":"两学一作"},{"id":6,"name":"党员"},{"id":7,"name":"历史"},{"id":8,"name":"军事"}]
     * reason : 请求成功！
     * result_code : 200
     */

    public String reason;
    public String result_code;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 2
         * name : 要闻
         */

        public int id;
        public String name;
    }
}
