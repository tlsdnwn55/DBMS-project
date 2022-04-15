package com.shashank.platform.furnitureecommerceappui;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MediaListviewAdapter extends BaseAdapter {
    ArrayList<MediaInfo> mediaInfos ;

    public  MediaListviewAdapter (ArrayList<MediaInfo> mediaInfos){
        this. mediaInfos =mediaInfos;
    }

    public static  class MediaViewholder{
        TextView title, uploadtime, videotime, hit, likenum, profit;
    }

    @Override
    public int getCount() {
        return mediaInfos.size();
    }


    @Override
    public Object getItem(int position) {
        return mediaInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MediaViewholder holder =new MediaViewholder();

        convertView =LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_medialist,parent,false);

        holder.title =convertView.findViewById(R.id.video_title_tv);
        holder.uploadtime = convertView.findViewById(R.id.video_uploadtime_tv);
        holder.videotime =convertView.findViewById(R.id.video_time_tv);
        holder.hit =convertView.findViewById(R.id.video_hit_tv);
        holder.likenum =convertView.findViewById(R.id.video_likenum_tv);
        holder.profit =convertView.findViewById(R.id.video_profit_tv);

        holder.title.setText(mediaInfos.get(position).getTitle().trim());
        holder.uploadtime.setText(mediaInfos.get(position).getUploadtime().trim());
        holder.videotime.setText(mediaInfos.get(position).getVediotime().trim());
        holder.hit.setText(mediaInfos.get(position).getHit().trim());
        holder.likenum.setText(mediaInfos.get(position).getLikenum().trim());
        holder. profit.setText(mediaInfos.get(position).getProfit().trim());
        return convertView;
    }
}