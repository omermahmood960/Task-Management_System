package async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationExecutor {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    public static ExecutorService getExecutor() {
        return executor;
    }
    public static void shutDown(){
        executor.shutdown();
    }
}