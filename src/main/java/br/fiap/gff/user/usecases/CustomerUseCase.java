package br.fiap.gff.user.usecases;

import br.fiap.gff.user.models.Customer;

public interface CustomerUseCase {

    Customer save(Customer customer);

    Long createAnonymousCustomer(String nickname);

    Customer getById(Long id);

}
