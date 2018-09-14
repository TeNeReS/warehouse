package arkhipov.warehouse.servlets;

import arkhipov.warehouse.dao.ProductDAO;
import arkhipov.warehouse.models.Product;

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

@WebServlet(value = "/products", asyncSupported = true)
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new AsyncServletListener());

        ThreadPoolExecutor executor = (ThreadPoolExecutor) req.getServletContext().getAttribute("executor");

        executor.execute(() -> {
            Integer storeId = Integer.valueOf(req.getParameter("storeId"));

            List<Product> productList = productDAO.getAllProductByStoreID(storeId);

            req.setAttribute("products", productList);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("products.jsp");
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
