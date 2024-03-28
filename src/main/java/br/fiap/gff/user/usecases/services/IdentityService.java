package br.fiap.gff.user.usecases.services;

import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Identity;
import br.fiap.gff.user.repository.IdentityRepository;
import br.fiap.gff.user.usecases.IdentityUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class IdentityService implements IdentityUseCase {

    private final IdentityRepository repository;

    @Override
    public Identity save(Identity identity) {
        repository.persist(identity);
        return identity;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return repository.find("username", username)
                .firstResult().getCustomer();
    }


}