package com.authenticity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.authenticity.Responses.RestJsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 123;
    private TextView messageTextView;
    private TextView refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        messageTextView = findViewById(R.id.messageTextView);
        refreshButton = findViewById(R.id.refresh);

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
            SpannableStringBuilder messageBuilder = new SpannableStringBuilder();
            for (Message message : messages) {
                MessageAuthenticityRequest request = new MessageAuthenticityRequest();
                request.sender = message.getSender().replaceAll("[^a-zA-Z0-9]", "");
                request.messageBody = message.getMessageBody();
                String response = RestJsonUtil.sendPostRequest("messages", request);

                JSONObject objJSON = null;
                String authentication = "Numero Suspeito";
                try {
                    objJSON = new JSONObject(response);
                    int status = objJSON.getInt("status");

                    if (status == 1) {
                        String company = objJSON.getString("company");
                        authentication = "Numero Autênticado\n" + "Empresa: " + company;
                    }

                    if (status == 2) {
                        String company = "Não indentificada";
                        authentication = "Autênticado por mensagem\n" + "Empresa: " + company;
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                SpannableString span = colorImplementation(authentication);

                messageBuilder.append("Autenticação: ").append(span);
                messageBuilder.append("\nDe: ").append(message.getSender()).append("\n").append("Mensagem: ").append(message.getMessageBody()).append("\n\n");

            }
            messageTextView.setText(messageBuilder);
            return;
        }

        Toast.makeText(this, "Mensagens não encontradas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                retrieveAndDisplayMessages();
                return;
            }

            Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
        }
    }

    public void refresh(View v) {
        refreshButton.setEnabled(false);
        retrieveAndDisplayMessages();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                refreshButton.setEnabled(true);
            }
        };
        scheduler.schedule(task, 60, TimeUnit.SECONDS);
        scheduler.shutdown();
    }

    public SpannableString colorImplementation(String text) {
        SpannableString spannable = new SpannableString(text);

        if (text.contains("Autênticado por mensagem")) {
            spannable.setSpan(new ForegroundColorSpan(Color.rgb(255, 128, 0)), 0, 24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannable;
        }

        int color = text.equals("Numero Suspeito") ? Color.RED : Color.rgb(0, 210, 0);
        int length = text.equals("Numero Suspeito") ? 15 : 18;
        spannable.setSpan(new ForegroundColorSpan(color), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }
}
