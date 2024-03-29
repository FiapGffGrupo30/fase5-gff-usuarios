package br.fiap.gff.user.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(schema = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String fullName;

    @Column(length = 50)
    private String nickName;

    private LocalDate birthDate;

    @Column(unique = true, length = 14)
    private String document;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String phone;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(schema = "customer", name = "customer_wallets",
            joinColumns = @JoinColumn(name = "customer_id"))
    private Set<Wallet> wallets = new HashSet<>();

    private LocalDateTime createdAt;

}
