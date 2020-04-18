package com.example.appnhaconline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appnhaconline.Activity.DanhsachbaihatActivity;
import com.example.appnhaconline.Activity.DanhsachcacplaylistActivity;
import com.example.appnhaconline.Adapter.PlaylistAdapter;
import com.example.appnhaconline.Model.Playlist;
import com.example.appnhaconline.R;
import com.example.appnhaconline.Service.APIRetrofitClient;
import com.example.appnhaconline.Service.APIService;
import com.example.appnhaconline.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lvplaylist;
    TextView txttitleplaylist, txtmoreplaylist;
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> mangplaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        lvplaylist = view.findViewById(R.id.listviewplaylist);
        txttitleplaylist = view.findViewById(R.id.textviewtitleplaylist);
        txtmoreplaylist = view.findViewById(R.id.textviewmoreplaylist);
        GetData();
        txtmoreplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachcacplaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                mangplaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,mangplaylist);
                lvplaylist.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(lvplaylist);
                lvplaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                        intent.putExtra("itemplaylist",mangplaylist.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
