package Main;

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
            System.out.println("Movie not found. Please type correct title.");
            System.out.println();
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
            System.out.println("Showtime not found. Please type correct title, time (e.g. 7:00 PM), and date (e.g. 2022-12-15).");
            System.out.println();
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
            for (int i = 0; i < show.getSeat().length; i++) {
                for (int j = 0; j < show.getSeat()[i].length; j++) {
                    if (show.getSeat()[i][j].isAvailable()) {
                        System.out.print("[O] ");
                    } else {
                        System.out.print("[X] ");
                    }
                }
                System.out.println();
            }
        }
        else {
            System.out.println("Showtime not found. Please type correct title, time (e.g. 7:00 PM), and date (e.g. 2022-12-15).");
        }
    }

    public boolean buyTicket(String title, String time, String date, int row, int column) {
        if (row < 0 || row > 9 || column < 0 || column > 9) {
            System.out.println("Invalid seat! Please try again!");
            return false;
        }
        Showtime show = findShowtime(title, time, date);
        if (Objects.nonNull(show)) {
            Seat[][] seats = show.getSeat();
            if (seats[row][column].isAvailable()) {
                seats[row][column].setAvailable(false);
                System.out.println("Ticket purchased.");
                return true;
            } else {
                System.out.println("Seat is not available.");
            }
        } else {
            System.out.println("Showtime not found. Please type correct title, time (e.g. 7:00 PM), and date (e.g. 2022-12-15).");
            return true;
        }
        return false;
    }

    public Movie findMovie(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().trim().equalsIgnoreCase(title.trim())) {
                return movie;
            }
        }
        return null;
    }
    public Showtime findShowtime(String title, String time, String date) {
        for (Showtime show : showtimes) {
            if (show.getMovie().getTitle().trim().equalsIgnoreCase(title.trim()) &&
                    show.getTime().trim().equalsIgnoreCase(time.trim()) &&
                    show.getDate().trim().equals(date.trim())) {
                return show;
            }

        }
        return null;
    }


}
