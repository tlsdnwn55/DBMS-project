package com.shashank.platform.furnitureecommerceappui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2Activity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<PlaylistInfo> playlistInfos  =new ArrayList<>();

    ListView mListView;
    ArrayList<MediaInfo> mediaInfos =new ArrayList<>();
    ArrayList<MediaInfo> total_mediaInfos = new ArrayList<>();
    MediaListviewAdapter mediaListviewAdapter ;
    String rMsg = "";
    String rMsg2 = "";



    String check_playlist_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar mytoolbar =findViewById(R.id.main2_appbar);
        setSupportActionBar(mytoolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_power_24dp);

        Intent Intent = getIntent();
        String creator_id = Intent.getStringExtra("creator_id");

        //check_playlist_id =0;
        mRecyclerView = findViewById(R.id.playlist_recycler_View);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mListView =findViewById(R.id.media_listview);
        mediaListviewAdapter = new MediaListviewAdapter(mediaInfos);
        mListView.setAdapter(mediaListviewAdapter);

        try{
            DBTask playlist_get = new DBTask();
            rMsg = playlist_get.execute("playlist_get",creator_id).get();
            StringTokenizer playlist = new StringTokenizer(rMsg,"%");
            while (playlist.hasMoreTokens() ){
                String id = playlist.nextToken();
                String title = playlist.nextToken();
                String Total_profit = playlist.nextToken();
                final PlaylistInfo temp = new PlaylistInfo(id,title,Total_profit);
                playlistInfos.add(temp);
                try{
                    DBTask media_get = new DBTask();
                    rMsg2 = media_get.execute("media_get",id).get();
                    Log.i("리스트",rMsg2);
                    StringTokenizer medialist = new StringTokenizer(rMsg2,"%");
                    while (medialist.hasMoreTokens()){
                        final MediaInfo temp2 = new MediaInfo(medialist.nextToken(),medialist.nextToken(),medialist.nextToken(),medialist.nextToken(),medialist.nextToken(),medialist.nextToken(),id
                        ,medialist.nextToken(),medialist.nextToken(),medialist.nextToken());
                        total_mediaInfos.add(temp2);
                    }
                }
                catch (Exception e){}
            }
        }
        catch (Exception e){}


        RecyclerAdapter recyclerAdapter =new RecyclerAdapter(playlistInfos);

        mRecyclerView.setAdapter(recyclerAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View child = mRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                int position = recyclerView.getChildAdapterPosition(child);
                check_playlist_id = playlistInfos.get(position).getPlaylist_id().trim();
                    mediaInfos.clear();
                for(int i=0; i<total_mediaInfos.size() ; i++){
                    if(total_mediaInfos.get(i).getPlaylist_id().trim().equals(check_playlist_id)){
                        mediaInfos.add(total_mediaInfos.get(i));
                    }else continue;
                    mediaListviewAdapter.notifyDataSetChanged();
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }
        });

/*        if(mediaInfos.isEmpty()) {
            for (int i = 0; i < total_mediaInfos.size(); i++) {
                int k = Integer.parseInt(total_mediaInfos.get(i).getPlaylist_id());
                if (k == 1) {
                    mediaInfos.add(total_mediaInfos.get(i));
                } else continue;
            }}
*/

        Button graph_btn = findViewById(R.id.graph_btn);

        graph_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Main2Activity.this, GraphActivity.class);
                Bundle bundle =new Bundle();

                ArrayList<String> profit =new ArrayList<>();
                ArrayList<String> title = new ArrayList<>();
                for(int i=0; i<playlistInfos.size(); i++){
                    profit.add(playlistInfos.get(i).getTotal_profit());
                    title.add(playlistInfos.get(i).getPlaylist_title());
                }
                bundle.putStringArrayList("profit",profit);
                bundle.putStringArrayList("title",title);
                intent.putExtra("bundle",bundle);
                startActivity(intent);

            }
        });

        Button graph2_btn = findViewById(R.id.graph2_btn);
        graph2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Main2Activity.this, Graph2Activity.class);
                Bundle bundle =new Bundle();

                ArrayList<MediaInfo> m = new ArrayList<>();
                if(check_playlist_id ==null) check_playlist_id ="1";

                for(int i=0; i<total_mediaInfos.size() ; i++){
                    if(total_mediaInfos.get(i).getPlaylist_id().trim().equals(check_playlist_id)){
                       m.add(total_mediaInfos.get(i));
                    }else continue;
                }


                ArrayList<String> profit =new ArrayList<>();
                ArrayList<String> title = new ArrayList<>();
                for(int i=0; i<m.size(); i++){
                    profit.add(m.get(i).getProfit());
                    title.add(m.get(i).getTitle());
                }
                bundle.putStringArrayList("profit",profit);
                bundle.putStringArrayList("title",title);
                intent.putExtra("bundle",bundle);
                startActivity(intent);

            }
        });





    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
            case R.id.main_info :
                Intent intent =new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}