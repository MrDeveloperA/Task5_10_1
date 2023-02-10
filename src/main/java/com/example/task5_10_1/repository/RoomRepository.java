package com.example.task5_10_1.repository;

import com.example.task5_10_1.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
Page<Room> findAllByHotelId(Integer hotel_id, Pageable pageable);
}
