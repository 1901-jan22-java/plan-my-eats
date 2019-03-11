package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Preference;
import com.revature.services.PreferenceService;

@RestController
@RequestMapping("/preference")
public class PreferenceController {

	@Autowired
	PreferenceService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Preference>> getPreferences() {
		return new ResponseEntity<List<Preference>>(service.getAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Preference>> postPreference(@RequestBody Preference newPref) {
		service.save(newPref);
		return new ResponseEntity<List<Preference>>(service.getAll(), HttpStatus.OK);
	}

}
