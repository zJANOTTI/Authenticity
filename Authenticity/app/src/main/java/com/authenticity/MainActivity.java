package com.authenticity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.authenticity.Responses.RestJsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 123;
    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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
                MessageAuthenticityRequest request = new MessageAuthenticityRequest();
                request.sender = message.getSender().replaceAll("[^a-zA-Z0-9]", "");
                request.messageBody = message.getMessageBody();
                String response = RestJsonUtil.sendPostRequest("messages", request);

                JSONObject objJSON = null;
                String authentication = "Failed";
                try {
                    objJSON = new JSONObject(response);
                    Boolean status = objJSON.getBoolean("status");

                    if (status) {
                        String company = objJSON.getString("company");
                        authentication = "Completed\n" + "Company: " + company;
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                messageBuilder.append("Authentication: ").append(authentication);
                messageBuilder.append("\nFrom: ").append(message.getSender()).append("\n").append("Message: ").append(message.getMessageBody()).append("\n\n");

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

    public void refresh(View v) {
        retrieveAndDisplayMessages();
    }
}
