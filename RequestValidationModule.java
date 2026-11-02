package modules;

import model.CustomHttpRequest;

public class RequestValidationModule implements SecurityModule {

    @Override
    public boolean isBlocked(CustomHttpRequest request) {

        // Block TRACE method
        if (request.getMethod().equalsIgnoreCase("TRACE")) {
            return true;
        }

        // Block suspicious User-Agent
        if (request.getHeaders().containsKey("User-Agent")) {
            String ua = request.getHeaders().get("User-Agent").toString();
            if (ua.toLowerCase().contains("sqlmap")) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getModuleName() {
        return "Request Validation Module";
    }
}