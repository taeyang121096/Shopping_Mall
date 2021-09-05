package shop.item.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String userPw;
    private String nickName;
    private String userName;
    private String number;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    //== 멤버 생성 메서드==//
    public static Member createMember(String userId,String userPw,String nickName, String userName,String number,Address address){
        Member member = new Member();
        member.userId = userId;
        member.userPw = userPw;
        member.nickName = nickName;
        member.userName = userName;
        member.number = number;
        member.address = address;

        return member;
    }

    //==비지니스 로직==//
    public void update(String userPw, String nickName, Address address){
        this.userPw = userPw;
        this.nickName = nickName;
        this.address = address;
    }

}
