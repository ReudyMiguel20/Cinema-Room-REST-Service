package cinema;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@JsonPropertyOrder({"total_rows", "total_columns", "available_seats"})
public class Room {
    private int total_rows = 9;
    private int total_columns = 9;
    private ArrayList<Seat> available_seats;

    public Room() {
        this.available_seats = new ArrayList<>();
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                this.available_seats.add(new Seat(i,j));
            }
        }
    }

    public List<Seat> assignSeats() {
        return this.available_seats;
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return this.total_rows;
    }

    public void setTotal_Rows(int totalRows) {
        this.total_rows = totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return this.total_columns;
    }

    public void setTotalColumns(int totalColumns) {
        this.total_columns = totalColumns;
    }

    @JsonProperty("available_seats")
    public ArrayList<Seat> getAvailableSeats() {
        return available_seats;
    }

    public void setAvailableSeats(ArrayList<Seat> availableSeats) {
        this.available_seats = availableSeats;

    }

    @GetMapping("/seats")
    public Room room() {
        return new Room();
    }


}
