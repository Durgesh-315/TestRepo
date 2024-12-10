package dbService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class DataFetcherListener implements ServletContextListener {
    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer();
        DataFetcher dataFetcher = new DataFetcher();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dataFetcher.fetchDataAndInsert();
            }
        }, 0, 3 * 60 * 1000); // 0 delay, 3 minutes period
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (timer != null) {
            timer.cancel();
        }
    }
}
