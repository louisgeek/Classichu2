package com.classichu.classichu2.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

/**
 * Created by louisgeek on 2017/9/4.
 */
public class UploadFileHelper {
    public static String  uploadFile(String urlStr,File file,Map<String, String> mapParam) throws Exception{
        String result = null;
        //相关属性
        String  boundary =  UUID.randomUUID().toString();  //数据分隔标志
        String  twoHyphens = "--";
        String  line_end = "\r\n";//回车换行

        if (!file.exists()){
            return result;
        }
        //相关配置
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(60*1000);
        conn.setConnectTimeout(60*1000);
        conn.setDoInput(true);  //允许输入流
        conn.setDoOutput(true); //允许输出流
        conn.setUseCaches(false);  //不允许使用缓存
        conn.setRequestMethod("POST");  //请求方式
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        //表示要上传文件
        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);//固定格式

        //配置数据写入输出流
        DataOutputStream dos=new DataOutputStream(conn.getOutputStream());

        //文本参数
        if(mapParam!=null&&!mapParam.isEmpty()){
            for(String key:mapParam.keySet()){
                String value=mapParam.get(key);
                //开始标志 "--boundary\r\n"
                dos.writeBytes(twoHyphens + boundary + line_end);
                dos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + line_end);
                dos.writeBytes(line_end);
                dos.writeBytes(value);
                dos.writeBytes(line_end);
            }
        }

        //文件
        //开始标志 "--boundary\r\n"
        dos.writeBytes(twoHyphens + boundary + line_end);
        dos.writeBytes("Content-Disposition: form-data; name=\"file_name\"; filename=\""
                + file.getName() + "\"" + line_end);
        dos.writeBytes(line_end);//


        //文件输入流读取文件数据
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len;
        while((len=fis.read(bytes))!=-1)
        {
            dos.write(bytes, 0, len);//文件数据写入输出流
        }
        dos.writeBytes(line_end);//
        //结束标志 "--boundary--\r\n"
        dos.writeBytes(twoHyphens + boundary + twoHyphens + line_end);//
        dos.flush();
        fis.close();


        StringBuffer stringBuffer=new StringBuffer();
        if (conn.getResponseCode()==200) {//请求成功
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            result=stringBuffer.toString();

            bufferedReader.close();
        }else {
            System.err.printf("uploadFile错误code:"+conn.getResponseCode());
        }
        return  result;
    }

    public static  boolean downloadFile(String urlStr,String webFilePath,String savePath) throws Exception{
       boolean isSuccess=false;
        URL url = new URL(urlStr+"?WJLJ="+ Base64.encodeToString(webFilePath.getBytes(),Base64.NO_WRAP));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(60*1000);
        conn.setConnectTimeout(60*1000);
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        if (conn.getResponseCode()==200) {
            File saveFile = new File(savePath);
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(saveFile);

            //得到输入流
            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);

            }
            is.close();
            fos.flush();
            fos.close();

            isSuccess=true;
        }
        return  isSuccess;
    }


    public static Bitmap getNetWorkBitmap(String urlString) {
        URL imgUrl = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(urlString);
            // 使用HttpURLConnection打开连接
            HttpURLConnection urlConn = (HttpURLConnection) imgUrl
                    .openConnection();
            urlConn.setDoInput(true);
            urlConn.connect();
            // 将得到的数据转化成InputStream
            InputStream is = urlConn.getInputStream();
            // 将InputStream转换成Bitmap
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
