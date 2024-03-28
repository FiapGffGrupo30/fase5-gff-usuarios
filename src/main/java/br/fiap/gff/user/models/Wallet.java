package br.fiap.gff.user.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Wallet {

    private String paymentMethod;
    private String cardNumber;
    private String cardName;
    private String expirationDate;
    private String securityCode;
    private Boolean main;
    private LocalDateTime createdAt;

}
