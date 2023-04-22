public class Seat {
    private int row = 10;
    private int column = 10;
    private boolean[][] seats = new boolean[row][column];
    public Seat() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                seats[i][j] = true;
            }
        }
    }
    public void viewSeat() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (seats[i][j]) {
                    System.out.print("[O] ");
                } else {
                    System.out.print("[X] ");
                }
            }
            System.out.println();
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean[][] getSeats() {
        return seats;
    }

    public void setSeats(int row, int column) {
        this.seats[row][column] = false;
    }

    public boolean isSeatTaken(int row, int seat) {
        return !seats[row][seat]; // if the seat is taken, return true
    }
}
