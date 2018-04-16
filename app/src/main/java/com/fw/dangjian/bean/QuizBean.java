package com.fw.dangjian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */

public class QuizBean {


    /**
     * result : {"subject":[{"id":1,"subject_name":"十八大党章共11章____条。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"53","option_opt":"A","isOk":1},{"option_name":"50","option_opt":"B","isOk":0},{"option_name":"58","option_opt":"C","isOk":0}],"count":null},{"id":2,"subject_name":"中国共产党是中国工人阶级的先锋队，同时是____的先锋队，是中国特色社会主义事业的领导核心，代表中国先进生产力的发展要求，代表中国先进文化的前进方向，代表中国最广大人民的根本利益。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"中国人民","option_opt":"A","isOk":0},{"option_name":"中华民族","option_opt":"B","isOk":0},{"option_name":"中国人民和中华民族","option_opt":"C","isOk":1}],"count":null},{"id":3,"subject_name":"中国共产党以马克思列宁主义、毛泽东思想、邓小平理论、\u201c三个代表\u201d重要思想和____作为自己的行动指南","subject_type":2,"addtime":null,"optionEntity":[{"option_name":"社会主义荣辱观","option_opt":"A","isOk":1},{"option_name":"科学发展观","option_opt":"B","isOk":0},{"option_name":"社会主义核心价值","option_opt":"C","isOk":0}],"count":null},{"id":4,"subject_name":"党章总纲指出：马克思列宁主义揭示了____，它的基本原理是正确的，具有强大的生命力。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"共产党执政规律","option_opt":"A","isOk":0},{"option_name":"社会主义建设规律","option_opt":"B","isOk":0},{"option_name":"人类社会历史发展规律","option_opt":"C","isOk":1}],"count":null},{"id":5,"subject_name":"下列说法中，错误的应该是____。","subject_type":2,"addtime":null,"optionEntity":[{"option_name":"党员只能向上级党组织提出请求、申诉和控告","option_opt":"A","isOk":1},{"option_name":"党员可以向上级党组织直至中央提出请求、申诉和控告","option_opt":"B","isOk":0},{"option_name":"党员可以向上级党组织直至中央提出请求、申诉和控告，并要求有关组织给以负责的答复","option_opt":"C","isOk":0}],"count":null},{"id":6,"subject_name":"改革开放以来我们取得一切成绩和进步的根本原因，归结起来就是：开辟了中国特色社会主义道路，形成了中国特色社会主义理论体系，确立了____。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"中国特色社会主义旗帜","option_opt":"A","isOk":0},{"option_name":"中国特色社会主义制度","option_opt":"B","isOk":1},{"option_name":"中国特色社会主义目标","option_opt":"C","isOk":0}],"count":null},{"id":7,"subject_name":"我国正处于并将长期处于____。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"社会主义中级阶段","option_opt":"A","isOk":0},{"option_name":"社会主义高级阶段","option_opt":"B","isOk":0},{"option_name":"社会主义初级阶段","option_opt":"C","isOk":1}],"count":null},{"id":8,"subject_name":"在现阶段，我国社会的主要矛盾是____。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"阶级矛盾","option_opt":"A","isOk":0},{"option_name":"人民内部矛盾","option_opt":"B","isOk":0},{"option_name":"人民日益增长的物质文化需要同落后的社会生产之间的矛盾","option_opt":"C","isOk":1}],"count":null},{"id":9,"subject_name":"党章总纲指出:____是我们党执政兴国的第一要务。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"发展","option_opt":"A","isOk":1},{"option_name":"开放","option_opt":"B","isOk":0},{"option_name":"改革","option_opt":"C","isOk":0}],"count":null},{"id":10,"subject_name":"社会主义初级阶段需要____的时间。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"十几年","option_opt":"A","isOk":0},{"option_name":"几十年","option_opt":"B","isOk":0},{"option_name":"上百年","option_opt":"C","isOk":1}],"count":null}],"square_time":5}
     * result_msg : 请求成功！
     * result_code : 200
     */

    public ResultBean result;
    public String result_msg;
    public String result_code;

    public static class ResultBean {
        /**
         * subject : [{"id":1,"subject_name":"十八大党章共11章____条。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"53","option_opt":"A","isOk":1},{"option_name":"50","option_opt":"B","isOk":0},{"option_name":"58","option_opt":"C","isOk":0}],"count":null},{"id":2,"subject_name":"中国共产党是中国工人阶级的先锋队，同时是____的先锋队，是中国特色社会主义事业的领导核心，代表中国先进生产力的发展要求，代表中国先进文化的前进方向，代表中国最广大人民的根本利益。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"中国人民","option_opt":"A","isOk":0},{"option_name":"中华民族","option_opt":"B","isOk":0},{"option_name":"中国人民和中华民族","option_opt":"C","isOk":1}],"count":null},{"id":3,"subject_name":"中国共产党以马克思列宁主义、毛泽东思想、邓小平理论、\u201c三个代表\u201d重要思想和____作为自己的行动指南","subject_type":2,"addtime":null,"optionEntity":[{"option_name":"社会主义荣辱观","option_opt":"A","isOk":1},{"option_name":"科学发展观","option_opt":"B","isOk":0},{"option_name":"社会主义核心价值","option_opt":"C","isOk":0}],"count":null},{"id":4,"subject_name":"党章总纲指出：马克思列宁主义揭示了____，它的基本原理是正确的，具有强大的生命力。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"共产党执政规律","option_opt":"A","isOk":0},{"option_name":"社会主义建设规律","option_opt":"B","isOk":0},{"option_name":"人类社会历史发展规律","option_opt":"C","isOk":1}],"count":null},{"id":5,"subject_name":"下列说法中，错误的应该是____。","subject_type":2,"addtime":null,"optionEntity":[{"option_name":"党员只能向上级党组织提出请求、申诉和控告","option_opt":"A","isOk":1},{"option_name":"党员可以向上级党组织直至中央提出请求、申诉和控告","option_opt":"B","isOk":0},{"option_name":"党员可以向上级党组织直至中央提出请求、申诉和控告，并要求有关组织给以负责的答复","option_opt":"C","isOk":0}],"count":null},{"id":6,"subject_name":"改革开放以来我们取得一切成绩和进步的根本原因，归结起来就是：开辟了中国特色社会主义道路，形成了中国特色社会主义理论体系，确立了____。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"中国特色社会主义旗帜","option_opt":"A","isOk":0},{"option_name":"中国特色社会主义制度","option_opt":"B","isOk":1},{"option_name":"中国特色社会主义目标","option_opt":"C","isOk":0}],"count":null},{"id":7,"subject_name":"我国正处于并将长期处于____。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"社会主义中级阶段","option_opt":"A","isOk":0},{"option_name":"社会主义高级阶段","option_opt":"B","isOk":0},{"option_name":"社会主义初级阶段","option_opt":"C","isOk":1}],"count":null},{"id":8,"subject_name":"在现阶段，我国社会的主要矛盾是____。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"阶级矛盾","option_opt":"A","isOk":0},{"option_name":"人民内部矛盾","option_opt":"B","isOk":0},{"option_name":"人民日益增长的物质文化需要同落后的社会生产之间的矛盾","option_opt":"C","isOk":1}],"count":null},{"id":9,"subject_name":"党章总纲指出:____是我们党执政兴国的第一要务。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"发展","option_opt":"A","isOk":1},{"option_name":"开放","option_opt":"B","isOk":0},{"option_name":"改革","option_opt":"C","isOk":0}],"count":null},{"id":10,"subject_name":"社会主义初级阶段需要____的时间。","subject_type":1,"addtime":null,"optionEntity":[{"option_name":"十几年","option_opt":"A","isOk":0},{"option_name":"几十年","option_opt":"B","isOk":0},{"option_name":"上百年","option_opt":"C","isOk":1}],"count":null}]
         * square_time : 5
         */

        public int square_time;
        public List<SubjectBean> subject;

        public static class SubjectBean {
            /**
             * id : 1
             * subject_name : 十八大党章共11章____条。
             * subject_type : 1
             * addtime : null
             * optionEntity : [{"option_name":"53","option_opt":"A","isOk":1},{"option_name":"50","option_opt":"B","isOk":0},{"option_name":"58","option_opt":"C","isOk":0}]
             * count : null
             */

            public int id;
            public String subject_name;
            public int subject_type;
            public Object addtime;
            public Object count;
            public List<OptionEntityBean> optionEntity;

            public static class OptionEntityBean {
                /**
                 * option_name : 53
                 * option_opt : A
                 * isOk : 1
                 */

                public String option_name;
                public String option_opt;
                public int isOk;
            }
        }
    }
}
