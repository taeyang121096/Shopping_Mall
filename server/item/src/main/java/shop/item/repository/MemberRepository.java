package shop.item.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.item.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
//    회원 가입
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

//    회원 한명 찾기 by id
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

//  모든 회원 찾기
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

//  회원 아이디로 찾기
    public List<Member> findByUserId(String userId){
        return em.createQuery("select m from Member m where m.userId = :userId", Member.class)
                .setParameter("userId",userId).getResultList();
    }

}
