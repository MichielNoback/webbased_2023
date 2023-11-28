package nl.bioinf.servlets;

import nl.bioinf.config.WebConfig; //change to your situation!
import nl.bioinf.model.Address;
import nl.bioinf.model.MessageFactory;
import nl.bioinf.model.Movie;
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
import java.util.Comparator;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MoviesServlet", urlPatterns = "/the.movies", loadOnStartup = 1)
public class MoviesServlet extends HttpServlet {
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
        //this step is optional; standard settings also suffice
        WebConfig.configureResponse(response);
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                request.getLocale());

        //java 8+ type Comparator
//        ctx.setVariable("movies_year_sorter",
//                (Comparator<Movie>) (o1, o2) -> Integer.compare(o1.getYear(), o2.getYear()));
        List<Movie> allMovies = Movie.getAllMovies();
        allMovies.sort((o1, o2) -> Integer.compare(o2.getYear(), o1.getYear()));
        ctx.setVariable("movies", allMovies);
        //ctx.setVariable("error", "This is an error message");
        this.templateEngine.process("movies", ctx, response.getWriter());
    }
}