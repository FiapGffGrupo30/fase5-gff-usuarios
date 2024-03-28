package br.fiap.gff.user.models;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@UserDefinition
public class Identity {

    @Id
    @GeneratedValue
    private Long id;

    @Username
    @Column(unique = true)
    private String username;

    @Password
    private String password;

    @Roles
    private String role;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime createdAt;
}
