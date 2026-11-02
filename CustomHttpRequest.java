package model;

import com.sun.net.httpserver.HttpExchange;

import java.io.InputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class CustomHttpRequest {

    private String method;
    private String path;
    private String body;
    private Map<String, List<String>> headers;
    private String ip;

    public CustomHttpRequest(HttpExchange exchange) throws IOException {

        this.method = exchange.getRequestMethod();
        this.path = exchange.getRequestURI().toString();
        this.headers = exchange.getRequestHeaders();
        this.ip = exchange.getRemoteAddress().getAddress().getHostAddress();

        InputStream is = exchange.getRequestBody();
        String rawBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        this.body = URLDecoder.decode(rawBody, StandardCharsets.UTF_8);
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public String getIp() {
        return ip;
    }
}