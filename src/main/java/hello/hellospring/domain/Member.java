package hello.hellospring.domain;

import jakarta.persistence.*;


@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 디비가 알아서 생성해 주는 것 IDENTITY
    private Long id; // 시스템이 저장하는 id

    //@Column(name = "username") ← db의 컬럼명과 매핑된다.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
