package org.oobootcamp.ParkingLot.Model;
import java.util.UUID;

public class Ticket extends ParkingOutPut {
    private final UUID ticketUUID;
    public Ticket() {
        ticketUUID = UUID.randomUUID();
    }

    @Override
    public String getTicketNum() {
        return ticketUUID.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ticket ticket){
            return ticket.getTicketNum().equals(this.getTicketNum());
        }
        return false;
    }
}
