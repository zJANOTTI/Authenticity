package com.authenticity.Responses;

import com.android.volley.VolleyError;

public interface VolleyResponse {
    void onResponseSuccess(String json);
    void onResponseError(VolleyError error);
}