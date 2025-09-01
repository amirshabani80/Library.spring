package com.library.librarymanagement.member;


import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public List<MemberDTO> findAll() {
        List<MemberDTO> members = new ArrayList<>();
        List<MemberEntity> memberEntities = memberRepository.findAll();
        for (MemberEntity memberEntity : memberEntities
        ) {
            members.add(memberMapper.toDto(memberEntity));
        }
        return members;
    }
    public boolean existsById(Integer id) {
        return memberRepository.existsById(id);
    }

    public MemberDTO findById(Integer id) {
        return memberRepository.findById(id)
                .map(memberMapper::toDto).orElse(null);
    }

    public MemberDTO addMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.save(memberMapper.toEntity(memberDTO));
        return memberMapper.toDto(memberEntity);
    }

    public MemberDTO updateMember(Integer id, MemberDTO memberDTO) {
        Optional<MemberEntity> optional = memberRepository.findById(id);
        if (optional.isPresent()) {
            MemberEntity newMember = optional.get();
            newMember.setName(memberDTO.getName());
            newMember.setAddress(memberDTO.getAddress());
            newMember.setBirthday(memberDTO.getBirthday());
            newMember.setPhoneNumber(memberDTO.getPhoneNumber());
            memberRepository.save(newMember);
            return memberMapper.toDto(newMember);
        } else
            return null;
    }

    public boolean deleteMember(Integer id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}


