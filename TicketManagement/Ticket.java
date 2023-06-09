package cinema.TicketManagement;

import cinema.RoomManagement.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Ticket {
    @JsonUnwrapped
    private Token token;
    @JsonProperty(value="ticket")
    private Seat seat;

    public Ticket() {

    }

    public Ticket(Token token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

    public Ticket(Token token) {
        this.token = token;
    }

    @JsonProperty(value="token")
    public String getTicketToken() {
        return this.token.getToken();
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
