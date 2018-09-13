package arkhipov.warehouse.servlets;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.PrintWriter;

@WebListener
public class AsyncServletListener implements AsyncListener {

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("AsyncServletListener onComplete");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("AsyncServletListener onTimeout");
        ServletResponse response = event.getAsyncContext().getResponse();
        PrintWriter writer = response.getWriter();
        writer.write("TimeOut Error");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("AsyncServletListener onError");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("AsyncServletListener onStartAsync");
    }
}
