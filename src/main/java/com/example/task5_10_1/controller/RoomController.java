package com.example.task5_10_1.controller;

import com.example.task5_10_1.deliver.RoomDto;
import com.example.task5_10_1.entity.Hotel;
import com.example.task5_10_1.entity.Room;
import com.example.task5_10_1.repository.HotelRepository;
import com.example.task5_10_1.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    //    Create
    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
            return "Such hotel was not found";
        room.setHotel(optionalHotel.get());

        roomRepository.save(room);
        return "Room was added";
    }

    //    Read
    @GetMapping
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    //    Get Room by Id
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return new Room();
        Room room = optionalRoom.get();
        return room;
    }

    //    Get Room by Hotel Id
    @GetMapping("/getByHotelId/{hotelId}")
    public Page<Room> getRoomByHotelId( @PathVariable Integer hotelId,@RequestParam(required = false) Integer page) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Room> roomPage = roomRepository.findAllByHotelId(hotelId, pageable);

        return roomPage;
    }

    //    Update
    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "Such room was not found";
        Room editRoom = optionalRoom.get();
        editRoom.setNumber(roomDto.getNumber());
        editRoom.setFloor(roomDto.getFloor());
        editRoom.setSize(roomDto.getSize());

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
            return "Such hotel was not found";
        editRoom.setHotel(optionalHotel.get());
        roomRepository.save(editRoom);
        return "Room was edited";
    }

    //    Delete
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "Such room was not found";
        roomRepository.deleteById(id);
        return "Room was deleted";
    }
}
