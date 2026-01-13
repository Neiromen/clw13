package com.example.clw13.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private LocalDateTime dateTime;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private int clubId;
}
