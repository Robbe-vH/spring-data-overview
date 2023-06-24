package com.pluralsight.springdataoverview.repository

interface DeleteByOriginRepository {
    fun deleteByOrigin(origin: String);

}