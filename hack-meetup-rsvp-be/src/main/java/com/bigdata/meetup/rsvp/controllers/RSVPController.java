package com.bigdata.meetup.rsvp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RSVPController {
	@GetMapping("/rsvp")
	public String rsvp(Model model) {
		return "rsvp";
	}

	@GetMapping("/locations")
	public String heatMap(Model model) {
		return "locations";
	}

	@GetMapping("/topics")
	public String topics(Model model) {
		return "topics";
	}

}
