package com.library.library_system.service;

import com.library.library_system.dto.MemberResponseDTO;
import com.library.library_system.entity.Member;
import com.library.library_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    //Entity转DTO
    private MemberResponseDTO toDTO(Member member){
        return new MemberResponseDTO(
                member.getId(),
                member.getName(),
                member.getPhone(),
                member.getEmail(),
                member.getCreateTime()
        );
    }

    //注册新用户
    public MemberResponseDTO register(String name,String phone,String email){
        if(memberRepository.findByPhone(phone).isPresent()){
            throw new RuntimeException("该手机号已被注册");
        }
        Member member=new Member();
        member.setName(name);
        member.setPhone(phone);
        member.setEmail(email);

        return toDTO(memberRepository.save(member));
    }

    //根据ID查读者
    public MemberResponseDTO getMemberById(Long id){
        Member member=memberRepository.findById(id).orElseThrow(()->new RuntimeException("读者不存在"));
        return toDTO(member);
    }

    //查询所有读者
    public List<MemberResponseDTO> getAllMembers(){
        return memberRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //更新读者信息
    public MemberResponseDTO updateMember(Long id,String newName,String newPhone,String newEmail){
        Member member=memberRepository.findById(id).orElseThrow(()->new RuntimeException("读者不存在"));
        if(newName!=null&&!newName.isEmpty()){
            if(!member.getPhone().equals(newPhone)&&memberRepository.findByPhone(newPhone).isPresent()){
                throw new RuntimeException("该手机号已被其他用户注册");
            }
            member.setPhone(newPhone);
        }
        if(newEmail!=null && !newEmail.isEmpty()){
            member.setEmail(newEmail);
        }
        return toDTO(memberRepository.save(member));
    }

    //删除读者
    public void deleteMember(Long id){
        Member member=memberRepository.findById(id).orElseThrow(()->new RuntimeException("读者不存在"));
        memberRepository.delete(member);
    }

}
