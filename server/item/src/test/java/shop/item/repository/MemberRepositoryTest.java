package shop.item.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.Address;
import shop.item.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    void 회원등록() throws Exception {
        //given
        Address address = new Address("광주","1506-13","12345");
        Member member = Member.createMember("sun","lso","sunny","taeyang","010-121-1211",address);


        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(saveId);


        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
    }

    @Test
    void 아이디_회원_찾기() throws Exception{
        //given
        Address address = new Address("광주","1506-13","12346");
        Member member = Member.createMember("sun","lso","sunny","taeyang","010-121-1211",address);
        //when
        Long save = memberRepository.save(member);

        List<Member> findMember = memberRepository.findByUserId("sun");

        //then
        assertThat(findMember.get(0).getId()).isEqualTo(member.getId());
    }
    @Test
    void 아이디_회원_없을경우_크기는0() throws Exception{
        //given
        Address address = new Address("광주","1506-13","12346");
        Member member = Member.createMember("sun","lso","sunny","taeyang","010-121-1211",address);
        //when
        Long save = memberRepository.save(member);

        List<Member> findMember = memberRepository.findByUserId("su");

        //then
        assertThat(findMember.size()).isEqualTo(0);
    }


}