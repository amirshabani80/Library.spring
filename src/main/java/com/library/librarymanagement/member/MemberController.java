package com.library.librarymanagement.member;


import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberDTO> memberDTOS() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@Valid @PathVariable Integer id) {
        MemberDTO memberDTO = memberService.findById(id);
        return ResponseEntity.ok(memberDTO);
    }

    @PostMapping
    public ResponseEntity<MemberDTO> addMember(@Valid @RequestBody MemberDTO memberDTO) {
        MemberDTO savedMember = memberService.addMember(memberDTO);
        return ResponseEntity.ok(savedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@Valid @PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
        MemberDTO updatedMember = memberService.updateMember(id, memberDTO);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@Valid @PathVariable Integer id) {
        Boolean deletedMember = memberService.deleteMember(id);

        return ResponseEntity.ok().build();
    }
}

