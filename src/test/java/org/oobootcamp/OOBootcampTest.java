package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Dictionary;
import java.util.Hashtable;

public class OOBootcampTest {

    @Test
    void should_welcome_to_oo_bootcamp() {
        OOBootcamp ooBootcamp = new OOBootcamp("Hello, Welcome to OOBootcamp");
        assertThat(ooBootcamp.message()).isEqualTo("Hello, Welcome to OOBootcamp");
    }

    private boolean need_a_function(int good){
        Hashtable hs = new Hashtable<String, Integer>();
        return true;
    }
}
