package cinema.TicketManagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SoldTickets {
    private List<Ticket> ticketsSold;
    private int soldTicketsInTotal;
    private int profitTicketsInTotal;

    public SoldTickets() {
        soldTicketsInTotal = 0;
        this.ticketsSold = new ArrayList<>();
        profitTicketsInTotal = 0;
    }

    public void add(Ticket ticket) {
        this.ticketsSold.add(ticket);
    }

    public List<Ticket> getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(List<Ticket> ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public int getSoldTicketsInTotal() {
        return soldTicketsInTotal;
    }

    public void setSoldTicketsInTotal() {
        soldTicketsInTotal += 1;
    }

    public int getProfitTicketsInTotal() {
        return profitTicketsInTotal;
    }

    public void setProfitTicketsInTotal(int ticketPrice) {
        profitTicketsInTotal += ticketPrice;
    }

    public void removeProfitTicketsInTotal(int ticketPrice) {
        profitTicketsInTotal -= ticketPrice;
    }

    public Ticket returnTicket(String token) {
        Ticket tempTicket = null;

        for (Ticket x : this.ticketsSold) {
            if (x.getTicketToken().equals(token)) {
                tempTicket = x;
                this.ticketsSold.remove(x);
                this.soldTicketsInTotal--;
                this.removeProfitTicketsInTotal(x.getSeat().getPrice());
                break;
            }
        }

        return tempTicket;
    }
}
