package Main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class Main {
    public static void menu() {
        System.out.println("Welcome to the LUT Kino at Yliopistonkatu!");
        System.out.println("1. Add a movie");
        System.out.println("2. Remove a movie");
        System.out.println("3. View a movie");
        System.out.println("4. View all movies");
        System.out.println("5. Add a showtime");
        System.out.println("6. Remove a showtime");
        System.out.println("7. View a showtime");
        System.out.println("8. View all showtimes");
        System.out.println("9. Buy a ticket");
        System.out.println("10. View seating");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean ok = true;
        Theater theater = new Theater();
        // use the movie database API here


        while(ok) {
            menu();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter the movie title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter the director: ");
                    String director = sc.nextLine();
                    System.out.print("Enter the duration: ");
                    int duration = sc.nextInt();

                    Movie movie = apiConnectToGetMovieInfo(title, director, duration);
                    theater.addMovie(movie);
                    System.out.println("Movie added successfully!");
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter movie title: ");
                    title = sc.nextLine();
                    boolean isRemovedMovie = theater.removeMovie(title);
                    if (isRemovedMovie) {
                        System.out.println("Movie removed successfully!");
                    } else {
                        System.out.println("Movie not found!");
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter movie title: ");
                    title = sc.nextLine();
                    System.out.println();
                    theater.viewAMovie(title);
                    break;
                case 4:
                    System.out.println();
                    theater.viewMovies();
                    break;
                case 5:
                    System.out.print("Enter the title of the movie: ");
                    title = sc.nextLine();
                    System.out.print("Enter the time of the showtime (e.g. 7:00 PM): ");
                    String time = sc.nextLine();
                    System.out.print("Enter the date of the showtime (e.g. 2022-12-15): ");
                    String date = sc.nextLine();
                    System.out.print("Enter the ticket price for the showtime: ");
                    double price = sc.nextDouble();
                    movie = theater.findMovie(title);
                    if(movie == null) {
                        System.out.println("The title of movie not found! Please try again!");
                    }
                    else {
                        Showtime showtime = new Showtime(movie, time, date, price);
                        theater.addShowtime(showtime);
                        System.out.println("Showtime added successfully!");
                    }
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Please enter the following information to remove a showtime: ");
                    System.out.print("Enter the title of the movie: ");
                    title = sc.nextLine();
                    System.out.print("Enter the time of the showtime (e.g. 7:00 PM): ");
                    time = sc.nextLine();
                    System.out.print("Enter the date of the showtime (e.g. 2022-12-15): ");
                    date = sc.nextLine();
                    boolean isRemovedShowtime = theater.removeShowtime(title, time, date);
                    if (isRemovedShowtime) {
                        System.out.println("Showtime removed successfully!");
                    } else {
                        System.out.println("Showtime not found!");
                    }
                    System.out.println();
                    break;
                case 7:
                    System.out.println("Please enter the following information to view a showtime: ");
                    System.out.print("Enter the movie title: ");
                    title = sc.nextLine();
                    System.out.print("Enter the time of the showtime (e.g. 7:00 PM): ");
                    time = sc.nextLine();
                    System.out.print("Enter the date of the showtime (e.g. 2022-12-15): ");
                    date = sc.nextLine();
                    System.out.println();
                    theater.viewAShowtime(title, time, date);
                    break;
                case 8:
                    System.out.println();
                    theater.viewShowtimes();
                    break;
                case 9:
                    boolean isSeatAvailable = false;
                    System.out.print("Enter the movie title: ");
                    title = sc.nextLine();
                    System.out.print("Enter the showtime (time): ");
                    time = sc.nextLine();
                    System.out.print("Enter the showtime (date): ");
                    date = sc.nextLine();
                    while(!isSeatAvailable) {
                        System.out.print("Enter the seat (row and column): ");
                        int row = sc.nextInt();
                        int column = sc.nextInt();
                        isSeatAvailable = theater.buyTicket(title, time, date, row, column);
                    }

                    System.out.println();
                    break;
                case 10:
                    System.out.print("Enter the movie title: ");
                    title = sc.nextLine();
                    System.out.print("Enter the showtime (time): ");
                    time = sc.nextLine();
                    System.out.print("Enter the showtime (date): ");
                    date = sc.nextLine();
                    theater.viewSeating(title, time, date);
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    ok = false;
                    break;
            }
        }
    }
    public static int apiConnectToGetMovieID(Movie movie) {
        try {
            String apiKey = "I removed my API key for security reasons, you can get your own API key from https://www.themoviedb.org/";
            URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&language=en-US&page=1&include_adult=false&query=" + URLEncoder.encode(movie.getTitle(), "UTF-8"));

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder(1024);
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();
                //System.out.println(informationString);
                JSONObject jsonObjectMovieId = new JSONObject(informationString.toString());
                JSONArray results = jsonObjectMovieId.getJSONArray("results");
                JSONObject firstResult = results.getJSONObject(0);

                int idMovie = firstResult.getInt("id");
                return idMovie;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static Movie apiConnectToGetMovieInfo(String title, String director, int duration) {
        try {
            //Public API:
            Movie movie = new Movie(title, director, duration);
            int idMovie = apiConnectToGetMovieID(movie);
            //System.out.println(idMovie);

            String apiKey = "I removed my API key for security reasons, you can get your own API key from https://www.themoviedb.org/";
            URL url = new URL("https://api.themoviedb.org/3/movie/" + Integer.toString(idMovie) + "?api_key=" + apiKey + "&language=en-US");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder(1024);
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();
                //System.out.println(informationString);
                JSONObject jsonObjectMovie = new JSONObject(informationString.toString());

                JSONArray genres = jsonObjectMovie.getJSONArray("genres");
                String genreslist = "";
                for(int i = 0; i < genres.length(); i++){
                    if(i == genres.length()-1)
                        genreslist += genres.getJSONObject(i).getString("name");
                    else
                        genreslist = genreslist + genres.getJSONObject(i).getString("name") + ", ";
                }

                movie.setReleaseYear(jsonObjectMovie.getString("release_date"));
                movie.setRating(jsonObjectMovie.getDouble("vote_average"));
                movie.setNumberOfRatings(jsonObjectMovie.getInt("vote_count"));
                movie.setReview(jsonObjectMovie.getString("overview"));
                movie.setGenre("[" + genreslist + "]");
                return movie;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}