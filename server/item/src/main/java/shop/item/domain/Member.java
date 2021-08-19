package shop.item.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


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
    private String number;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders;


}
