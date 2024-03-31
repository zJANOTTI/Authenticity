package com.authenticity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.authenticity.Message;
import com.authenticity.MessageRetriever;
import com.authenticity.Responses.RestJsonUtil;
import com.authenticity.Responses.VolleyResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements VolleyResponse {

    private static final int PERMISSION_REQUEST_CODE = 123;
    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageTextView = findViewById(R.id.messageTextView);

        // Check if permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, PERMISSION_REQUEST_CODE);
            return;
        }

        retrieveAndDisplayMessages();
    }

    private void retrieveAndDisplayMessages() {
        List<Message> messages = MessageRetriever.retrieveMessages(this);
        if (!messages.isEmpty()) {
            StringBuilder messageBuilder = new StringBuilder();
            for (Message message : messages) {
                messageBuilder.append("From: ").append(message.getSender()).append("\n").append("Message: ").append(message.getMessageBody()).append("\n\n");
                MessageAuthenticityRequest request = new MessageAuthenticityRequest();
                request.sender = message.getSender();
                request.messageBody = message.getMessageBody();
                RestJsonUtil.postJson(request, new MessageAuthenticityResponse(), Volley.newRequestQueue(this), "messages", this);
            }
            messageTextView.setText(messageBuilder.toString());
            return;
        }

        Toast.makeText(this, "No messages found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                retrieveAndDisplayMessages();
                return;
            }

            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponseSuccess(String json) {

    }

    @Override
    public void onResponseError(VolleyError error) {

    }
}
