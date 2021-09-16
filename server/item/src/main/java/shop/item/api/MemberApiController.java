package shop.item.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.item.domain.Address;
import shop.item.domain.Member;
import shop.item.service.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;
//  회원가입
    @PostMapping("/api/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request){
        Address address = new Address(request.getCity(), request.getStreet(), request.getZipcode());
        Member member = Member.createMember(request.getUserId(),request.getUserPw(), request.getNickName(), request.getUserName(), request.getNumber(),address );
        Long id = memberService.join(member);
        System.out.println(member.getOrders());
        return new CreateMemberResponse(id);
    }
//  아이디 중복 체크
    @GetMapping("/api/members/{userId}/duplicate")
    public boolean checkDuplicateUserId(@PathVariable("userId") String userId){
        List<Member> members = memberService.findByUserId(userId);
//        중복이 아니면 true
        if(members.isEmpty()) return true;
//        중복이면 false
        return false;
    }
//  아이디 로그인
    @PostMapping("/api/members/login")
    public CreateMemberResponse login(@RequestBody @Valid LoginRequest request){
        List<Member> user = memberService.findByUserId(request.userId);
        if(user.get(0).getUserPw().equals(request.getUserPw()))
            return new CreateMemberResponse(user.get(0).getId());
        return new CreateMemberResponse(0L);
    }

//  마이페이지
    @GetMapping("/api/members/{id}")
    public MemberInfo memberInfo(@PathVariable("id") Long id){
        Member member = memberService.findOne(id);
        Address address = member.getAddress();
        return new MemberInfo(member.getUserId(), member.getUserPw(),member.getNickName(),
                member.getUserName(), member.getNumber(),address);
    }
//  마이페이지 수정
    @PutMapping("/api/members/{id}")
    public void update(@PathVariable("id") Long id ,@RequestBody @Valid UpdateMember request){
        Address address = new Address(request.getCity(), request.getStreet(), request.getZipcode());
        memberService.update(id, request.getUserPw(), request.getNickName(), address);
    }

    @Data
    static class CreateMemberResponse {
//        db id 임!!!
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {

        private String userId;
        private String userPw;
        private String nickName;
        private String userName;
        private String number;
        private String city;
        private String street;
        private String zipcode;

    }
    @Data
    static class LoginRequest {
        private String userId;
        private String userPw;
    }

    @Data
    @AllArgsConstructor
    static class MemberInfo {
        private String userId;
        private String userPw;
        private String nickName;
        private String userName;
        private String number;
        private Address address;
    }
    @Data
    static class UpdateMember {
        String userPw;
        String nickName;
        String city;
        String street;
        String zipcode;
    }
}
