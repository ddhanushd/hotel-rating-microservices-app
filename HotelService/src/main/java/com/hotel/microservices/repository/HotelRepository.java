package com.hotel.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.microservices.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
