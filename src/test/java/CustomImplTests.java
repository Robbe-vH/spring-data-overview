package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomImplTests {
    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void shouldSaveCustomImpl() {
        final Flight toDelete = createFlight("Paris");
        final Flight toKeep = createFlight("London");

        flightRepository.save(toDelete);
        flightRepository.save(toKeep);

        flightRepository.deleteByOrigin("Paris");

        Iterable<Flight> flights = flightRepository.findAll();

        Assertions.assertThat(flights).hasSize(1).first().isEqualTo(toKeep);

    }

    private Flight createFlight(String origin) {
        final Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination("New York");
        flight.setScheduledAt(LocalDateTime.parse("2021-12-13T12:12:00"));
        return flightRepository.save(flight);
    }
}
