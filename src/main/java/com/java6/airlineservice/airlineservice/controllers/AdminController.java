package com.java6.airlineservice.airlineservice.controllers;

import com.java6.airlineservice.airlineservice.models.Airport;
import com.java6.airlineservice.airlineservice.models.Schedule;
import com.java6.airlineservice.airlineservice.models.Flight;
import com.java6.airlineservice.airlineservice.models.Location;
import com.java6.airlineservice.airlineservice.services.AirportServices;
import com.java6.airlineservice.airlineservice.services.FlightService;
import com.java6.airlineservice.airlineservice.services.LocationServices;
import com.java6.airlineservice.airlineservice.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @Autowired
    AirportServices airportServices;
    @Autowired
    LocationServices locationServices;
    @Autowired
    FlightService flightService;
    @Autowired
    ScheduleService scheduleService;


    @GetMapping("/admin")
    @Secured("ROLE_ADMIN_VIEW")
    public String returnAdminPage() {
        return "admin";
    }

    @GetMapping("/airport-admin")
    @Secured("ROLE_AIRPORT_WRITE")
    public String returnAirportAdmin() {
        return "airport-admin";
    }

    @GetMapping("/schedule-admin")
    @Secured("ROLE_SCHEDULE_WRITE")
    public String returnScheduleAdmin(Model model){
        model.addAttribute("flights", flightService.findAllFlights());
        return "schedule-admin";
    }


    @GetMapping("/flight-admin")
    @Secured("ROLE_FLIGHT_WRITE")
    public String returnFlightAdmin() {
        return "flight-admin";
    }

    @GetMapping("/location-admin")
    @Secured("ROLE_LOCATION_WRITE")
    public String returnLocationAdmin() {
        return "location-admin";
    }

    @PostMapping("/addairport")
    @Secured("ROLE_AIRPORT_WRITE")
    public ModelAndView addAirport(Airport airport) {
        ModelAndView model = new ModelAndView();
        model.addObject("airport", airportServices.addAirport(airport));
        model.setViewName("airport-add-successful");
        return model;


    }

    @PostMapping("/deleteairport")
    @Secured("ROLE_AIRPORT_WRITE")
    public ModelAndView deleteAirport(Airport airport) {
        ModelAndView model = new ModelAndView();
        model.addObject("airport", airport);
        airportServices.deleteAirport(airport);
        model.setViewName("airport-delete-successful");
        return model;


    }

    @PostMapping("/addschedule")
    @Secured("ROLE_SCHEDULE_WRITE")
    public ModelAndView addSchedule(Schedule schedule){
        ModelAndView model= new ModelAndView();
        model.addObject("schedule",scheduleService.addSchedule(schedule));
        model.setViewName("schedule-add-successful");
        return model;


    }

    @PostMapping("/deleteschedule")
    @Secured("ROLE_SCHEDULE_WRITE")
    public ModelAndView deleteSchedule(Schedule schedule){
        ModelAndView model= new ModelAndView();
        scheduleService.deleteSchedule(schedule);
        model.addObject("schedule", schedule);
        model.setViewName("schedule-delete-successful");
        return model;


    }

    @PostMapping("/addflight")
    @Secured("ROLE_FLIGHT_WRITE")
    public ModelAndView addFlight(Flight flight) {
        ModelAndView model = new ModelAndView();
        model.addObject(flightService.addFlight(flight));
        model.setViewName("flight-add-successful");
        return model;
    }

    @PostMapping("/deleteflight")
    @Secured("ROLE_FLIGHT_WRITE")
    public ModelAndView deleteFlight(Flight flight){
        ModelAndView model = new ModelAndView();
        flightService.deleteFlight(flight);
        model.addObject("flight", flight);
        model.setViewName("flight-delete-successful");
        return model;
    }


    @PostMapping("/addlocation")
    @Secured("ROLE_LOCATION_WRITE")
    public ModelAndView addLocation(Location location) {
        ModelAndView model = new ModelAndView();
        model.addObject(locationServices.addLocation(location));
        model.setViewName("location-add-successful");
        return model;

    }

    @PostMapping("/deletelocation")
    @Secured("ROLE_LOCATION_WRITE")
    public ModelAndView deleteLocation(Location location) {
        ModelAndView model = new ModelAndView();
        locationServices.deleteLocation(location);
        model.addObject("location", location);
        model.setViewName("location-delete-successful");
        return model;

    }

    //TODO display all flights
    //TODO display all reservations (max 100 per page?)
    //TODO display all locations
    //TODO display all schedules (max 100 per page?)
    //TODO editing functionality for the repositories
    //TODO deleting functionality for the repositories
    //TODO SPRING SECURITY for admin login

}
