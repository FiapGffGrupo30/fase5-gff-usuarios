package br.fiap.gff.user.usecases.services;

import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Identity;
import br.fiap.gff.user.repository.IdentityRepository;
import br.fiap.gff.user.usecases.IdentityUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class IdentityService implements IdentityUseCase {

    private final IdentityRepository repository;

    @Override
    @Transactional
    public Identity save(Identity identity) {
        repository.persist(identity);
        return identity;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return repository.find("username", username)
                .firstResult().getCustomer();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByCustomerId(Long customerId) {
        repository.delete("customer.id", customerId);
    }


}
