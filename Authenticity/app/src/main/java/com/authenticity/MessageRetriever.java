package com.authenticity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.android.volley.toolbox.Volley;
import com.authenticity.Responses.RestJsonUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MessageRetriever {

    public static List<Message> retrieveMessages(Context context) {
        List<Message> messages = new ArrayList<>();
        Uri uri = Uri.parse("content://sms/inbox");

        // Limit the query to the 25 most recent messages
        String sortOrder = "date DESC LIMIT 15";
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, sortOrder);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String sender = cursor.getString(cursor.getColumnIndex("address")); // Phone number
                String messageBody = cursor.getString(cursor.getColumnIndex("body")); // Text
                messages.add(new Message(sender, messageBody));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return messages;
    }

}
