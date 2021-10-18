package org.example.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createProduct")
public class CreateProductServlet extends HttpServlet {

       private static final String NAME_PARAM = "name";

       private static final String PRICE_PARAM = "price";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter(NAME_PARAM);
        String price = request.getParameter(PRICE_PARAM);
        System.out.println(price + name);
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }
}
