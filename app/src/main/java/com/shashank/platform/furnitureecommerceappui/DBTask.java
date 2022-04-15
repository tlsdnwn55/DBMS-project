package com.shashank.platform.furnitureecommerceappui;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DBTask extends AsyncTask<String, Void, String> {

    String sendMsg, receiveMsg;
    @Override
    // doInBackground의 매개값이 문자열 배열인데요. 보낼 값이 여러개일 경우를 위해 배열로 합니다.
    protected String doInBackground(String... strings) {
        try {
            String str;
            URL url = new URL("http://175.193.55.200:12345/DB/data.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송합니다.
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            if(strings.length == 2) sendMsg = "type="+strings[0] + "&id="+strings[1];
            if(strings.length == 3) sendMsg = "type="+strings[0]+"&id="+strings[1]+"&pwd="+strings[2];

            Log.d("sendMsg:",sendMsg+"\n");
            osw.write(sendMsg);//OutputStreamWriter에 담아 전송합니다.
            osw.flush();
            osw.close();


            //jsp와 통신이 정상적으로 되었을 때 할 코드들입니다.
            if(conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);

                StringBuffer buffer = new StringBuffer();
                //jsp에서 보낸 값을 받겠죠?
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                tmp.close();
                reader.close();
                receiveMsg = buffer.toString();

            } else {
                Log.i("통신 결과", conn.getResponseCode()+"에러");

                // 통신이 실패했을 때 실패한 이유를 알기 위해 로그를 찍습니다.
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //jsp로부터 받은 리턴 값입니다.
        return receiveMsg;
    }
}


