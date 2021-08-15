package shop.item.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 아이디는 필수 입니다")
    private String userId;

    private String userName;
    private String userPw;
    private String nickName;
    private String number;

    private String city;
    private String street;
    private String zipcode;
}
