package cinema.TicketManagement;

import java.util.ArrayList;
import java.util.List;

public class SoldTickets {
    private List<Ticket> ticketsSold;

    public SoldTickets() {
        this.ticketsSold = new ArrayList<>();
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

    public Ticket returnTicket(String token) {
        Ticket tempTicket = null;

        for (Ticket x : this.ticketsSold) {
            if (x.getTicketToken().equals(token)) {
                tempTicket = x;
                this.ticketsSold.remove(x);
                break;
            }
        }

        return tempTicket;
    }
}
