package com.pluralsight.springdataoverview.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Flight {
    @Id
    @GeneratedValue
    var id: Long = 0;
    var origin: String = "";
    var destination: String = "";
    var scheduledAt: LocalDateTime = LocalDateTime.now();


    override fun toString(): String {
        return "Flight(id=$id, origin='$origin', destination='$destination', scheduledAt=$scheduledAt)"
    }
}