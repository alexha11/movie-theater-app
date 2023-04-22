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

                    Movie movie = new Movie(title, director, duration);
                    theater.addMovie(movie);
                    System.out.println("Movie added successfully!");
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
                        Seat seat = new Seat();
                        Showtime showtime = new Showtime(movie, seat, time, date, price);
                        theater.addShowtime(showtime);
                        System.out.println("Showtime added successfully!");
                    }
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
                    System.out.print("Enter the movie title: ");
                    title = sc.nextLine();
                    System.out.print("Enter the showtime (time): ");
                    time = sc.nextLine();
                    System.out.print("Enter the showtime (date): ");
                    date = sc.nextLine();
                    System.out.print("Enter the seat (row and column): ");
                    int row = sc.nextInt();
                    int column = sc.nextInt();
                    theater.buyTicket(title, time, date, row, column);
                    break;
                case 10:
                    System.out.print("Enter the movie title: ");
                    title = sc.nextLine();
                    System.out.print("Enter the showtime (time): ");
                    time = sc.nextLine();
                    System.out.print("Enter the showtime (date): ");
                    date = sc.nextLine();
                    theater.viewSeating(title, time, date);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    ok = false;
                    break;
            }
            System.out.println();
        }
    }
}