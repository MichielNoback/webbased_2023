package nl.bioinf.servlets;

import nl.bioinf.config.WebConfig; //change to your situation!
import nl.bioinf.model.Address;
import nl.bioinf.model.MessageFactory;
import nl.bioinf.model.Person;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/give.welcome", loadOnStartup = 1)
public class WelcomeServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing Thymeleaf template engine");
        final ServletContext servletContext = this.getServletContext();
        this.templateEngine = WebConfig.createTemplateEngine(servletContext);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        process(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
//        PrintWriter writer = response.getWriter();
//        writer.println("Hallo dit is echt simpel!");
//        writer.flush();

        Address a = new Address("Zernikeplein", 11, "Groningen", "9747AS");
        Person p = new Person("Jan", 23, a);
        //Math.sqrt(4);
        //this step is optional; standard settings also suffice
        WebConfig.configureResponse(response);
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                request.getLocale());
        ctx.setVariable("person", p);
        ctx.setVariable("currentDate", new Date());
        ctx.setVariable("dayMessage", "hi, " + name + ": " + MessageFactory.giveMessage());
        this.templateEngine.process("welcome", ctx, response.getWriter());
    }
}