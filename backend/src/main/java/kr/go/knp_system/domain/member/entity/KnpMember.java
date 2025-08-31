package kr.go.knp_system.domain.member.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "knp_member")
public class KnpMember {

    @Id
    @Column(name = "em_idnum", length = 30, nullable = false)
    private String emIdNum;

    @Column(name = "em_password", length = 100, nullable = false)
    private String emPasswd;

    // 1:N (Member → HR)
    @OneToMany(mappedBy = "knpMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KnpHR> hrList = new ArrayList<>();
}
