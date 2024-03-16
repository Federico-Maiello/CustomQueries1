package com.example.exercise16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> provisionFlights() {
        Random random = new Random();
        List<Flight> flights = IntStream.range(0, 50)
                .mapToObj(i -> {
                    Flight flight = new Flight();
                    flight.setDescription("Flight " + i);
                    flight.setFromAirport("Airport " + random.ints(97, 123).limit(3).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
                    flight.setToAirport("Airport " + random.ints(97, 123).limit(3).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
                    flight.setStatus(Status.ON_TIME);
                    return flight;
                })
                .collect(Collectors.toList());

        flightRepository.saveAll(flights);
        return flightRepository.findAll();
    }
}