package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class OrganisationBean {

    /**
     * result : {"pageNum":1,"pageSize":10,"size":10,"orderBy":null,"startRow":1,"endRow":10,"total":27,"pages":3,"list":[{"zhibuxinix":null,"rolecode":"manager","shiye":null,"nickName":null,"rolename":"超级管理员","yijizuzhi":null,"phone":null,"organize":null,"name":"管理员","id":1,"time":1524636043000,"rudangzhibu":null,"locked":true,"account":"admin"},{"zhibuxinix":null,"rolecode":"code","shiye":null,"nickName":null,"rolename":"开发管理员","yijizuzhi":null,"phone":null,"organize":null,"name":"吴守星","id":3,"time":1515072213000,"rudangzhibu":null,"locked":true,"account":"memmsc"},{"zhibuxinix":"去","rolecode":"test","shiye":"去","nickName":"去","rolename":"测试管理员","yijizuzhi":"","phone":null,"organize":"","name":"test","id":7,"time":1520488878000,"rudangzhibu":" 去","locked":true,"account":"test"},{"zhibuxinix":"0","rolecode":"Info Manager","shiye":"0","nickName":"0","rolename":"信息管理员","yijizuzhi":"0","phone":"0","organize":"0","name":"info","id":24,"time":1524628058000,"rudangzhibu":"0","locked":true,"account":"dangjian"},{"zhibuxinix":null,"rolecode":"manager","shiye":" 我","nickName":"我","rolename":"超级管理员","yijizuzhi":"我","phone":"我","organize":"我","name":"我","id":27,"time":1524636994000,"rudangzhibu":"我","locked":true,"account":"buwenyuan"},{"zhibuxinix":null,"rolecode":null,"shiye":null,"nickName":null,"rolename":null,"yijizuzhi":null,"phone":null,"organize":null,"name":null,"id":60,"time":1524621352000,"rudangzhibu":null,"locked":false,"account":"18210459634"},{"zhibuxinix":null,"rolecode":null,"shiye":null,"nickName":null,"rolename":null,"yijizuzhi":null,"phone":null,"organize":null,"name":null,"id":61,"time":1524563965000,"rudangzhibu":null,"locked":false,"account":"15538293786"},{"zhibuxinix":null,"rolecode":null,"shiye":null,"nickName":null,"rolename":null,"yijizuzhi":null,"phone":null,"organize":null,"name":null,"id":62,"time":1524624652000,"rudangzhibu":null,"locked":false,"account":"18518089115"},{"zhibuxinix":null,"rolecode":null,"shiye":null,"nickName":null,"rolename":null,"yijizuzhi":null,"phone":null,"organize":null,"name":null,"id":63,"time":1524557035000,"rudangzhibu":null,"locked":false,"account":"18847788700"},{"zhibuxinix":"220282198005026211","rolecode":"push","shiye":"副经理","nickName":"男","rolename":"信息发布","yijizuzhi":"","phone":null,"organize":"","name":"王彦鸿","id":92,"time":1524630048000,"rudangzhibu":"中共党员","locked":true,"account":"18618413725"}],"firstPage":1,"prePage":0,"nextPage":2,"lastPage":3,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3]}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 10
         * orderBy : null
         * startRow : 1
         * endRow : 10
         * total : 27
         * pages : 3
         * list : [{"zhibuxinix":null,"rolecode":"manager","shiye":null,"nickName":null,"rolename":"超级管理员","yijizuzhi":null,"phone":null,"organize":null,"name":"管理员","id":1,"time":1524636043000,"rudangzhibu":null,"locked":true,"account":"admin"},{"zhibuxinix":null,"rolecode":"code","shiye":null,"nickName":null,"rolename":"开发管理员","yijizuzhi":null,"phone":null,"organize":null,"name":"吴守星","id":3,"time":1515072213000,"rudangzhibu":null,"locked":true,"account":"memmsc"},{"zhibuxinix":"去","rolecode":"test","shiye":"去","nickName":"去","rolename":"测试管理员","yijizuzhi":"","phone":null,"organize":"","name":"test","id":7,"time":1520488878000,"rudangzhibu":" 去","locked":true,"account":"test"},{"zhibuxinix":"0","rolecode":"Info Manager","shiye":"0","nickName":"0","rolename":"信息管理员","yijizuzhi":"0","phone":"0","organize":"0","name":"info","id":24,"time":1524628058000,"rudangzhibu":"0","locked":true,"account":"dangjian"},{"zhibuxinix":null,"rolecode":"manager","shiye":" 我","nickName":"我","rolename":"超级管理员","yijizuzhi":"我","phone":"我","organize":"我","name":"我","id":27,"time":1524636994000,"rudangzhibu":"我","locked":true,"account":"buwenyuan"},{"zhibuxinix":null,"rolecode":null,"shiye":null,"nickName":null,"rolename":null,"yijizuzhi":null,"phone":null,"organize":null,"name":null,"id":60,"time":1524621352000,"rudangzhibu":null,"locked":false,"account":"18210459634"},{"zhibuxinix":null,"rolecode":null,"shiye":null,"nickName":null,"rolename":null,"yijizuzhi":null,"phone":null,"organize":null,"name":null,"id":61,"time":1524563965000,"rudangzhibu":null,"locked":false,"account":"15538293786"},{"zhibuxinix":null,"rolecode":null,"shiye":null,"nickName":null,"rolename":null,"yijizuzhi":null,"phone":null,"organize":null,"name":null,"id":62,"time":1524624652000,"rudangzhibu":null,"locked":false,"account":"18518089115"},{"zhibuxinix":null,"rolecode":null,"shiye":null,"nickName":null,"rolename":null,"yijizuzhi":null,"phone":null,"organize":null,"name":null,"id":63,"time":1524557035000,"rudangzhibu":null,"locked":false,"account":"18847788700"},{"zhibuxinix":"220282198005026211","rolecode":"push","shiye":"副经理","nickName":"男","rolename":"信息发布","yijizuzhi":"","phone":null,"organize":"","name":"王彦鸿","id":92,"time":1524630048000,"rudangzhibu":"中共党员","locked":true,"account":"18618413725"}]
         * firstPage : 1
         * prePage : 0
         * nextPage : 2
         * lastPage : 3
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3]
         */

        public int pageNum;
        public int pageSize;
        public int size;
        public Object orderBy;
        public int startRow;
        public int endRow;
        public int total;
        public int pages;
        public int firstPage;
        public int prePage;
        public int nextPage;
        public int lastPage;
        public boolean isFirstPage;
        public boolean isLastPage;
        public boolean hasPreviousPage;
        public boolean hasNextPage;
        public int navigatePages;
        public List<ListBean> list;
        public List<Integer> navigatepageNums;

        public static class ListBean {
            /**
             * zhibuxinix : null
             * rolecode : manager
             * shiye : null
             * nickName : null
             * rolename : 超级管理员
             * yijizuzhi : null
             * phone : null
             * organize : null
             * name : 管理员
             * id : 1
             * time : 1524636043000
             * rudangzhibu : null
             * locked : true
             * account : admin
             */

            public Object zhibuxinix;
            public String rolecode;
            public Object shiye;
            public Object nickName;
            public String rolename;
            public Object yijizuzhi;
            public Object phone;
            public Object organize;
            public String name;
            public int id;
            public long time;
            public String rudangzhibu;
            public boolean locked;
            public String account;
        }
    }
}
