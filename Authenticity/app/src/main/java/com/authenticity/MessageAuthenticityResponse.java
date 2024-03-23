package com.authenticity;

import com.authenticity.Responses.ResponseJson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MessageAuthenticityResponse implements ResponseJson {
    public String sender;
    public String messageBody;

    public void fromJSON(String json) throws JSONException {
        JSONObject obj = (JSONObject) new JSONTokener(json).nextValue();
        sender = obj.getString("sender");
        messageBody = obj.getString("messageBody");
    }
}

