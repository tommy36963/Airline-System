package com.java6.airlineservice.airlineservice.controllers;

import com.java6.airlineservice.airlineservice.models.SearchParameters;
import com.java6.airlineservice.airlineservice.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.Instant;
import java.time.LocalDate;

@Controller
public class SearchController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/search")
    public ModelAndView searchForAvailableFlightSchedules(@Valid SearchParameters searchParameters) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bookingError", "");
        modelAndView.addObject("schedules", scheduleService.searchForAvailableFlightSchedules(searchParameters.getFromLocation(),
                searchParameters.getToLocation(), searchParameters.getFlightTime(), searchParameters.getNumberOfPeople()));
        modelAndView.setViewName("flights");
        return modelAndView;
    }

    @GetMapping("/search/return")
    public ModelAndView searchForFlightSchedulesWithReturn(String fromLocation, String toLocation,
                                                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate flightTime,
                                                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnFlightTime){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("toschedules", scheduleService.searchForFlightSchedulesWithReturn(fromLocation,toLocation,flightTime,returnFlightTime).get("toflights"));
        modelAndView.addObject("returnschedules", scheduleService.searchForFlightSchedulesWithReturn(fromLocation,toLocation,flightTime,returnFlightTime).get("returnflights"));
        modelAndView.setViewName("flights-return");
        return modelAndView;
    }

    //TODO schedules by location
    //TODO some smart autocomplete functionality for the user interface

}
