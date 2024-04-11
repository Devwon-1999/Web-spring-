package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //이 한줄로 JPA가 insert쿼리를 만들어 DB에 집어넣어준다 id까지 setId해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //ctrl + shift + alt + t inline 선택하면 아래의 결과와 같이 합쳐주는 단축어이다.
//        List<Member> result = em.createQuery("select m from Member m", Member.class)
//                                .getResultList();
//        return result;
                                //객체를 대상으로 날리는 쿼리
        return em.createQuery("select m from Member m", Member.class)
                                .getResultList();

    }
}
