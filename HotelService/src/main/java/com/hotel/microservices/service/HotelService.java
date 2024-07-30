package com.hotel.microservices.service;

import java.util.List;

import com.hotel.microservices.entities.Hotel;

public interface HotelService {
	
	Hotel createHotel(Hotel hotel);
	List<Hotel> getAllHotels();
	Hotel getHotelById(String id);

}
