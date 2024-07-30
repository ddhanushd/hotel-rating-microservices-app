package com.user.microservices.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.microservices.entities.Hotel;
import com.user.microservices.entities.Rating;
import com.user.microservices.entities.User;
import com.user.microservices.exception.ResourceNotFoundException;
import com.user.microservices.repository.UserRepository;
import com.user.microservices.service.external.services.HotelService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
		
	}

	@Override
	public User getUserById(String userId) {
		//Get user from database with the help of of user repository
		User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !! : "+userId));
		//fetch rating of the above user from rating service
		//http://localhost:7083/ratings/users/4f41818a-afe5-4fb0-84d4-916fdf1eeb05
		//ArrayList<Rating> ratingsOfUser=restTemplate.getForObject("http://localhost:7083/ratings/users/"+user.getUserId(), ArrayList.class);
		//Rating[] ratingsOfUser=restTemplate.getForObject("http://localhost:7083/ratings/users/"+user.getUserId(), Rating[].class);
		// removing host and port
		Rating[] ratingsOfUser=restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{} ",ratingsOfUser);
		
		List<Rating>ratings=Arrays.stream(ratingsOfUser).toList();		
		List<Rating> ratingList=ratings.stream().map(rating->{
			//api call to hotel service to get the hotel
			//http://localhost:7082/hotels/302e45dc-cb18-4502-8abd-254e41a3571a
			//ResponseEntity<Hotel>forEntity=restTemplate.getForEntity("http://localhost:7082/hotels/"+rating.getHotelId(),Hotel.class);
			// removing host and port
			//ResponseEntity<Hotel>forEntity=restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(),Hotel.class);
			//Hotel hotel=forEntity.getBody();
			
			// communication using feign client
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
			//logger.info("resonse status code: {} ",forEntity.getStatusCode());
			
			//set the hotel to rating
			rating.setHotel(hotel);
			//return the rating
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		//user.setRatings(ratingsOfUser);
		return user;
	}

	@Override
	public User updateUser(User user, String userId) {
		User user1=userRepository.findById(userId).orElseThrow();
		user1.setUserId(user.getUserId());
		user1.setName(user.getName());
		user1.setEmail(user.getEmail());
		user1.setAbout(user.getAbout());
		return userRepository.save(user1);
	}

	@Override
	public void deleteUser(String userId) {
		userRepository.deleteById(userId);
	}

}
