package com.example.clw13.controller;

import com.example.clw13.entity.Member;
import com.example.clw13.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") int id) {
        Optional<Member> memberData = memberRepository.findById(id);
        if (memberData.isPresent()) {
            return ResponseEntity.ok(memberData.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberRepository.save(member));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable("id") int id, @RequestBody Member member) {
        Optional<Member> memberData = memberRepository.findById(id);
        if (memberData.isPresent()) {
            Member _member = memberData.get();
            _member.setName(member.getName());
            _member.setSchoolClass(member.getSchoolClass());
            _member.setEmail(member.getEmail());
            return ResponseEntity.ok(memberRepository.save(_member));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") int id) {
        memberRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
