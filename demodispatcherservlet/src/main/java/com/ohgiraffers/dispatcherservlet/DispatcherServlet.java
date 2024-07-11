package com.ohgiraffers.dispatcherservlet;

import java.io.*;
import java.net.URL;
import java.util.Map;

import com.ohgiraffers.dispatcherservlet.controller.Controller;
import com.ohgiraffers.dispatcherservlet.handler.HandlerMapping;
import com.ohgiraffers.dispatcherservlet.handler.ViewResolver;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/")
public class DispatcherServlet extends HttpServlet {

    private HandlerMapping handlerMapping;
    private ViewResolver viewResolver;



    @Override
    public void init(ServletConfig config) throws ServletException {
        handlerMapping = new HandlerMapping();
        viewResolver = new ViewResolver();
    }

    @Override
    public void service(ServletRequest req,
                        ServletResponse res) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;

        String method = httpRequest.getMethod();
        // post, delete, put, get, patch, options : HTTP method

        if ("GET".equals(method)) {
            doGet(httpRequest, httpResponse);
        } else if ("POST".equals(method)){
            doPost(httpRequest, httpResponse);
        } else if ("PUT".equals(method)) {
            doPut(httpRequest, httpResponse);
        } else if ("DELETE".equals(method)) {
            doDelete(httpRequest, httpResponse);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req,
                            HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        Controller controller = handlerMapping.getController(path);

        if (controller != null){
            String page = controller.handlerRequest(request, response);

            Map<String, String> root = viewResolver.getView(page);

            if (root.containsKey("redirect")) {
                response.sendRedirect(root.get("redirect"));
            } else {
                ServletContext servletContext = request.getServletContext();
                URL resource = servletContext.getResource(root.get("forward"));

                if (resource == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }

                request.getRequestDispatcher(root.get("forward")).forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}