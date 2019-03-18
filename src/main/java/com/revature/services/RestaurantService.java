package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Preference;
import com.revature.beans.Restaurant;
import com.revature.configurations.KeyConfiguration;
import com.revature.dtos.google.places.PlaceDetails;
import com.revature.dtos.google.places.PlaceDetailsResponse;
import com.revature.dtos.google.places.PlaceLocation;
import com.revature.dtos.google.places.PlacePhoto;
import com.revature.repositories.RestaurantRepository;

@Service
public class RestaurantService {

	private static final Logger log = Logger.getLogger(RestaurantService.class);

	private static String appKey = KeyConfiguration.PROPS.getProperty("google.places.appKey");

	@Autowired
	private static RestaurantRepository repo;

	public ResponseEntity<List<Restaurant>> searchRestaurantsByKeywords(String location, List<String> keywords) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<PlaceDetailsResponse> re = rt.getForEntity(buildAPIUrl(location, keywords),
				PlaceDetailsResponse.class);

		return mapAndCacheGoogleAPI(re, keywords);
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

	/************************************/
	/********** HELPER METHODS **********/
	/************************************/

	private static String buildAPIUrl(String location, List<String> keywords) {
		StringBuilder kwSB = new StringBuilder();
		for (String s : keywords) {
			kwSB.append("&keyword=" + s);
		}
		String apiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?&location=" + location
				+ "&radius=1500&type=restaurant" + kwSB.toString() + "&key=" + appKey;
		log.info("API Url: " + apiUrl);
		return apiUrl;
	}

	private static ResponseEntity<List<Restaurant>> mapAndCacheGoogleAPI(ResponseEntity<PlaceDetailsResponse> re,
			List<String> keywords) {
		PlaceDetailsResponse pdr = re.getBody();

		List<Restaurant> rs = new ArrayList<Restaurant>();
		log.info("Result: " + pdr.getResult());

		for (PlaceDetails pd : pdr.getResult()) {
			PlaceLocation pl = pd.getGeometry().getLocation();

			StringBuilder sb = new StringBuilder();
			for (PlacePhoto pp : pd.getPhotos()) {
				sb.append(pp.getReference() + " ");
			}
			List<Preference> prefs = new ArrayList<>();
			for (String p : keywords) {
				prefs.add(new Preference(p, "cuisine"));
			}

			Restaurant r = new Restaurant(pd.getName(), pd.getVicinity(), pl.getLatitude(), pl.getLongitude(),
					sb.toString().substring(0, sb.length() - 1), prefs);
			repo.save(r);
			rs.add(r);
		}

		return new ResponseEntity<List<Restaurant>>(rs, re.getStatusCode());
	}

}
