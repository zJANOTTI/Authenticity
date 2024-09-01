package com.authenticity.Responses;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestJsonUtil {

    public static String sendPostRequest(String service, RequestJson request) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://192.168.1.110:8090/" + service);
            urlConnection = (HttpURLConnection) url.openConnection();

            // Configure connection properties
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setDoOutput(true);

            // Write the request body
            OutputStream os = new BufferedOutputStream(urlConnection.getOutputStream());
            String json = null;
            try {
                json = request.toJSON();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            os.write(json.getBytes());
            os.flush();
            os.close();

            // Check response code and handle input stream
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                return "Error: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
