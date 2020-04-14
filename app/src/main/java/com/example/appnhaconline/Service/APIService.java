package com.example.appnhaconline.Service;

public class APIService {
    private static String base_url = "https://appnhacbuicaoson.000webhostapp.com/FilePhp/";

    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);

    }
}
