package com.example.clw13.repository;

import com.example.clw13.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
    Attendance getAttendanceByEventIdIsAndMemberId(int eventId, int memberId);

    List<Attendance> getAttendancesByEventId(int eventId);
}
