package com.authenticity.Responses;

import org.json.JSONException;

public interface ResponseJson {
    void fromJSON(String json) throws JSONException;
}
