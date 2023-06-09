package cinema.RoomManagement;

import cinema.RoomManagement.Room;
import cinema.RoomManagement.Seat;
import cinema.TicketManagement.ReturnedTicket;
import cinema.TicketManagement.SoldTickets;
import cinema.TicketManagement.Ticket;
import cinema.TicketManagement.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaManagement {
    private Room room;
    private SoldTickets soldTickets;

    public CinemaManagement() {
        this.soldTickets = new SoldTickets();
        this.room = new Room();
    }


    @GetMapping("/seats")
    public Room getSeats() {
        return this.room;
    }


    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat seat) {

        // Check if row or column is in bounds
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

        /* Starting a new Seat with the name of 'purchasedSeat' with value 'null', the objective is that if after the next for each loop the Seat
        * is not available then it will be null, and then it will return an error, otherwise it will function as expected */
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

        //This is for the purchased seat to obtain all the values as a seat, because it only got the row and column values
        purchasedSeat = this.room.buySeat(purchasedSeat.getRow(), purchasedSeat.getColumn());

        //Creating new token and adding the seat into a new Ticket, then adding the Ticket to a List
        Token token = new Token();
        Ticket soldTicket = new Ticket(token, purchasedSeat);
        this.soldTickets.add(soldTicket);

        //Returning the ticket
        return ResponseEntity.ok(soldTicket);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnSeat(@RequestBody Token token) {
        //Creating a Ticket with value null, this is to handle the exception if the item return null after the method 'returnTicket'
        Ticket tempTicket = null;
        tempTicket = this.soldTickets.returnTicket(token.getToken());

        if (tempTicket == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"Wrong token!\"}");
        }

        /* returnSeat: add the selected seat back to the list and mark it as available for future purchase, it also sorts the list
        *  ReturnedTicket: stores temporarily the Seat to return a specific body response
        * */
        this.room.returnSeat(tempTicket.getSeat().getRow(), tempTicket.getSeat().getColumn());

        ReturnedTicket tempReturnedTicket = new ReturnedTicket(tempTicket.getSeat());
        return ResponseEntity.ok(tempReturnedTicket);
    }


}
