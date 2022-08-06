package org.oobootcamp.parkingLot.model;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketTest {
    @Test
    void should_equal_when_comparing_given_the_same_ticket() {
        Ticket one = new Ticket();
        Ticket two = one;
        assertThat(one).isEqualTo(two);

    }

    @Test
    void should_not_equal_when_comparing_given_two_different_tickets() {
        Ticket one = new Ticket();
        Ticket two = new Ticket();
        assertThat(one).isNotEqualTo(two);

    }
}
