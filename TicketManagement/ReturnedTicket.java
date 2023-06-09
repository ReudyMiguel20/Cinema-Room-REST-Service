package cinema.TicketManagement;

import cinema.RoomManagement.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicket {
    @JsonProperty("returned_ticket")
    private Seat returnedTicket;

    public ReturnedTicket(Seat seat) {
        this.returnedTicket = seat;
    }

    public Seat returnReturnedTicket() {
        return this.returnedTicket;
    }
}
