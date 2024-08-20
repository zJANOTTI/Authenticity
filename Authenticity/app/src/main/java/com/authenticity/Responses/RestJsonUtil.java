package com.authenticity.Responses;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

public class RestJsonUtil {
    public static void postJson(RequestJson request, ResponseJson response, RequestQueue queue, String service, VolleyResponse activity) {
        //@HINT url is based on your local host.
        String url = "http://192.168.1.110:8090/" + service;
        String json;
        try {
            json = request.toJSON();
        } catch (Exception ex) {
            ex.printStackTrace();
            activity.onResponseError(null);
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                success -> activity.onResponseSuccess(success), error -> activity.onResponseError(error)) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return json.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding. Bytes of %s using %s", json, "utf-8");
                    return null;
                }
            }
        };
        queue.add(stringRequest);
    }

    public static void getJson( ResponseJson response, RequestQueue queue, String service, VolleyResponse volley) {
        String url = "http://192.168.1.110:8090/" + service;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                success -> volley.onResponseSuccess(success), error -> volley.onResponseError(error));
        queue.add(stringRequest);
    }
}
