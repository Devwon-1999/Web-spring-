package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//@Service // 레포지토리에는 구현체에!! @Repository를 넣어줘야함


@Transactional //JPA사용시 데이터를 저장하고 변경할때 트랜잭션이 필요하다 여기 해줘도되고 //회원가입에 해줘도된다.
public class MemberService { //ctrl + shift + T 테스트 단축키
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //테스트시 다른 레포지토리를 생성하는 문제를 해결하기 위해 아래와 같이 코드를 수정한다.

    //@Autowired
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //회원가입
    //@Transactional //회원가입에 해줘도 되는 이유는 회원가입시에만 필요하기 때문이다.
    public Long join (Member member){
        //같은 이름이 있는 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName()); //result에 동일이름 아이디가 있는지 확인
//                result.ifPresent(m -> { // 있다면?
//            throw new IllegalStateException("이미 존재하는 회원입니다");  // 예외처리
//        });
        // 위의 코드를 아래와 같이 함축화할 수 있다
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                throw new IllegalStateException("이미 존재하는 회원입니다");
//            });
        // 위의 코드를 다시한번 리팩토링하여 함수화 할 수 있다. ctrl + T (Extract Method)
//        validateDuplicateMember(member);    // 중복회원 검증
//        memberRepository.save(member);      //통과시
//        return member.getId();              // 저장

        long start = System.currentTimeMillis();
        try{
            validateDuplicateMember(member);    // 중복회원 검증
            memberRepository.save(member);      //통과시
            return member.getId();              // 저장
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join(timeMs) = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        //return memberRepository.findAll();
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers(timeMs) = " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
