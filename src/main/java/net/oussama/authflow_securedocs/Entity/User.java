package net.oussama.authflow_securedocs.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String Role;
    private String email;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Documents> documents;
}
