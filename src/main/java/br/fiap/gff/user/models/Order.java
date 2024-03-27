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
@Table(name = "CustomerOrder", indexes = @Index(name = "idx_correlationalId", columnList = "correlationalId"))
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID correlationalId;

    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
