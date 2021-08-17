package shop.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.Member;
import shop.item.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validateDuplicateId(member);
        memberRepository.save(member);
        return member.getId();
    }



    private void validateDuplicateId(Member member) {
        List<Member> findMember = memberRepository.findById(member.getUserId());
        if(!findMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
