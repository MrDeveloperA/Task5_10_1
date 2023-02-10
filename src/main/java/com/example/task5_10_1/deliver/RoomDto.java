package com.example.task5_10_1.deliver;

import com.example.task5_10_1.entity.Hotel;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class RoomDto {
    private Integer number;
    private Integer floor;
    private Integer size;
    private Integer hotelId;
}
