package com.fw.dangjian.view;

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
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.fw.dangjian.util.ImageUtils.createNewFile;
import static com.fw.dangjian.util.ImageUtils.cropImageUri;
import static com.fw.dangjian.util.ImageUtils.file_photo;

public class MineFragment extends BaseFragment implements UserCenterMvpView{

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
    @BindView(R.id.rl_user)
    RelativeLayout rl_user;
    @BindView(R.id.rl_psw)
    RelativeLayout rl_psw;
    @BindView(R.id.rl_spec)
    RelativeLayout rl_spec;
    @BindView(R.id.root)
    LinearLayout root;

    private CameraPopu cameraPopu;
    private static final int REQUEST_CODE_SETTING = 200;
    public static final int REQUEST_CODE_CAMERA = 11;
    private static final int REQUEST_CODE_PHOTO = 22;

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
        userPresenter.getUserProfile(managerId,this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_msg, R.id.rv_touxiang, R.id.rl_ku, R.id.rl_spec, R.id.rl_user, R.id.rl_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:

                break;
            case R.id.rv_touxiang:

                AndPermission.with(getActivity())
                        .requestCode(101)
                        .permission(
                                // 申请多个权限组方式：
                                Permission.CAMERA
                        )
                        .rationale(rationaleListener)
                        .callback(this)
                        .start();


                break;
            case R.id.rl_ku:
                if(managerId == -1){
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                }else{
                    Intent intent1 = new Intent(getActivity(), FileActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.rl_user:
                if(managerId == -1){
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                }else{
                    Intent intent2 = new Intent(getActivity(), ChangeNameActivity.class);
                    startActivityForResult(intent2,400);

                }

                break;
            case R.id.rl_psw:
                if(managerId == -1){
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                }else{
                    Intent intent3 = new Intent(getActivity(), ResetPswActivity.class);
                    startActivity(intent3);
                }
                break;
            case R.id.rl_spec:
                if(managerId == -1){
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                }else{
                    SPUtils.remove(getActivity(), ConstanceValue.LOGIN_TOKEN);
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }

                break;
        }
    }

    /**
     * Rationale支持，这里自定义对话框。
     */
    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            // 这里使用自定义对话框，如果不想自定义，用AndPermission默认对话框：
//             AndPermission.rationaleDialog(Context, Rationale).show();
            // 自定义对话框。
            AlertDialog.newBuilder(getActivity())
                    .setTitle("温馨提醒")
                    .setMessage("建议打开您的相机权限")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel();
                        }
                    }).show();
        }
    };

    // 成功回调的方法，用注解即可，这里的100就是请求时的requestCode。
    @PermissionYes(101)
    private void getPermissionYes(List<String> grantedPermissions) {
        // TODO 申请权限成功。
        showPop();
    }

    @PermissionNo(101)
    private void getPermissionNo(List<String> deniedPermissions) {
        // TODO 申请权限失败。

        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(getActivity(), deniedPermissions)) {
            // 第一种：用默认的提示语。
            AndPermission.defaultSettingDialog(getActivity(), REQUEST_CODE_SETTING).show();
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

                        Intent intentFromGallery = new Intent(Intent.ACTION_PICK, null);
                        // 设置文件类型
                        intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intentFromGallery, REQUEST_CODE_PHOTO);
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
          /*
                * 默认情况下，即不需要指定intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);照相机有自己默认的存储路径，拍摄的照片将返回一个缩略图。如果想访问原始图片，可以通过dat extra能够得到原始图片位置。即，如果指定了目标uri，data就没有数据，如果没有指定uri，则data就返回有数据！*/
                Uri uri = Uri.fromFile(file_photo);
                startActivityForResult(cropImageUri(uri, 480, 480, false, uri), 103);

                break;

//          相册
            case REQUEST_CODE_PHOTO:
                if (data != null) {
                    Uri uri1 = data.getData();
                    createNewFile();
                    Uri tempUri = Uri.fromFile(file_photo);
                    startActivityForResult(cropImageUri(uri1, 480, 480, false, tempUri), 103);
                }
                break;

            //裁剪
            case 103:
                if (data != null) {
                    // 读取uri所在的图片
                    if (Uri.fromFile(file_photo) != null) {

                        rv_touxiang.destroyDrawingCache(); // 上传成功以后，清除图片缓存

                        Glide.with(this)
                                .load(Uri.fromFile(file_photo))
                                .error(R.mipmap.head_portrait)
                                .into(rv_touxiang);

//                   上传头像到服务器
                        userPresenter.uploadImg(managerId,file_photo,this);
                    }
                }
                break;

            case 400:

                if( resultCode == RESULT_OK ) {
                    String userName = data.getStringExtra("userName");
                    tv_name.setText(userName);
                }

                break;
        }
    }



    @Override
    public void setUserData(MineBean userProfile) {

        if (userProfile.result_code!= null&&userProfile.result_code.equals("200")){

            Glide.with(this)
                    .load(userProfile.result.photo)
                    .error(R.mipmap.head_portrait)
                    .into(rv_touxiang);

            tv_name.setText(userProfile.result.name);

        }else{
            ToastUtils.show(getActivity(),userProfile.result_msg,Toast.LENGTH_SHORT);
        }


    }

    @Override
    public void uploadImg(KongBean kongBean) {

        if (kongBean.result_code!= null&&kongBean.result_code.equals("200")){
            ToastUtils.show(getActivity(),"上传成功",Toast.LENGTH_SHORT);
        }else{
            ToastUtils.show(getActivity(),kongBean.result_msg,Toast.LENGTH_SHORT);
        }
    }
}
