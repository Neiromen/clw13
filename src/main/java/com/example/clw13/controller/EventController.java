package com.example.clw13.controller;

import com.example.clw13.entity.Attendance;
import com.example.clw13.entity.Event;
import com.example.clw13.repository.AttendanceRepository;
import com.example.clw13.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventRepository eventRepository;
    private final AttendanceRepository attendanceRepository;

    public EventController(EventRepository eventRepository, AttendanceRepository attendanceRepository) {
        this.eventRepository = eventRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventRepository.save(event));
    }

    @PostMapping("/{eventId}/invite")
    public ResponseEntity<Void> inviteInEvent(@PathVariable int eventId ,@RequestParam int memberId){
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()){
            Attendance attendance = new Attendance(eventId,memberId,"приглашен");
            attendanceRepository.save(attendance);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{eventId}/attendance")
    public ResponseEntity<Void> setAttendance(@PathVariable int eventId,
                                              @RequestParam int memberId,
                                              @RequestParam String status){
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()){
            Attendance attendance = attendanceRepository.getAttendanceByEventIdIsAndMemberId(eventId,memberId);
            if (attendance!=null){
                attendance.setStatus(status);
                attendanceRepository.save(attendance);
            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{eventId}/attendance")
    public ResponseEntity<List<Attendance>> getEventAttendances(@PathVariable int eventId){
        List<Attendance> attendances = attendanceRepository.getAttendancesByEventId(eventId);
        return ResponseEntity.ok(attendances);
    }
}
