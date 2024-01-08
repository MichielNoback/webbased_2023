package nl.bioinf.servlets;


import com.google.gson.Gson;
import nl.bioinf.model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MoviesAjaxServlet", urlPatterns = "/movie_service")
public class MoviesAjaxServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestType = request.getParameter("request_type");
        response.setContentType("application/json;charset=UTF-8");
        final PrintWriter writer = response.getWriter();
        JsonResponse jsonResponse = new JsonResponse();
        if (requestType.equals("best")) {
            int number = Integer.parseInt(request.getParameter("number"));
            List<Movie> bestMovies = new ArrayList<>();
            for (int i = 0; i < number; i++) {
                bestMovies.add(Movie.getAllMovies().get(i));
            }
            jsonResponse.responseType = "movielist";
            jsonResponse.responseObject = bestMovies;
        } else if (requestType.equals("random")) {
            List<Movie> movies = Movie.getAllMovies();
            Movie selected = movies.get((int)(Math.random() * movies.size()));
            //jsonResponse.responseType = "movie";
            jsonResponse.responseObject = selected;
        }
        else {
            jsonResponse.errorMessage = "unknown request_type: " + requestType;
        }
        Gson gson = new Gson();
        writer.write(gson.toJson(jsonResponse));
        writer.flush();
    }

    private static class JsonResponse {
        String errorMessage = "NO ERRORS";
        String responseType = "movie";
        Object responseObject;
    }
}
