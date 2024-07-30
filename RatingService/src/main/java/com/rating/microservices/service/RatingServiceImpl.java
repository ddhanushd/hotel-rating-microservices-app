package com.rating.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.microservices.entities.Rating;
import com.rating.microservices.exception.ResourceNotFoundException;
import com.rating.microservices.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository repository;

	@Override
	public Rating create(Rating rating) {
		return repository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return repository.findAll();
	}

	/*
	 * @Override public List<Rating> getRatingByUserId(String userId) { return
	 * repository.findByUserId(userId); }
	 * 
	 * @Override public List<Rating> getRatingByHotelId(String hotelId) { return
	 * repository.findByHotelId(hotelId); }
	 */
	
	@Override
	public List<Rating> getRatingByUserId(String userId) {
	    List<Rating> ratings = repository.findByUserId(userId);
	    if (ratings.isEmpty()) {
	        throw new ResourceNotFoundException("Ratings for user with ID " + userId + " not found !!");
	    }
	    return ratings;
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
	    List<Rating> ratings = repository.findByHotelId(hotelId);
	    if (ratings.isEmpty()) {
	        throw new ResourceNotFoundException("Ratings for hotel with ID " + hotelId + " not found !!");
	    }
	    return ratings;
	}



}
