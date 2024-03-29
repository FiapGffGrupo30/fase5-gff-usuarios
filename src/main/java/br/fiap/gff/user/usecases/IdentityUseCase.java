package br.fiap.gff.user.usecases;

import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Identity;

public interface IdentityUseCase {
    Identity save(Identity identity);

    Customer getCustomerByUsername(String username);

    void deleteById(Long id);

    void deleteByCustomerId(Long customerId);
}
