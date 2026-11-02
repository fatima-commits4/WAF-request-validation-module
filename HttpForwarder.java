package util;

import model.CustomHttpRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpForwarder {

    public static byte[] forward(CustomHttpRequest request) throws IOException {

        URL url = new URL("http://localhost:8081" + request.getPath());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(request.getMethod());
        conn.setDoOutput(true);

        if (!request.getBody().isEmpty()) {
            OutputStream os = conn.getOutputStream();
            os.write(request.getBody().getBytes());
            os.close();
        }

        InputStream is = conn.getInputStream();
        return is.readAllBytes();
    }
}