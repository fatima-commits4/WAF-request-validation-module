package modules;

import model.CustomHttpRequest;

public class SqlInjectionModule implements SecurityModule {

    @Override
    public boolean isBlocked(CustomHttpRequest request) {

        String data = (request.getPath() + request.getBody()).toLowerCase();

        return data.contains("' or 1=1") || data.contains("union select") || data.contains("--") || data.contains("drop table") || data.contains("' or '1'='1");
    }

    @Override
    public String getModuleName() {
        return "SQL Injection Module";
    }
}