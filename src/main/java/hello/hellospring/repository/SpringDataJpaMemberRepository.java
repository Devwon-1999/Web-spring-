package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
                                                    //JpaRepository를 받고있으면
                                                    //SpringData JPA가 구현체를 자동으로 등록을 해준다.
    @Override
    Optional<Member> findById(Long id);
}
