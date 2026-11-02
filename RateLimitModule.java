package modules;

import model.CustomHttpRequest;

import java.util.*;

public class RateLimitModule implements SecurityModule {

    private static final int LIMIT = 5;
    private static final long WINDOW = 10000; // 10 seconds

    private Map<String, List<Long>> requests = new HashMap<>();

    @Override
    public boolean isBlocked(CustomHttpRequest request) {
        String ip = request.getIp();
        long now = System.currentTimeMillis();

        requests.putIfAbsent(ip, new ArrayList<>());

        List<Long> timestamps = requests.get(ip);

        timestamps.removeIf(time -> now - time > WINDOW);
        timestamps.add(now);

        return timestamps.size() > LIMIT;
    }

    @Override
    public String getModuleName() {
        return "Rate Limiting Module";
    }
}