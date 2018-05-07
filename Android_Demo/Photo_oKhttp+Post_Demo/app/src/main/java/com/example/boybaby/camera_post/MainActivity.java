package com.example.boybaby.camera_post;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;



/**
 * Created by Boy Baby on 2018/4/8.
 *  页面：保养设备
 */

public class MainActivity extends Activity {
    //声明变量
    private static final String TAG = "MaintenanceActivity";
    private static String photo_URL = "http://192.168.43.145:8080/javaweb1/QPhotoServlet";//服务器API
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "mms" ;//获取自定义SD路径
    private File photo_file=new File(path);//生成该路径的文件
    private String photoPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //添加okhttp对象
        OkHttpClient mOkHttpClent = new OkHttpClient();
    }
    /**\
     * 功能：拍照 （启动相机）
     * @param view
     */
    public void Btn_photo(View view){
        //判断该路径文件在SD卡中是否存在
        if (!photo_file.exists()) {
            photo_file.mkdirs();
        }
        photo_file = new File(path, "/temp.jpg");
        photoPath = path + "/temp.jpg";
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (photo_file != null) {
            //启动相机拍照
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo_file));
            startActivityForResult(captureIntent, 1); // 参数 1 作为页面标识
        }
    }

    //回调方法
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            //这中情况下 data是为null的，因为自定义了路径 所以通过这个路径来获取
            Bitmap smallBitmap = BitmapUtil.getSmallBitmap(photoPath);
            //Android版本不同所用到的MultipartBody  MultipartBuilder对象不同  我用的版本android 3.0以上 所以用的MultipartBuilder
            MultipartBuilder multipartBuilder = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("img", "temp.jpg",    //第一个参数是标识  服务器端通过该标识拿到照片
                    RequestBody.create(MediaType.parse("image/png"), photo_file));
            RequestBody requestBody = multipartBuilder.build();
            Request request = new Request.Builder()
                    .url(photo_URL)     //服务器URL
                    .post(requestBody)
                    .build();
            executeRequest(request);
        }
    }
    //服务器回调
    private void executeRequest(Request request) {

        //3.将Request封装为Call
        com.squareup.okhttp.Call call = new OkHttpClient().newCall(request);
        //4.执行call
        call.enqueue(new Callback() {

            @Override//回调错误时
            public void onFailure(Request request, IOException e) {
                Toast.makeText(MainActivity.this,"网络连接失败！",Toast.LENGTH_LONG).show();
            }

            @Override//回调成功时
            public void onResponse(Response response) throws IOException {

                final String relsult = response.body().string();//接收服务器返回来的信息
                try {
                    JSONObject jsonObject = new JSONObject(relsult);
                    String rel = jsonObject.getString("result");
                    if(rel.equals("1")){

                    }else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    /**
//     * 文件上传
//     *
//     * @param urlStr 接口路径
//     * @param filePath 本地图片路径
//     * @return
//     */
//    public static String formUpload(String urlStr, String filePath,File photo_file1) {
//        String rsp = "";
//        HttpURLConnection conn = null;
//        String BOUNDARY = "|"; // request头和上传文件内容分隔符
//        try {
//            URL url = new URL(urlStr);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setConnectTimeout(5000);
//            conn.setReadTimeout(30000);
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            conn.setRequestProperty("User-Agent",
//                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
//            conn.setRequestProperty("Content-Type",
//                    "multipart/form-data; boundary=" + BOUNDARY);
//
//            OutputStream out = new DataOutputStream(conn.getOutputStream());
//            String filename ="";
//            try {
//                //File file = new File(filePath);
//                 filename = photo_file1.getName();
//            }catch (Exception e)
//            {
//
//            }
//            String contentType = "";
//            if (filename.endsWith(".png")) {
//                contentType = "image/png";
//            }
//            if (filename.endsWith(".jpg")) {
//                contentType = "image/jpg";
//            }
//            if (filename.endsWith(".gif")) {
//                contentType = "image/gif";
//            }
//            if (filename.endsWith(".bmp")) {
//                contentType = "image/bmp";
//            }
//            if (contentType == null || contentType.equals("")) {
//                contentType = "application/octet-stream";
//            }
//            StringBuffer strBuf = new StringBuffer();
//            strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
//            strBuf.append("Content-Disposition: form-data; name=\"" + filePath
//                    + "\"; filename=\"" + filename + "\"\r\n");
//            strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
//            out.write(strBuf.toString().getBytes());
//            DataInputStream in = new DataInputStream(new FileInputStream(photo_file1));
//            int bytes = 0;
//            byte[] bufferOut = new byte[1024];
//            while ((bytes = in.read(bufferOut)) != -1) {
//                out.write(bufferOut, 0, bytes);
//            }
//            in.close();
//            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
//            out.write(endData);
//            out.flush();
//            out.close();
//
//            // 读取返回数据
//            StringBuffer buffer = new StringBuffer();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line).append("\n");
//            }
//            rsp = buffer.toString();
//
//            reader.close();
//            reader = null;
//        } catch (Exception e) {
//            String s=e.getMessage();
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//                conn = null;
//            }
//        }
//        return rsp;
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("photoPath", photoPath);
//        Log.d(TAG, "onSaveInstanceState");
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        if (TextUtils.isEmpty(photoPath)) {
//            photoPath = savedInstanceState.getString("photoPath");
//        }
//        Log.d(TAG, "onRestoreInstanceState");
//    }
}
