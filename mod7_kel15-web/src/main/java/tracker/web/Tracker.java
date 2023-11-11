package tracker.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tracker.ejb.TrackerBean;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Tracker", urlPatterns = {"/Tracker"})
public class Tracker extends HttpServlet {
    TrackerBean trackerBean = new TrackerBean();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            double total;
            double average = 0;
            int count = 0;

            if (!request.getParameter("value").isEmpty()) {
                total = trackerBean.add(Double.parseDouble(request.getParameter("value")));
            } else {
                total = trackerBean.getTotal();
            }

            if (trackerBean.getCount() != 0) {
                average = trackerBean.average();
                count = trackerBean.getCount();
            }

            out.println("Count: " + count + "<br />");
            out.println("Total: " + total + "<br />");
            out.println("Average: " + average + "<br />");
            out.println("Bilangan Genap: " + trackerBean.getBilanganGenap() + "<br />");
            out.println("Bilangan Ganjil: " + trackerBean.getBilanganGanjil() + "<br />");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            rd.include(request, response);
        } catch (IOException | NumberFormatException | ServletException ex) {
            PrintWriter out = response.getWriter();
            out.println("Error: Silahkan isi field dengan angka");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            rd.include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}