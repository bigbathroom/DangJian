package com.fw.dangjian.bean;

/**
 * Created by Administrator on 2018/2/2.
 */

public class FileBean {

    /**
     * result : {"id":1,"managerid":1,"manager_phone":"17701228803","organize_name":"组织名称"}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * id : 1
         * managerid : 1
         * manager_phone : 17701228803
         * organize_name : 组织名称
         */

        public int id;
        public int managerid;
        public String manager_phone;
        public String organize_name;
    }
}
