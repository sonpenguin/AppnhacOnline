package com.example.appnhaconline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appnhaconline.Model.Playlist;
import com.example.appnhaconline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    //class lưu giá trị ánh xạ lần đầu, lần 2 thì đã có giá trị và chỉ cần gán giá trị, không cần khởi tạo lại
    class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackground,imgplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.txttenplaylist = convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgbackground = convertView.findViewById(R.id.imageviewbackgoundplaylist);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhnen()).into(viewHolder.imgbackground);
        Picasso.with(getContext()).load(playlist.getHinhicon()).into(viewHolder.imgplaylist);
        viewHolder.txttenplaylist.setText(playlist.getTenPlaylist());
        return convertView;
    }
}
