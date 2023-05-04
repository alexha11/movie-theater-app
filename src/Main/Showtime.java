package Main;

public class Showtime {
    private String time;
    private String date;
    private double ticketPrice;
    private Movie movie;
    private Seat[][] seat;
    private int row = 10;
    private int column = 10;

    public Showtime(Movie movie, String time, String date, double ticketPrice) {
        this.movie = movie;
        this.time = time;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.seat = new Seat[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Seat seat = new Seat(i, j, true);
                this.seat[i][j] = seat;
            }
        }
    }

    public Seat[][] getSeat() {
        return seat;
    }

    public void setSeat(Seat[][] seat) {
        this.seat = seat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

}
