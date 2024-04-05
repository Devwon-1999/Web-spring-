package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //회원가입
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
        validateDuplicateMember(member);    // 중복회원 검증

        memberRepository.save(member);      //통과시
        return member.getId();              // 저장
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
