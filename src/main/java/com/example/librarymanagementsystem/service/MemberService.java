package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.config.security.SecurityUtils;
import com.example.librarymanagementsystem.entity.Member;
import com.example.librarymanagementsystem.error.ResourceAlreadyExistException;
import com.example.librarymanagementsystem.error.ResourceNotFoundException;
import com.example.librarymanagementsystem.mapStruct.dto.member.AddingMemberRequest;
import com.example.librarymanagementsystem.mapStruct.dto.member.MemberDto;
import com.example.librarymanagementsystem.mapStruct.mapper.MemberMapper;
import com.example.librarymanagementsystem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<MemberDto> getAllMembers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Member> Members = memberRepository.findAll(pageable);
        List<MemberDto> memberDtos = Members.stream().map(memberMapper::toDTO).toList();
        if (memberDtos.isEmpty()) {
            logger.warn("getAllUsers returned empty list");
            throw new ResourceNotFoundException("There is no users in this page");
        } else
            logger.info("List of members with page : {} size : {} returned for user: {}", page, size, SecurityUtils.getCurrentUsername());

        return memberDtos;

    }

    public MemberDto createMember(AddingMemberRequest addRequest) {
        Member member = memberMapper.toEntity(addRequest);
        Optional<Member> memberWithSameEmail = memberRepository.findByEmail(member.getEmail());
        if (memberWithSameEmail.isPresent()) {
            logger.error("Member with Email {} already exists", member.getEmail());
            throw new ResourceAlreadyExistException("Member with email " + member.getEmail() + " already exists");
        }
        memberRepository.save(member);
        logger.info("Member created");
        return memberMapper.toDTO(member);
    }

    public MemberDto updateMember(MemberDto updateRequest) {
        Optional<Member> member = memberRepository.findById(updateRequest.getId());
        if (member.isEmpty()) {
            logger.error("Member with id {} not found for updating", updateRequest.getId());
            throw new ResourceNotFoundException("Member with id " + updateRequest.getId() + " not found");
        }
        Member memberToUpdate = member.get();
        memberToUpdate.setFullName(updateRequest.getFullName());
        memberToUpdate.setEmail(updateRequest.getEmail());
        memberToUpdate.setAddress(updateRequest.getAddress());
        memberToUpdate.setPhone(updateRequest.getPhone());
        memberToUpdate.setMaxBorrowLimit(updateRequest.getMaxBorrowLimit());
        memberToUpdate = memberRepository.save(memberToUpdate);
        return memberMapper.toDTO(memberToUpdate);
    }

    public void deleteMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            logger.error("Member with id {} not found", id);
            throw new ResourceNotFoundException("Member with id " + id + " not found");
        }
        memberRepository.deleteById(id);
        logger.info("Member deleted {}", id);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new ResourceNotFoundException("Member with id " + memberId + " not found"));
    }
}
