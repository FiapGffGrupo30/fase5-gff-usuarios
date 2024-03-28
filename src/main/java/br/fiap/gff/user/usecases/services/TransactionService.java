package br.fiap.gff.user.usecases.services;

import br.fiap.gff.user.dto.TransactionProperties;
import br.fiap.gff.user.models.Wallet;
import br.fiap.gff.user.usecases.TransactionUseCase;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class TransactionService implements TransactionUseCase {

    private final ValueCommands<String, TransactionProperties> redisCommands;

    public TransactionService(RedisDataSource ds) {
        this.redisCommands = ds.value(TransactionProperties.class);
    }

    @Override
    public UUID create(Long customerId, Wallet paymentMethod) {
        UUID transactionId = UUID.randomUUID();
        TransactionProperties tp = TransactionProperties.builder()
                .customerId(customerId)
                .paymentMethod(paymentMethod)
                .build();
        redisCommands.setex(transactionId.toString(), 240, tp);
        return transactionId;
    }

}
