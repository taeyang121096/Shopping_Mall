package shop.item.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.Address;
import shop.item.domain.Member;
import shop.item.repository.MemberRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    void 회원가입() throws Exception {
        //given
        Address address = new Address("광주","1596-15","3564");
        Member member = Member.createMember("sunny","lso","sunny","tae","010-0000-0000",address);
        //when
        Long saveId = memberService.join(member);
        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    void 중복_아이디() throws Exception {
        //given
        Address address = new Address("광주","1596-15","3564");
        Member member1 = Member.createMember("sun","lso","sunny","tae","010-0000-0000",address);

        Member member2 = Member.createMember("sun","lso","sunny","tae","010-0000-0000",address);

        //when

        //then
        assertThrows(IllegalStateException.class,()->{
            memberService.join(member1);
            memberService.join(member2);
        });
    }

    @Test
    void 정보_변경() throws Exception{
        //given
        Address address = new Address("광주","1596-15","3564");
        Member member = Member.createMember("sunny","lso","sunny","tae","010-0000-0000",address);
        Long id = memberService.join(member);
        //when
        Member find = memberService.findOne(id);
        em.flush();
        em.clear();
        memberService.update(find.getId(),"lsolyh",find.getNickName(),find.getAddress());
        Member update = memberService.findOne(id);

        //then
        assertNotEquals(find.getUserPw(),update.getUserPw());
    }



}