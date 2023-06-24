package com.pluralsight.springdataoverview.service;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
        throw new RuntimeException("I failed");
    }

    @Transactional
    public void saveFlightTransactional(Flight flight) {
        flightRepository.save(flight);
        //because of the exception, th transaction will not be completed and therefore rollback the previous commands
        throw new RuntimeException("I failed");
    }
}
