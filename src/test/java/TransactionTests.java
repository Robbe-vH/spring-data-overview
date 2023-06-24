package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import com.pluralsight.springdataoverview.service.FlightService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTests {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @Before
    public void setUp() {
        flightRepository.deleteAll();
    }

    @Test
    public void shouldNotRollBackWhenThereIsNoTransaction() {
        try {
            flightService.saveFlight(new Flight());
        } catch (Exception e) {
            // do Nothing
        } finally {
            Assertions.assertThat(flightRepository.findAll()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotRollBackWhenThereIsATransaction() {
        try {
            flightService.saveFlightTransactional(new Flight());
        } catch (Exception e) {
            // do Nothing
        } finally {
            Assertions.assertThat(flightRepository.findAll()).isEmpty();
        }
    }

}
