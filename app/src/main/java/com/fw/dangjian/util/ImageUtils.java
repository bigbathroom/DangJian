package com.fw.dangjian.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Administrator on 2017/10/9.
 */

public class ImageUtils {


    public static String capturePath;
    public static File file_photo;

    /*
     *创建一个.jpg文件
  */
    public static void createNewFile() {
        capturePath = Environment.getExternalStorageDirectory()+ "/" + DateFormat.format("yyyyMMddhhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
        file_photo = new File(capturePath);
        try {
            if(file_photo.exists()) {
                file_photo.delete();
            }
            file_photo.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// 裁剪图片
    public static Intent cropImageUri(Uri uri, int outputX, int outputY) {

        createNewFile();
        Uri crop_Uri = Uri.fromFile(file_photo);

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        //裁剪框比例
        intent.putExtra("aspectX",outputX);
        intent.putExtra("aspectY",outputY);
        //图片输出大小
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, crop_Uri);
        return  intent;
    }

    public void setPicToView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Log.i("SS", photo.toString());

//           * 获得图片
//            iv_touxiang.setImageBitmap(photo);

            Drawable drawable = new BitmapDrawable(photo);
// draw转换为String
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 60, stream);
            byte[] b = stream.toByteArray();

          /*  Glide.with(this)
                    .load(b)
                    .error(R.mipmap.my_photo0)
                    .into(iv_touxiang);*/

//            updateHeadToReserver(Bitmap2StrByBase64(photo));
        }
    }


    public String Bitmap2StrByBase64(Bitmap bit){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }


   /**
     * 将file文件转化为byte数组
     *
     * @param filePath
     * @return
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
