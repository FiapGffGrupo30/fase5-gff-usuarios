package br.fiap.gff.user.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String fullName;

    @Column(length = 50)
    private String nickName;

    @Column(unique = true, length = 14)
    private String document;

    @Column(length = 50)
    private String email;

    @Column(length = 14)
    private String phone;

    @Embedded
    private Address address;

    private LocalDateTime createdAt;

}
