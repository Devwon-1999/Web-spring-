package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//단위테스트

class MemberServiceTest {
    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //테스트시 다른 레포지토리를 생성하는 문제를 해결하기 위해 아래와 같이 코드를 수정한다.
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){ // beforeEach는 모든 아래의 테스트코드 실행 전 실행할 코드
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    //위와 같은 방식을 DI 의존성 주입이라고 한다. new하지않고 외부에서 개발자가 넣어준다.

    @AfterEach //테스트 이후 항상 메모리 버퍼를 비워줘라
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(findMember.getName(), member.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
//        try{
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다");
//        }
        //위와 같이 try catch로도 예외를 처리할 수 있으나 아래와 같이 한문장으로 할 수 있다.
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //아래와 같이 객체로 담아서 실제 값 또한 비교할 수 있다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals(e.getMessage(), "이미 존재하는 회원입니다" );

        //then

    }

    @Test
    void findMembers() {
        //given

        //when

        //then

    }

    @Test
    void findOne() {
        //given

        //when

        //then

    }
}