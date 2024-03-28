package br.fiap.gff.user.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CustomerOrder", indexes = @Index(name = "idx_transactionId", columnList = "transactionId"))
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID transactionId;

    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
