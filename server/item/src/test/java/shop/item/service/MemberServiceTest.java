package shop.item.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.Member;
import shop.item.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() throws Exception {
        //given
        Member member = new Member();
//        member.setUserName("aa");

        //when
        Long join = memberService.join(member);
        //then
        assertEquals(member, memberRepository.findOne(join));
    }

    @Test
    void 중복_아이디() throws Exception {
        //given
        Member member1 = new Member();
//        member1.setUserId("aa");

        Member member2 = new Member();
//        member2.setUserId("aa");
        //when

        //then
        assertThrows(IllegalStateException.class,()->{
            memberService.join(member1);
            memberService.join(member2);
        });
    }



}