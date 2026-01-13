package com.example.clw13.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

    public Attendance(int eventId, int memberId, String status) {
        this.eventId = eventId;
        this.memberId = memberId;
        this.status = status;
    }
}
