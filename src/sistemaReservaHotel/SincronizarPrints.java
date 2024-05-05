package sistemaReservaHotel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SincronizarPrints {
    private static final Object lock = new Object();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void log(String message) {
        synchronized (lock) {
            String timestamp = sdf.format(new Date());
            String threadName = Thread.currentThread().getName();
            System.out.println(timestamp + " [" + threadName + "] - " + message);
        }
    }
}