package hello.hellospring.service;

import hello.hellospring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//이 방법이 훨씬 간결하고 편하다.
@Configuration
public class SpringConfig {
//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

    //JPA
//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

    //JPA with SpringData JPA
    private final  MemberRepository memberRepository;
    @Autowired //생성자ㅣ가 하나일 경우 생략할 수 있다.
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        // return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em); //JPA
//    }
}
