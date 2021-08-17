package shop.item.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String userPw;
    private String nickName;
    private String userName;

    @Embedded
    private Address address;

    private String number;

//    private List<Order> orders;


}