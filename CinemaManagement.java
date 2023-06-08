package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaManagement {
    private Room room;

    public CinemaManagement() {
        this.room = new Room();
    }

    @GetMapping("/seats")
    public Room getSeats() {
        return this.room;
    }


    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat seat) {

        //check if row or column is in bounds
        if (
                 seat.getRow() <= 0 ||
                 seat.getColumn() <= 0 ||
                 seat.getRow() > room.getTotalRows() ||
                 seat.getColumn() > room.getTotalColumns()
        ) {
            return ResponseEntity.badRequest().body(
                    "{\"error\": \"The number of a row or a column is out of bounds!\"}"
            );
        }

        Seat purchasedSeat = null;
        for (Seat s : this.room.getAvailableSeats()) {
            if (s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn()) {
                purchasedSeat = seat;
                break;
            }
        }


        if (purchasedSeat == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"The ticket has been already purchased!\"}");
        }


        return ResponseEntity.ok(this.room.buySeat(purchasedSeat.getRow(), purchasedSeat.getColumn()));
    }


}
