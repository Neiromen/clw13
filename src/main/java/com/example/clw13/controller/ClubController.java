package com.example.clw13.controller;

import com.example.clw13.entity.Club;
import com.example.clw13.repository.ClubRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {
    private final ClubRepository clubRepository;

    public ClubController(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(clubRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable("id") int id) {
        Optional<Club> clubData = clubRepository.findById(id);
        if (clubData.isPresent()) {
            return ResponseEntity.ok(clubData.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clubRepository.save(club));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable("id") int id, @RequestBody Club club) {
        Optional<Club> clubData = clubRepository.findById(id);
        if (clubData.isPresent()) {
            Club _club = clubData.get();
            _club.setName(club.getName());
            _club.setAboutIt(club.getAboutIt());
            return ResponseEntity.ok(clubRepository.save(_club));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable("id") int id) {
        clubRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
