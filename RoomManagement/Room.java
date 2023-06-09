package cinema.RoomManagement;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.stereotype.Component;

import java.util.*;


@JsonPropertyOrder({"total_rows", "total_columns", "available_seats"})
@Component
public class Room {
    private int totalRows = 9;
    private int totalColumns = 9;
    private ArrayList<Seat> availableSeats;

    public Room() {
        this.availableSeats = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                if (i <= 4) {
                    this.availableSeats.add(new Seat(i, j, 10));
                } else {
                    this.availableSeats.add(new Seat(i, j, 8));
                }
            }
        }
    }

    public List<Seat> assignSeats() {
        return this.availableSeats;
    }

    //This method is for sorting the list after a ticket gets added back to the available seats after being returned
    public void sortList() {
        Comparator<Seat> comparator = Comparator
                .comparing(Seat::getRow)
                .thenComparing(Seat::getColumn)
                .thenComparing(Seat::getPrice);

        availableSeats.sort(comparator);
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return this.totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return this.totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    @JsonProperty("available_seats")
    public ArrayList<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(ArrayList<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Seat buySeat(int row, int column) {
        int count = 0;
        for (Seat x : this.availableSeats) {
            if (x.getRow() == row && x.getColumn() == column) {
                x.setTaken(true);
                this.availableSeats.remove(count);
                return x;
            }
            count++;
        }
        return null;
    }

    public void returnSeat(int row, int column) {
        int price = 0;

        if (row <= 4) {
            price = 10;
        } else {
            price = 8;
        }

        Seat returnedSeat = new Seat(row, column, price);
        availableSeats.add(returnedSeat);
        sortList();
    }

}
