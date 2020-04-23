package com.example.appnhaconline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.appnhaconline.Activity.DanhsachbaihatActivity;
import com.example.appnhaconline.Activity.DanhsachtatcachudeActivity;
import com.example.appnhaconline.Activity.DanhsachtheloaitheochudeActivity;
import com.example.appnhaconline.Model.ChuDe;
import com.example.appnhaconline.Model.ChudeTheloai;
import com.example.appnhaconline.Model.Playlist;
import com.example.appnhaconline.Model.TheLoai;
import com.example.appnhaconline.R;
import com.example.appnhaconline.Service.APIRetrofitClient;
import com.example.appnhaconline.Service.APIService;
import com.example.appnhaconline.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Chude_Theloai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthemchudetheloai = view.findViewById(R.id.textviewxemthemchudetheloai);
        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<ChudeTheloai> callback = dataservice.GetDataChudeTheloai();
        callback.enqueue(new Callback<ChudeTheloai>() {
            @Override
            public void onResponse(Call<ChudeTheloai> call, Response<ChudeTheloai> response) {
                ChudeTheloai chudeTheloai = response.body();
                //Tao mang lay du lieu cua mang chu de
                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chudeTheloai.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(chudeTheloai.getTheLoai());
                //Tao viewgroup để chứa giá trị hình ảnh, lấy từ viewgroup đưa vào horizontal sau.
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                //set kich htuoc truoc khi dua vao cardview
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 350);
                layout.setMargins(10, 20, 10, 30);
                for (int i = 0; i < (chuDeArrayList.size()); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChude() != null) {
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChude()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude", chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for (int j = 0; j < (chuDeArrayList.size()); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheloai() != null) {
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheloai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<ChudeTheloai> call, Throwable t) {

            }
        });
    }
}
