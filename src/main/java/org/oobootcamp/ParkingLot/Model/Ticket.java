package org.oobootcamp.ParkingLot.Model;
import java.util.UUID;

public class Ticket {
    private final UUID ticketUUID;
    public Ticket() {
        ticketUUID = UUID.randomUUID();
    }

    public String getTicketId() {
        return ticketUUID.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ticket ticket){
            return ticket.getTicketId().equals(this.getTicketId());
        }
        return false;
    }
}
