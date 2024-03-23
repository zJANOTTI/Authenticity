package com.authenticity;

import com.authenticity.Responses.RequestJson;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageAuthenticityRequest implements RequestJson {
    public String sender;
    public String messageBody;

    //@TODO this is just an example, adjust it based on Message retriever class.
    public String toJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("sender", sender);
        obj.put("messageBody", messageBody);
        return obj.toString();
    }
}
