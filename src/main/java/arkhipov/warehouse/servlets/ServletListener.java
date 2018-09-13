package arkhipov.warehouse.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.*;

@WebListener
public class ServletListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1000, Integer.MAX_VALUE, 50000L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1000));
        servletContextEvent.getServletContext().setAttribute("executor",
                executor);

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent
                .getServletContext().getAttribute("executor");
        executor.shutdown();
    }
}
