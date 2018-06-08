package com.fw.dangjian.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.FeedPicAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.dialog.ChangeColumnDialog;
import com.fw.dangjian.popwin.CameraPopu;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.Bimp;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.SettingService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.fw.dangjian.util.ImageUtils.createNewFile;
import static com.fw.dangjian.util.ImageUtils.cropImageUri;
import static com.fw.dangjian.util.ImageUtils.file_photo;
import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;
import static com.umeng.socialize.utils.DeviceConfig.context;

public class FaBuActivity extends BaseActivity {
    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.root)
    LinearLayout root;

    @BindView(R.id.tv_bankuai)
    TextView tv_bankuai;
    @BindView(R.id.tv_arrow)
    TextView tv_arrow;
    @BindView(R.id.tv_lanmu)
    TextView tv_lanmu;
    @BindView(R.id.tv_arrow1)
    TextView tv_arrow1;
    @BindView(R.id.tv_biaoti)
    TextView tv_biaoti;

    @BindView(R.id.et_question)
    EditText et_question;
    @BindView(R.id.rv)
    RecyclerView rv;


    public List<Bitmap> bmps = new ArrayList<>();
    public List<File> drr = new ArrayList<>();
    private String path = "";
    private Uri photoUri;

    public static final int REQUEST_CODE_CAMERA = 100;
    private static final int REQUEST_CODE_PHOTO = 200;

    FeedPicAdapter mAdapter;
    private CameraPopu cameraPopu;
    private Bitmap bmp;

    UserPresenter  userPresenter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                int position = (int) msg.obj;
                bmps.remove(position);
                drr.remove(position);
            }
        }
    };
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList1;
    @Override
    protected int fillView() {
        return R.layout.activity_fa_bu;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("");
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("发布");
            /*
         * 载入默认图片添加图片加号
         * 通过适配器实现
         */
        //获取资源图片加号
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.feed_add);
        bmps.clear();
        bmps.add(bmp);
        initData();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(layoutManager);
        userPresenter = new UserPresenter();
    }

    @Override
    protected void initData() {
        setData();

    }

    public void  setData(){
        mAdapter = new FeedPicAdapter(bmps,drr, this,handler);
        rv.setAdapter(mAdapter);

        mAdapter.setonItemClickLitener(new FeedPicAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                if (bmps.size()<7) {
                    if (position == bmps.size() - 1) { //点击图片位置为+ 0对应0张图片
                        //选择图片
                        //通过onResume()刷新数据
                        choicePic();
                    } else {

//                        deletDialog(position);
                        Intent intent = new Intent(FaBuActivity.this, BigActivity.class);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bmps.get(position).compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] bitmapByte = baos.toByteArray();
                        intent.putExtra("image", bitmapByte);
                        startActivity(intent);
                    }
                }else{
                    if (position == bmps.size() - 1) {

                    } else {
                        Intent intent = new Intent(FaBuActivity.this, BigActivity.class);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bmps.get(position).compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] bitmapByte = baos.toByteArray();
                        intent.putExtra("image", bitmapByte);
                        startActivity(intent);

//                        deletDialog(position);
                    }
                }
            }

        });
    }
    private void choicePic() {
        if (drr.size() < 6){
            AndPermission.with(this)
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

                        AlertDialog.Builder builder = new AlertDialog.Builder(FaBuActivity.this);
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

        }else{
            Toast.makeText(this,"最多上传五张", Toast.LENGTH_SHORT).show();
        }

    }



    private void showPop() {
        cameraPopu = new CameraPopu(this, new View.OnClickListener() {
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
                            Toast.makeText(FaBuActivity.this, "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
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
                            drr.add(file_photo);

                            Bitmap bitmap = Bimp.getLoacalBitmap(drr.get(drr.size() - 1));

                            if(bmps.size()==1){
                                bmps.clear();
                                bmps.add(bitmap);
                                bmps.add(bmp);
                            }else if(bmps.size()>1){
                                if(bmps.size()<7){
                                    bmps.remove(bmps.size()-1);
                                    bmps.add(bitmap);
                                    bmps.add(bmp);
                                }
                            }
                            setData();
                        }
                    }
                }

                break;
        }
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 清理图片缓存
        for (int i = 1; i < bmps.size(); i++) {
            bmps.get(i).recycle();
        }
        bmps.clear();
        drr.clear();

    }


    @OnClick({R.id.left,R.id.tv_right,R.id.tv_arrow,R.id.tv_arrow1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.tv_right:
//           TODO  发布
                break;
            case R.id.tv_arrow:

                arrayList = new ArrayList<>();
                arrayList.add("首页");
                arrayList.add("学习");
                ChangeColumnDialog mChangeAddressDialog = new ChangeColumnDialog(FaBuActivity.this,arrayList);
                mChangeAddressDialog.setAddress("首页");
                mChangeAddressDialog.show();
                mChangeAddressDialog.setAddresskListener(new ChangeColumnDialog.OnAddressCListener() {
                    @Override
                    public void onClick(String province) {
                        tv_bankuai.setText(province);
                    }
                });


                break;
            case R.id.tv_arrow1:

                arrayList1 = new ArrayList<>();
                arrayList1.add("两学一做");
                arrayList1.add("文件");
                ChangeColumnDialog mChangeAddressDialog1 = new ChangeColumnDialog(FaBuActivity.this,arrayList1);
                mChangeAddressDialog1.setAddress("两学一做");
                mChangeAddressDialog1.show();
                mChangeAddressDialog1.setAddresskListener(new ChangeColumnDialog.OnAddressCListener() {
                    @Override
                    public void onClick(String province) {
                        tv_lanmu.setText(province);
                    }
                });

                break;
        }
    }


    /*
   * Dialog对话框提示用户删除操作
   * position为删除图片位置
   */
    protected void deletDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                bmps.remove(position);
                drr.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private Rationale mRationale = new Rationale() {
        @Override
        public void showRationale(Context context, List<String> permissions,
                                  final RequestExecutor executor) {
            // 这里使用一个Dialog询问用户是否继续授权。

            AlertDialog.Builder builder = new AlertDialog.Builder(FaBuActivity.this);
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
}
