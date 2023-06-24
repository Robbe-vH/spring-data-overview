package com.pluralsight.springdataoverview.repository

import com.pluralsight.springdataoverview.entity.Flight
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : CrudRepository<Flight, Long>, DeleteByOriginRepository  {

    fun findByOrigin(origin: String): List<Flight>;
    fun findByOriginAndDestination(origin: String, destination: String): List<Flight>;
    fun findFlightsByOriginIn(origin: MutableCollection<String>): List<Flight>;
    fun findFlightsByOriginIgnoringCase(origin: String): List<Flight>;


}