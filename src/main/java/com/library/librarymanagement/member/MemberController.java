package com.library.librarymanagement.member;


import org.springframework.beans.factory.annotation.*;
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
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Integer id) {
        MemberDTO memberDTO = memberService.findById(id);
        if (memberDTO != null) {
            return ResponseEntity.ok(memberDTO);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MemberDTO> addMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO savedMember = memberService.addMember(memberDTO);
        return ResponseEntity.ok(savedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
        MemberDTO updatedMember = memberService.updateMember(id, memberDTO);
        if (updatedMember != null) {
            return ResponseEntity.ok(updatedMember);
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        Boolean deletedMember = memberService.deleteMember(id);
        if (deletedMember) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
