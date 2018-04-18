package com.fw.dangjian.netUtil;

import com.fw.dangjian.bean.ActionBean;
import com.fw.dangjian.bean.AllNoteBean;
import com.fw.dangjian.bean.BoardBean;
import com.fw.dangjian.bean.BookBean;
import com.fw.dangjian.bean.BoxBean;
import com.fw.dangjian.bean.BoxPageBean;
import com.fw.dangjian.bean.ColumnBean;
import com.fw.dangjian.bean.CommentBean;
import com.fw.dangjian.bean.CourseBean;
import com.fw.dangjian.bean.DjeBean;
import com.fw.dangjian.bean.FileBean;
import com.fw.dangjian.bean.HomeBean;
import com.fw.dangjian.bean.IdentifyCode;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.LoginBean;
import com.fw.dangjian.bean.MineBean;
import com.fw.dangjian.bean.MoneyBean;
import com.fw.dangjian.bean.NoteBean;
import com.fw.dangjian.bean.QuizBean;
import com.fw.dangjian.bean.RegistBean;
import com.fw.dangjian.bean.ScoreBean;
import com.fw.dangjian.bean.StudyBean;
import com.fw.dangjian.bean.StudyPageBean;
import com.fw.dangjian.bean.SubmitBean;
import com.fw.dangjian.bean.SubmitBean1;
import com.fw.dangjian.bean.TotalScoreBean;
import com.fw.dangjian.bean.VideoBean;
import com.fw.dangjian.bean.WaterBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface AppService {


    //获取首页的新闻类别
    @GET(Constants.HOME_COLUMN)
    Observable<ColumnBean> getHomeColumn();

    //获取首页的栏目新闻
    @GET(Constants.HOME_PAGE)
    Observable<HomeBean> getHomePage(@Query("columnid") int columnid,
                                     @Query("pageNum") int pageNum);


    //获取首页的栏目新闻
    @GET(Constants.HOME_PAGE)
    Observable<HomeBean> getHomePage1(@Query("pageNum") int pageNum,
                                      @Query("name") String name);

    //提交文章评论
    @FormUrlEncoded
    @POST(Constants.SUBMIT_COMMENT)
    Observable<KongBean> submitComment(@Field("comment_postid") int id,
                                       @Query("comment_author") String comment_author,
                                       @Query("comment_content") String comment_content);

    //提交文章评论1
    @FormUrlEncoded
    @POST(Constants.SUBMIT_COMMENT)
    Observable<KongBean> submitComment1(@Field("comment_postid") int id,
                                        @Query("comment_author") String comment_author,
                                        @Query("comment_content") String comment_content,
                                        @Field("managerid") int managerid);


    //提交视频评论
    @FormUrlEncoded
    @POST(Constants.SUBMIT_LEARN_COMMENT)
    Observable<KongBean> submitLearnComment1(@Field("comment_postid") int id,
                                             @Query("comment_author") String comment_author,
                                             @Query("comment_content") String comment_content,
                                             @Field("managerid") int managerid);

    //提交视频评论
    @FormUrlEncoded
    @POST(Constants.SUBMIT_LEARN_COMMENT)
    Observable<KongBean> submitLearnComment(@Field("comment_postid") int id,
                                            @Query("comment_author") String comment_author,
                                            @Query("comment_content") String comment_content);


    //获取评论
    @GET(Constants.All_COMMENT1 + "{postid}/")
    Observable<CommentBean> getComment(@Path("postid") int id);


    //点赞
    @FormUrlEncoded
    @POST(Constants.THUMB)
    Observable<KongBean> thumb(@Field("post_id") int id);

    //视频点赞
    @FormUrlEncoded
    @POST(Constants.THUMB1)
    Observable<KongBean> thumb1(@Field("post_id") int id);


    //获取学习视频类别
    @GET(Constants.STUDY_COLUMN)
    Observable<StudyBean> getStudyColumn();

    //获取学习视频列表
    @GET(Constants.STUDY_LIST)
    Observable<StudyPageBean> getStudyPage(@Query("columnid") int columnid,
                                           @Query("pageNum") int pageNum);

    //互动接口
    @GET(Constants.ACTION_LIST)
    Observable<ActionBean> getAction(@Query("pageNum") int pageNum);

    //在线竞答
    @GET(Constants.QUIZ + "{squareid}/")
    Observable<QuizBean> getQuestion(@Path("squareid") int squareid);

    //提交答案
    @POST(Constants.SUBMIT)
    Observable<SubmitBean> submitQuestion(@Header("managerid") int managerid,
                                          @Query("testId") int testId,
                                          @Query("times") int times,
                                          @Body RequestBody answer);

    //成绩单
    @GET(Constants.RESULT + "{squareid}/")
    Observable<ScoreBean> getResult(@Header("managerid") int managerid,@Path("squareid") int squareid);

    //登陆
    @FormUrlEncoded
    @POST(Constants.LOGIN)
    Observable<LoginBean> login(@Field("account") String username,
                                @Field("password") String password);


    //发送注册验证码
    @FormUrlEncoded
    @POST(Constants.SEND_YZM)
    Observable<IdentifyCode> sendCode(@Field("phone") String name,
                                      @Field("type") int type);


    //注册
    @FormUrlEncoded
    @POST(Constants.REGIST)
    Observable<RegistBean> regist(@Field("account") String s,
                                  @Field("password") String o,
                                  @Field("code") String k);

    //忘记密码
    @FormUrlEncoded
    @POST(Constants.FORGET_PSW)
    Observable<KongBean> forget(@Field("account") String s,
                                @Field("password") String o,
                                @Field("code") String k);

    //重置密码
    @FormUrlEncoded
    @POST(Constants.RESET_PSW)
    Observable<KongBean> reset(
            @Header("managerid") int id,
            @Field("oldpwd") String s,
            @Field("password") String o);

    //上传头像
    @Multipart
    @POST(Constants.UPLOADIMG)
    Observable<KongBean> uploadUserImg(@Part("managerid") int managerid, @PartMap Map<String, RequestBody> params);


    // 获取用户个人信息
    @GET(Constants.PROFILE + "{managerid}/")
    Observable<MineBean> getUserProfile(@Path("managerid") int managerid);

    //    修改用户名
    @FormUrlEncoded
    @POST(Constants.SAVEUSERNAME)
    Observable<KongBean> saveUserName(@Header("managerid") int id,
                                      @Field("name") String s);

    //获取党建e连心
    @GET(Constants.DJE_LIST)
    Observable<DjeBean> getDJEPage(@Query("columnid") int columnid, @Query("pageNum") int pageNum);

    //获取百宝箱类别
    @GET(Constants.BOX_COLUMN)
    Observable<BoxBean> getBoxColumn();

    //获取百宝箱列表
    @GET(Constants.BOX_LIST)
    Observable<BoxPageBean> getBoxPage(@Query("columnid") int columnid,
                                       @Query("pageNum") int pageNum);

    //获取百宝箱类别
    @GET(Constants.MONEY)
    Observable<MoneyBean> getMoney(@Query("salary") double salary);

    //档案信息
    @GET(Constants.FILE + "{managerid}/")
    Observable<FileBean> getFile(@Path("managerid") int managerid);

    //获取视频
    @GET(Constants.VIDEO + "{id}/")
    Observable<VideoBean> getVideo(@Path("id") int id);

    //记笔记
    @FormUrlEncoded
    @POST(Constants.NOTE )
    Observable<SubmitBean1> submitNote(@Field("managerid") int managerid,
                                       @Field("postId") int postId,
                                       @Query("content") String content);

    //记笔记
    @FormUrlEncoded
    @POST(Constants.NOTE )
    Observable<SubmitBean1> changeNote(@Field("managerid") int managerid,
                                       @Field("id") int id,
                                       @Query("content") String content);



    //学习课程
    @GET(Constants.COURSE)
    Observable<CourseBean> getCourse(@Query("pageNum") int pageNum);

    //大事记
    @GET(Constants.BOOK)
    Observable<BookBean> getBook(@Query("pageNum") int pageNum);

    //照片墙
    @GET(Constants.PHOTOWALL)
    Observable<WaterBean> getWater(@Query("pageNum") int pageNum, @Query("managerid") int managerid);

    //党建看板
    @GET(Constants.BOARD)
    Observable<BoardBean> getBoard(@Query("managerid") int managerid);

    // 获取总成绩单
    @GET(Constants.TOTAL_SCORE)
    Observable<TotalScoreBean> getTotalScore(@Header("managerid") int managerid);

    //获取单个笔记
    @GET(Constants.GET_NOTE + "{postid}")
    Observable<NoteBean> getNote(@Header("managerid") int managerid, @Path("postid") int postid);

    //获取所有笔记
    @GET(Constants.GET_ALL_NOTE)
    Observable<AllNoteBean> getAllNote(@Header("managerid") int managerid);


}


