package com.pluralsight.springdataoverview

import com.pluralsight.springdataoverview.entity.Flight
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringDataOverviewApplication

fun main(args: Array<String>) {
	runApplication<SpringDataOverviewApplication>(*args)
}
