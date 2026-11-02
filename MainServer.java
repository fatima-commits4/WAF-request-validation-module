import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import model.CustomHttpRequest;
import modules.*;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class MainServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        List<SecurityModule> modules = List.of(
                new RateLimitModule(),
                new SqlInjectionModule(),
                new XssModule(),
                new RequestValidationModule()
        );

        server.createContext("/", (HttpExchange exchange) -> {

            CustomHttpRequest request = new CustomHttpRequest(exchange);

            System.out.println("\nRequest: " + request.getMethod() + " " + request.getPath());

            for (SecurityModule module : modules) {
                if (module.isBlocked(request)) {

                    String response = "Blocked by: " + module.getModuleName();

                    System.out.println("BLOCKED by " + module.getModuleName());

                    exchange.sendResponseHeaders(403, response.length());

                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes());
                    }

                    return; //(do NOT forward)
                }
            }

            // If not blocked 
            System.out.println("ALLOWED, forwarding to backend");

            byte[] backendResponse = util.HttpForwarder.forward(request);

            exchange.sendResponseHeaders(200, backendResponse.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(backendResponse);
            }
        });

        server.start();
        System.out.println("WAF running on http://localhost:8080");
    }
}