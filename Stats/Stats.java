package cinema.Stats;

import cinema.Rest.CinemaManagement;
import cinema.RoomManagement.Room;
import cinema.TicketManagement.SoldTickets;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@JsonPropertyOrder({"current_income", "number_of_available_seats", "number_of_purchased_tickets"})
public class Stats {
    private CinemaManagement cinemaManagement;

    public Stats() {
    }

    @Autowired
    public Stats(CinemaManagement cinemaManagement) {
        this.cinemaManagement = cinemaManagement;
    }

    @JsonProperty(value = "current_income")
    public int getCurrentIncome() {
        return this.cinemaManagement.returnTotalMoney();
    }

    @JsonProperty(value = "number_of_available_seats")
    public int getAvailableSeats() {
        return this.cinemaManagement.returnTotalSeats();
    }

    @JsonProperty(value = "number_of_purchased_tickets")
    public int getNumberOfPurchasedTickets() {
        return this.cinemaManagement.returnTotalTicketsSold();
    }


}
