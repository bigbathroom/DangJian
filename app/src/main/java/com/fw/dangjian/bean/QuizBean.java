package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */

public class QuizBean {

    /**
     * result : {"subject":[{"id":1,"subject_name":"第一题的题目信息","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"选项A的信息","option_opt":"A"},{"option_name":"选项B的信息","option_opt":"B"},{"option_name":"选项C的信息","option_opt":"C"},{"option_name":"选项D的信息","option_opt":"D"}],"count":1}],"square_time":5}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * subject : [{"id":1,"subject_name":"第一题的题目信息","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"选项A的信息","option_opt":"A"},{"option_name":"选项B的信息","option_opt":"B"},{"option_name":"选项C的信息","option_opt":"C"},{"option_name":"选项D的信息","option_opt":"D"}],"count":1}]
         * square_time : 5
         */

        public int square_time;
        public List<SubjectBean> subject;

        public static class SubjectBean {
            /**
             * id : 1
             * subject_name : 第一题的题目信息
             * subject_type : 1
             * addtime : null
             * optionEntity : [{"option_name":"选项A的信息","option_opt":"A"},{"option_name":"选项B的信息","option_opt":"B"},{"option_name":"选项C的信息","option_opt":"C"},{"option_name":"选项D的信息","option_opt":"D"}]
             * count : 1
             */

            public int id;
            public String subject_name;
            public int subject_type;
            public Object addtime;
            public int count;
            public List<OptionEntityBean> optionEntity;

            public static class OptionEntityBean {
                /**
                 * option_name : 选项A的信息
                 * option_opt : A
                 */

                public String option_name;
                public String option_opt;
            }
        }
    }
}
