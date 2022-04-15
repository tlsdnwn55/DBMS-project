package com.shashank.platform.furnitureecommerceappui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText username,pwd;
    Button test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBTask DB = new DBTask();
                String result = null;
                String type = "login";
                String id = username.getText().toString();
                String pass = pwd.getText().toString();
                try {
                    result = DB.execute(type,id, pass).get();
                    Log.i("result : ",result);
                    StringTokenizer token = new StringTokenizer(result,"%");

                    if (token.nextToken().equals("true")) {
                        String creator_id = token.nextToken();
                        Toast.makeText(MainActivity.this,"로그인 하셨습니다.",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                        intent.putExtra("creator_id",creator_id);
                        startActivity(intent);
                    }
                     else if(result.equals("noId")) {
                        Log.i("result : ",result);
                        Toast.makeText(MainActivity.this,"아이디 or 비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {Toast.makeText(MainActivity.this,"오류",Toast.LENGTH_SHORT).show(); }
                /*
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);*/
            }
        });





    }
}
