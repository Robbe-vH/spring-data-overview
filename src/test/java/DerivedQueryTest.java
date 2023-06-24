package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DerivedQueryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Before
    public void setUp() {
        flightRepository.deleteAll();
    }

    @Test
    public void shouldFindFlightsFromLondon() {
        final Flight flight1 = createFlight("London");
        final Flight flight2 = createFlight("London");
        final Flight flight3 = createFlight("New York");

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);

        List<Flight> flightsFromLondon = flightRepository.findByOrigin("London");

        Assertions.assertThat(flightsFromLondon).hasSize(2);
        Assertions.assertThat(flightsFromLondon.get(0)).isEqualToComparingFieldByField(flight1);
        Assertions.assertThat(flightsFromLondon.get(1)).isEqualToComparingFieldByField(flight2);
    }

    @Test
    public void shouldFindFlightsFromLondonToParis() {
        final Flight flight1 = createFlight("London", "Paris");
        final Flight flight2 = createFlight("London", "Madrid");
        final Flight flight3 = createFlight("London", "Brussels");

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);

        List<Flight> flightsFromLondonToParis = flightRepository.findByOriginAndDestination("London", "Paris");

        Assertions.assertThat(flightsFromLondonToParis)
                .hasSize(1)
                .first()
                .isEqualTo(flight1);
    }

    @Test
    public void shouldFindFlightsFromLondonOrMadrid() {
        final Flight flight1 = createFlight("Paris");
        final Flight flight2 = createFlight("London");
        final Flight flight3 = createFlight("Madrid");

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);
        Collection<String> originsCollection = new ArrayList<String>(Arrays.asList("London", "Madrid"));

        List<Flight> flightsFromLondonOrMadrid = flightRepository.findFlightsByOriginIn(originsCollection);

        Assertions.assertThat(flightsFromLondonOrMadrid).hasSize(2);
        Assertions.assertThat(flightsFromLondonOrMadrid.get(0)).isEqualToComparingFieldByField(flight2);
        Assertions.assertThat(flightsFromLondonOrMadrid.get(1)).isEqualToComparingFieldByField(flight3);
    }

    @Test
    public void shouldFindFlightsFromLondonIgnoringCase() {
        final Flight flight1 = createFlight("Paris");
        final Flight flight2 = createFlight("London");
        final Flight flight3 = createFlight("Madrid");

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);

        List<Flight> flightsFromLondon = flightRepository.findFlightsByOriginIgnoringCase("lOnDoN");

        Assertions.assertThat(flightsFromLondon).hasSize(1);
    }

    private Flight createFlight(String origin) {
        Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination("New Orleans");
        flight.setScheduledAt(LocalDateTime.parse("2021-12-13T12:12:00"));

        return flight;
    }
    private Flight createFlight(String origin, String destination) {
        Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setScheduledAt(LocalDateTime.parse("2021-12-13T12:12:00"));

        return flight;
    }
}
