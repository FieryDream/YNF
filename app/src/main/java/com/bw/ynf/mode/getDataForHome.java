package com.bw.ynf.mode;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.utils.circleimageview.urlutils.UrlUtils;

/**
 * Created by GaoJun on 2016/12/10 0010.
 */

public class getDataForHome {
    private HomeFragmentData homedata;
    private Context context;

    public getDataForHome(HomeFragmentData homedata, Context context) {
        this.homedata=homedata;
        this.context=context;
    }

    public void getDataFromNet(){
        //得到requestQueue请求队列对象
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //得到request对象
        StringRequest request = new StringRequest(UrlUtils.HOME_URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                homedata.succes(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              Log.d("------>>>","失败");

            }
        });
        requestQueue.add(request);

    }



}
