package net.oussama.authflow_securedocs.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
@Builder
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Integer ownerId;
    @ManyToOne
    @Transient
    private User owner;
}
