package br.fiap.gff.user.dto;

import br.fiap.gff.user.models.Wallet;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Wallet}
 */
public record WalletInsertRequest(
        String paymentMethod,
        String cardNumber,
        String cardName,
        String expirationDate,
        String securityCode,
        Boolean main) implements Serializable {

    public Wallet toModel() {
        return Wallet.builder()
                .paymentMethod(paymentMethod)
                .cardNumber(cardNumber)
                .cardName(cardName)
                .expirationDate(expirationDate)
                .securityCode(securityCode)
                .main(main)
                .createdAt(LocalDateTime.now())
                .build();
    }

}