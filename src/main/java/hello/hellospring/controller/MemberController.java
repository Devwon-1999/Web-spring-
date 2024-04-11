package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    //이렇게는 할 수 없다. MemberService에 @Service를 넣어줘야한다.

    //URL창에 엔터치는게 GetMapping이다. 조회할때 사용한다.
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //PostMapping은 데이터를 폼에 넣어 전달할때 사용한다.
    @PostMapping("/members/new")
    public String create(MemberForm form){ //MemberForm에 문자열로 입력한 name이들어가서 setName으로 저장하게 된다.
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member = " + member.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
