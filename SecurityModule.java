package modules;

import model.CustomHttpRequest;

public interface SecurityModule {
    boolean isBlocked(CustomHttpRequest request);
    String getModuleName();
}