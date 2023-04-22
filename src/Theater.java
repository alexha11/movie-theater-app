import java.util.ArrayList;
import java.util.Objects;

public class Theater extends Venue {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Showtime> showtimes = new ArrayList<Showtime>();


    public void addMovie(Movie movie) {
        movies.add(movie);
    }
    public boolean removeMovie(String title) {
        Movie movie = findMovie(title);
        if (Objects.nonNull(movie)) {
            movies.remove(movie);
            return true;
        }
        return false;
    }

    public void viewAMovie(String title) {
        Movie movie = findMovie(title);
        if (Objects.nonNull(movie)) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Director: " + movie.getDirectorName());
            System.out.println("Release Year: " + movie.getReleaseYear());
            System.out.println("Review: " + movie.getReview());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Duration: " + movie.getDuration());
            System.out.println("Rating: " + movie.getRating());
            System.out.println("Number of Ratings: " + movie.getNumberOfRatings());
            System.out.println();
        }
        else {
            System.out.println("Movie not found.");
        }
    }
    public void viewMovies() {
        for (Movie movie : movies) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Director: " + movie.getDirectorName());
            System.out.println("Release Year: " + movie.getReleaseYear());
            System.out.println("Review: " + movie.getReview());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Duration: " + movie.getDuration());
            System.out.println("Rating: " + movie.getRating());
            System.out.println("Number of Ratings: " + movie.getNumberOfRatings());
            System.out.println();
        }
    }

    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }
    public boolean removeShowtime(String title, String time, String date) {
        Showtime show = findShowtime(title, time, date);
        if (Objects.nonNull(show)) {
            showtimes.remove(show);
            return true;
        }
        return false;
    }

    public void viewAShowtime(String title, String time, String date) {
        Showtime show = findShowtime(title, time, date);
        if (Objects.nonNull(show)) {
            System.out.println("Movie: " + show.getMovie().getTitle());
            System.out.println("Time: " + show.getTime());
            System.out.println("Date: " + show.getDate());
            System.out.println("Price: " + show.getTicketPrice());
            System.out.println();
        } else {
            System.out.println("Showtime not found.");
        }
    }
    public void viewShowtimes() {
        for (Showtime show : showtimes) {
            Movie movie = show.getMovie();
            System.out.println("Movie: " + movie.getTitle());
            System.out.println("Time: " + show.getTime());
            System.out.println("Date: " + show.getDate());
            System.out.println("Price: " + show.getTicketPrice());
            System.out.println();
        }
    }

    public void viewSeating(String title, String time, String date) {
        Showtime show = findShowtime(title, time, date);
        if (Objects.nonNull(show)) {
            show.getSeat().viewSeat();
        } else {
            System.out.println("Showtime not found. Please view a different showtime.");
        }
    }

    public void buyTicket(String title, String time, String date, int row, int seat) {
        Showtime show = findShowtime(title, time, date);
        if (Objects.nonNull(show)) {
            if(show.getSeat().isSeatTaken(row, seat)) {
                System.out.println("Seat already taken. Please choose another seat.");

            }
            else {
                show.getSeat().setSeats(row, seat);
                System.out.println("Ticket bought!");
            }
        } else {
            System.out.println("Showtime not found. Please buy a ticket for a different showtime.");
        }
    }

    public Movie findMovie(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }
    public Showtime findShowtime(String title, String time, String date) {
        for (Showtime show : showtimes) {
            if (show.getMovie().getTitle().equalsIgnoreCase(title) && show.getTime().equalsIgnoreCase(time) && show.getDate().equals(date)) {
                return show;
            }
        }
        return null;
    }


}
