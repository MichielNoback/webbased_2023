package nl.bioinf.servlets;

import nl.bioinf.model.Role;
import nl.bioinf.model.User;
import nl.bioinf.model.VisitCounter;
import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.bioinf.config.WebConfig;
import org.thymeleaf.context.WebContext;

import java.io.IOException;

//many imports

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        this.templateEngine = WebConfig.getTemplateEngine(getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        response.getWriter().println("You entered: " + username + " and " + password);
        System.out.println("You entered: " + username + " and " + password);
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                request.getLocale());

        //fetch the session object
        //if it is not present, one will be created
        HttpSession session = request.getSession();
        String nextPage;

        if (session.isNew() || session.getAttribute("user") == null) {
            boolean authenticated = authenticate(username, password);
            if (authenticated) {
                System.out.println("User " + username + " is authenticated");
                session.setAttribute("user", new User("Henk", "henk@example.com", Role.USER));
                session.setAttribute("counter", new VisitCounter());
                nextPage = "mainSite";
            } else {
                System.out.println("User " + username + " is not authenticated");
                ctx.setVariable("message", "Your password and/or username are incorrect; please try again");
                ctx.setVariable("message_type", "error");
                nextPage = "login";
            }
        } else {
            System.out.println("User " + username + " is already authenticated");
            VisitCounter counter = (VisitCounter)(session.getAttribute("counter"));
            counter.visit();

            nextPage = "mainSite";
        }
        templateEngine.process(nextPage, ctx, response.getWriter());
    }

    private boolean authenticate(String username, String password) {
        return username.equals("Henk") && password.equals("henk");
    }

    //simple GET requests are immediately forwarded to the login page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                request.getLocale());
        ctx.setVariable("message", "Fill out the login form");
        ctx.setVariable("message_type", "info");
        templateEngine.process("login", ctx, response.getWriter());
    }
}