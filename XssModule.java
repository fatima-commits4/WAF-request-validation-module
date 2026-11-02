package modules;

import model.CustomHttpRequest;

public class XssModule implements SecurityModule {

    @Override
    public boolean isBlocked(CustomHttpRequest request) {

        String data = (request.getPath() + request.getBody()).toLowerCase();

        return data.contains("<script>") || data.contains("</script>") || data.contains("javascript:") || data.contains("onerror=") || data.contains("<img") || data.contains("alert(");
    }

    @Override
    public String getModuleName() {
        return "XSS Module";
    }
}