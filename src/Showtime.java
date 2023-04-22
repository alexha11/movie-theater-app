public class Showtime {
    private String time;
    private String date;
    private Movie movie;
    private Seat seat;
    private double ticketPrice;

    public Showtime(Movie movie, Seat seat, String time, String date, double ticketPrice) {
        this.movie = movie;
        this.seat = seat;
        this.time = time;
        this.date = date;
        this.ticketPrice = ticketPrice;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
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
