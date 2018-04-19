package com.fw.dangjian.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.MineBean;
import com.fw.dangjian.mvpView.UserCenterMvpView;
import com.fw.dangjian.popwin.CameraPopu;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.SettingService;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.fw.dangjian.util.ImageUtils.createNewFile;
import static com.fw.dangjian.util.ImageUtils.cropImageUri;
import static com.fw.dangjian.util.ImageUtils.file_photo;
import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;
import static com.umeng.socialize.utils.DeviceConfig.context;

public class MineFragment extends BaseFragment implements UserCenterMvpView {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_msg)
    ImageView iv_msg;
    @BindView(R.id.rv_touxiang)
    RoundedImageView rv_touxiang;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rl_ku)
    RelativeLayout rl_ku;
    @BindView(R.id.rl_photo)
    RelativeLayout rl_photo;
    @BindView(R.id.rl_manager)
    RelativeLayout rl_manager;
    @BindView(R.id.rl_user)
    RelativeLayout rl_user;
    @BindView(R.id.rl_course)
    RelativeLayout rl_course;
    @BindView(R.id.rl_score)
    RelativeLayout rl_score;
    @BindView(R.id.rl_psw)
    RelativeLayout rl_psw;
    @BindView(R.id.rl_spec)
    RelativeLayout rl_spec;
    @BindView(R.id.root)
    LinearLayout root;

    private CameraPopu cameraPopu;
    public static final int REQUEST_CODE_CAMERA = 100;
    private static final int REQUEST_CODE_PHOTO = 200;
    UserPresenter userPresenter;
    int managerId;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_mine, null);
    }

    @Override
    protected void initUi() {
        tv_title.setText("用户设置");
//        iv_msg.setVisibility(View.VISIBLE);
        managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);
        userPresenter = new UserPresenter();
        userPresenter.getUserProfile(managerId, this);
    }

    @Override
    protected void initData() {

    }


    private Rationale mRationale = new Rationale() {
        @Override
        public void showRationale(Context context, List<String> permissions,
                                  final RequestExecutor executor) {
            // 这里使用一个Dialog询问用户是否继续授权。

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("温馨提醒");
            builder.setMessage("建议打开您的相机权限");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 如果用户中断：
                    executor.cancel();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 如果用户继续：
                    executor.execute();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };


    @OnClick({R.id.iv_msg, R.id.rv_touxiang, R.id.rl_ku, R.id.rl_photo, R.id.rl_manager, R.id.rl_spec, R.id.rl_user, R.id.rl_course,R.id.rl_score, R.id.rl_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:

                break;
            case R.id.rv_touxiang:

                AndPermission.with(getActivity())
                        .permission(Permission.CAMERA)
                        .rationale(mRationale)
                        .onGranted(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                // TODO what to do.
                                showPop();
                            }
                        }).onDenied(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                if (AndPermission.hasAlwaysDeniedPermission(context, permissions)) {
                                    // 这些权限被用户总是拒绝。
                                    // 这里使用一个Dialog展示没有这些权限应用程序无法继续运行，询问用户是否去设置中授权。
                                    final SettingService settingService = AndPermission.permissionSetting(mContext);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setTitle("温馨提醒");
                                    builder.setMessage("建议打开您的相机权限,否则无法继续");
                                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // 如果用户不同意去设置：
                                            settingService.cancel();
                                        }
                                    });
                                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // 如果用户同意去设置：
                                            settingService.execute();
                                        }
                                    });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            }
                        })
                        .start();

                break;
            case R.id.rl_ku:
                if (managerId == -1) {
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                } else {
//                    我的档案信息
                  /*  Intent intent1 = new Intent(getActivity(), FileActivity.class);
                    startActivity(intent1);*/
                    Intent intent1 = new Intent(getActivity(), WebActivity.class);
                    intent1.putExtra("flag_id",100);
                    startActivity(intent1);
                }

                break;
            case R.id.rl_photo:
                if (managerId == -1) {
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                } else {
                    Intent intent1 = new Intent(getActivity(), DJEActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.rl_score:
                if (managerId == -1) {
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                } else {
                    Intent intent1 = new Intent(getActivity(), TotalScoreActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.rl_manager:
                if (managerId == -1) {
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                } else {
                    Intent intent1 = new Intent(getActivity(), ManagerActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.rl_user:
                if (managerId == -1) {
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                } else {
                    Intent intent2 = new Intent(getActivity(), ChangeNameActivity.class);
                    startActivityForResult(intent2, 400);

                }

                break;
            case R.id.rl_course:
                if (managerId == -1) {
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                } else {
                    Intent intent5 = new Intent(getActivity(), CourseActivity.class);
                    startActivity(intent5);
                }

                break;
            case R.id.rl_psw:
                if (managerId == -1) {
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                } else {
                    Intent intent3 = new Intent(getActivity(), ResetPswActivity.class);
                    startActivity(intent3);
                }
                break;
            case R.id.rl_spec:
                if (managerId == -1) {
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                } else {
                    SPUtils.remove(getActivity(), ConstanceValue.LOGIN_TOKEN);
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }

                break;
        }
    }

    private void showPop() {
        cameraPopu = new CameraPopu(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.camera:
                        String state = Environment.getExternalStorageState();
                        if (state.equals(Environment.MEDIA_MOUNTED)) {
                            createNewFile();
                            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file_photo));
                            startActivityForResult(getImageByCamera, REQUEST_CODE_CAMERA);

                        } else {
                            Toast.makeText(getActivity(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
                        }

                        cameraPopu.dismiss();
                        break;

                    case R.id.photo:
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, REQUEST_CODE_PHOTO);

                        cameraPopu.dismiss();
                        break;
                    case R.id.cancle:
                        cameraPopu.dismiss();
                        break;
                }
            }
        });
        cameraPopu.showAtLocation(root, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //相机
            case REQUEST_CODE_CAMERA:
                if(resultCode == RESULT_OK){
  /*
                * 默认情况下，即不需要指定intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);照相机有自己默认的存储路径，拍摄的照片将返回一个缩略图。如果想访问原始图片，可以通过dat extra能够得到原始图片位置。即，如果指定了目标uri，data就没有数据，如果没有指定uri，则data就返回有数据！*/
                    Uri uri = Uri.fromFile(file_photo);
                    startActivityForResult(cropImageUri(uri, 480, 480), 103);
                }

                break;

//          相册
            case REQUEST_CODE_PHOTO:
                if (data != null) {
                    Uri uri1 = data.getData();
                    startActivityForResult(cropImageUri(uri1, 480, 480), 103);
                }
                break;

            //裁剪
            case 103:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // 读取uri所在的图片
                        if (Uri.fromFile(file_photo) != null) {

                            rv_touxiang.destroyDrawingCache(); // 上传成功以后，清除图片缓存
                            Glide.with(this)
                                    .load(Uri.fromFile(file_photo))
                                    .error(R.mipmap.head_portrait)
                                    .into(rv_touxiang);

//                   上传头像到服务器
                            userPresenter.uploadImg(managerId, file_photo, this);
                        }
                    }
                }

                break;

            case 400:

                if (resultCode == RESULT_OK) {
                    String userName = data.getStringExtra("userName");
                    tv_name.setText(userName);
                }

                break;
        }
    }


    @Override
    public void setUserData(MineBean userProfile) {

        if (userProfile.result_code != null && userProfile.result_code.equals("200")) {

            Glide.with(this)
                    .load(userProfile.result.photo)
                    .error(R.mipmap.head_portrait)
                    .into(rv_touxiang);

//            tv_name.setText(userProfile.result.name);
        } else {
            ToastUtils.show(getActivity(), userProfile.result_msg, Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void uploadImg(KongBean kongBean) {

        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {

            ToastUtils.show(getActivity(), "上传成功", Toast.LENGTH_SHORT);

        } else {
            ToastUtils.show(getActivity(), kongBean.result_msg, Toast.LENGTH_SHORT);
        }
    }
}
