package com.shashank.platform.furnitureecommerceappui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {


//    android.content.Intent Intent = getIntent();
//    String creator_id = Intent.getStringExtra("creator_id");
    TextView email,join_date,Nickname,Sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
       // getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        email = findViewById(R.id.email);
        join_date = findViewById(R.id.join_date);
        Nickname = findViewById(R.id.Nickname);
        Sub = findViewById(R.id.Sub);

        try{
            DBTask creator_get = new DBTask();
            String rMsg = creator_get.execute("creator_info","1").get();

        }catch (Exception e){}
    }


}
