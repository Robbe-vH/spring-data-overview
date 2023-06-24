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
public class CRUDTests {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void shouldPerformCRUDOperations() {
        final Flight flight = new Flight();
        flight.setOrigin("London");
        flight.setDestination("New York");
        flight.setScheduledAt(LocalDateTime.parse("2021-12-13T12:12:00"));

        flightRepository.save(flight);

        Assertions.assertThat(flightRepository.findAll())
                .hasSize(1).first().isEqualTo(flight);

        flightRepository.deleteById(flight.getId());
        Assertions.assertThat(flightRepository.count()).isZero();


    }

}
