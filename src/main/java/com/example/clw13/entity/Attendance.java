package com.example.clw13.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private int eventId;
    @Column
    private int memberId;
    @Column
    private String status;
}
