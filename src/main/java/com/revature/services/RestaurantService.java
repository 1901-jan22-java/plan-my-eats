package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Restaurant;
import com.revature.beans.RestaurantPhoto;
import com.revature.dtos.PlaceDetails;
import com.revature.dtos.PlaceDetailsResponse;
import com.revature.dtos.PlacePhoto;
import com.revature.repositories.RestaurantRepository;

@Service
@Transactional
public class RestaurantService {

	private static final Logger log = Logger.getLogger(RestaurantService.class);

	@Autowired
	private RestaurantRepository repo;

	public ResponseEntity<List<Restaurant>> searchRestaurantsByKeywords(String keywords) {

//		String[] filters = params.split(";");
//		String apiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?&location=" + filters[0]
//				+ "&radius=1500&type=restaurant&keyword=" + filters[1] + "&key=AIzaSyAv7FWb5nyCLZw9fxrpkaKLc3NS1BRGeXM";
//		log.info("API Url: " + apiUrl);
//
//		try {
//
//			RestTemplate restTemplate = new RestTemplate();
//			ResponseEntity<PlaceDetailsResponse> responseEntity = restTemplate.getForEntity(apiUrl,
//					PlaceDetailsResponse.class);
//			PlaceDetailsResponse restaurants = responseEntity.getBody();
//			
//			List<Restaurant> rests = new ArrayList<Restaurant>();
//			log.info("Result Size: " + restaurants.getResult().toArray().length);
//			
//			for (int i = 0; i < restaurants.getResult().toArray().length; i++) {
//
//				Restaurant r = new Restaurant();
//				r.setName(restaurants.namesList().get(i));
//				r.setType(filters[1]);
//				r.setLocation(restaurants.AddressList().get(i));
//				r.setImgRef(restaurants.PhotosList().get(i));
//				rests.add(r);
//
//			}
//
//			if (responseEntity.getStatusCode().toString().equals("200")) {
//				return new ResponseEntity<List<Restaurant>>(rests, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);
//			}
//
//		} catch (Exception e) {
//			log.error("Params: " + params, e);
//		}
//
//		return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);
//		return new ResponseEntity<List<Restaurant>>();
		return null;
	}

	public ResponseEntity<List<Restaurant>> mapAndCacheGoogleAPI(ResponseEntity<PlaceDetailsResponse> re,
			String keywords) {

		PlaceDetailsResponse pdr = re.getBody();

		List<Restaurant> rs = new ArrayList<Restaurant>();
		log.info("Result Size: " + pdr.getResult().toArray().length);

		for (PlaceDetails pd : pdr.getResult()) {

//			pd.getName(), pd.getAddress(), pd.getPhotos();
			List<RestaurantPhoto> rp = new ArrayList<>();
			for (PlacePhoto pp : pd.getPhotos()) {
				rp.add(new RestaurantPhoto(pp.getReference()));
			}

			Restaurant r = new Restaurant(pd.getName(), pd.getAddress(), rp);
			rs.add(r);

		}

		return new ResponseEntity<List<Restaurant>>(rs, re.getStatusCode());
	}

	public Restaurant saveRestaurant(Restaurant r) {
		return repo.save(r);
	}

	public List<Restaurant> getRestaurantByType(String type) {
		return repo.findByType(type);
	}

	public Restaurant getRestaurantByName(String name) {
		return repo.findByName(name);
	}

	public List<Restaurant> findAll() {
		return repo.findAll();
	}

	public Restaurant getById(int id) {
		return repo.getOne(id);
	}

	public List<Restaurant> getRestaurant() {
		return repo.findAll();
	}
}
