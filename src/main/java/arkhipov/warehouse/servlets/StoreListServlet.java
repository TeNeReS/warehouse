package arkhipov.warehouse.servlets;

import arkhipov.warehouse.dao.StoreDAO;
import arkhipov.warehouse.models.Store;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@WebServlet(value = "/stores", asyncSupported = true)
public class StoreListServlet extends HttpServlet {
    private StoreDAO storeDAO = new StoreDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new AsyncServletListener());

        ThreadPoolExecutor executor = (ThreadPoolExecutor) req.getServletContext().getAttribute("executor");

        executor.execute(() -> {

            List<Store> storeList = storeDAO.getAllStores();

            req.setAttribute("stores", storeList);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("stores.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                System.out.println("Error during forwarding!");
                e.printStackTrace();
            }

            asyncContext.complete();
        });
    }
}
