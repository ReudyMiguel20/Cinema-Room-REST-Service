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
    private List<Ticket> ticketsSold;
    private SoldTickets soldTickets;

    public CinemaManagement() {
//        this.ticket = new ArrayList<>(List.of(new Seat(11, 5, 4)));
//        this.ticket = new ConcurrentHashMap<>();
        this.soldTickets = new SoldTickets();
        this.ticketsSold = new ArrayList<>();
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

//        ticket.put("ticket", this.room.buySeat(purchasedSeat.getRow(), purchasedSeat.getColumn()));

        purchasedSeat = this.room.buySeat(purchasedSeat.getRow(), purchasedSeat.getColumn());


        Token token = new Token();
//        UUID uuid = UUID.randomUUID();
//        token.setToken(uuid);


        Ticket soldTicket = new Ticket(token, purchasedSeat);
        this.soldTickets.add(soldTicket);
//        this.ticketsSold.add(soldTicket);

        return ResponseEntity.ok(soldTicket);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnSeat(@RequestBody Token token) {
//        String UUIDstring = token;
//        UUID uuid = UUID.fromString(UUIDstring);
//        Token tempToken = new Token(uuid);
        Ticket tempTicket = null;

//        String tokenToString = String.valueOf(token.getUuid());

        tempTicket = this.soldTickets.returnTicket(token.getToken());


        if (tempTicket == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"Wrong token!\"}");
        }

        this.room.returnSeat(tempTicket.getSeat().getRow(), tempTicket.getSeat().getColumn());

        ReturnedTicket tempReturnedTicket = new ReturnedTicket(tempTicket.getSeat());
        return ResponseEntity.ok(tempReturnedTicket);
    }


}
