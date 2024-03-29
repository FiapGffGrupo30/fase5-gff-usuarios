package br.fiap.gff.user.usecases;

import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Wallet;

public interface CustomerUseCase {

    Customer save(Customer customer);

    Long createAnonymous(String nickname);

    Customer getById(Long id);

    void insertWallet(Long customerId, Wallet wallet);

    void sendNotification(Long customerId, String message);

    void deleteById(Long id);

}
