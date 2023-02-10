package com.example.task5_10_1.controller;

import com.example.task5_10_1.entity.Hotel;
import com.example.task5_10_1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    //    Create
    @PostMapping
    public String addHotel(@RequestBody Hotel hotel) {
        Hotel hotel1 = new Hotel();
        hotel1.setName(hotel.getName());

        hotelRepository.save(hotel1);
        return "Hotel was added";
    }

    //    Read
    @GetMapping
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    //    Get Hotel by Id
    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent())
            return new Hotel();
        Hotel hotel = optionalHotel.get();
        return hotel;
    }

    //    Update
    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent())
            return "Such hotel was not found";
        Hotel editHotel = optionalHotel.get();
        editHotel.setName(hotel.getName());
        hotelRepository.save(editHotel);
        return "Hotel was edited";
    }

    //    Delete
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent())
            return "Such hotel was not found";
        hotelRepository.deleteById(id);
        return "Hotel was deleted";
    }
}
