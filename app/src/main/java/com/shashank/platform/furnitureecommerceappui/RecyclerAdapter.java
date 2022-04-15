package com.shashank.platform.furnitureecommerceappui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private View.OnClickListener onItemViewClickListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder   {
        TextView playlist_title, total_profit;
        ImageView sumnail;

        MyViewHolder(View view){
            super(view);
            sumnail = view.findViewById(R.id.sumnail);
            playlist_title = view.findViewById(R.id.cardview_title_tv);
            total_profit = view.findViewById(R.id.cardview_profit_tv);
        }
    }

    private ArrayList<PlaylistInfo> info_list ;

    public RecyclerAdapter(){};
    public RecyclerAdapter(ArrayList<PlaylistInfo> info_list){
        this.info_list= info_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_playlist,viewGroup, false);

        return new  MyViewHolder(v);
    }


    Integer ImgID[] = {R.drawable.dado1,R.drawable.dado2,R.drawable.dado3,R.drawable.dado4,R.drawable.dado5,R.drawable.dado6
           ,R.drawable.dado7,R.drawable.dado8,R.drawable.dado9,R.drawable.dado10,R.drawable.dado11,R.drawable.dado12,R.drawable.dado13,R.drawable.dado14,R.drawable.dado15,R.drawable.dado16,R.drawable.dado17};
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.playlist_title.setText(info_list.get(i).getPlaylist_title());
        myViewHolder.total_profit.setText(String.valueOf(info_list.get(i).getTotal_profit()));
        myViewHolder.sumnail.setImageResource(ImgID[i]);


    }

    @Override
    public int getItemCount() {
        return info_list.size();
    }
}
