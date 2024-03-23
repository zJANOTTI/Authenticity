package com.authenticity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class MessageRetriever {

    public static List<Message> retrieveMessages(Context context) {
        List<Message> messages = new ArrayList<>();
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);

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