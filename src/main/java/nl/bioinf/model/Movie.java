package nl.bioinf.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Movie {
    private String title;
    private int year;
    private double rating = -1;
    private Set<String> mainActors = new HashSet<>();
    private static List<Movie> movies;

    public Movie() {
    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Movie(String title, int year, double rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public Movie(String title, int year, double rating, List<String> mainActors) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.mainActors.addAll(mainActors);
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getMainActors() {
        List<String> actors = new ArrayList<>();
        actors.addAll(mainActors);
        actors.sort(String::compareTo);
        return actors;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void addActor(String actor) {
        mainActors.add(actor);
    }

    public static List<Movie> getAllMovies() {
        return Movie.movies;
    }

    //create a list from IMDB top 20
    static {
        List<Movie> movies = new ArrayList<>();
        Movie movie;
        movie = new Movie("The Shawshank Redemption", 1994, 9.2, List.of("Tim Robbins", "Morgan Freeman"));
        movies.add(movie);
        movie = new Movie("The Dark Knight", 2008, 9.0, List.of("Christian Bale,", "Heath Ledger"));
        movies.add(movie);
        movie = new Movie("Pulp Fiction", 1994, 8.9, List.of("John Travolta", "Uma Thurman", "Samuel L. Jackson"));
        movies.add(movie);
        movie = new Movie("Fight Club ", 1999, 8.8, List.of("Brad Pitt", "Edward Norton"));
        movies.add(movie);
        movie = new Movie("Forrest Gump", 1994, 8.7, List.of("Tom Hanks", "Robin Wright", "Gary Sinise"));
        movies.add(movie);
        movie = new Movie("Inception", 2010, 8.7, List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"));
        movies.add(movie);
        movie = new Movie("One Flew Over the Cuckoo's Nest", 1975, 8.7, List.of("Jack Nicholson", "Louise Fletcher", "Will Sampson"));
        movies.add(movie);
        movie = new Movie("The Usual Suspects", 1995, 8.5, List.of("Kevin Spacey", "Gabriel Byrne", "Chazz Palminteri"));
        movies.add(movie);
        Movie.movies = movies;
    }
}