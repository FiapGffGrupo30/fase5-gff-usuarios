package br.fiap.gff.user.usecases.services;

import br.fiap.gff.user.exceptions.CustomerNotFoundException;
import br.fiap.gff.user.exceptions.CustomerTwoMainWalletsException;
import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Wallet;
import br.fiap.gff.user.repository.CustomerRepository;
import br.fiap.gff.user.usecases.CustomerUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@ApplicationScoped
@RequiredArgsConstructor
public class CustomerService implements CustomerUseCase {


    private final CustomerRepository repository;

    @Override
    @Transactional
    public Customer save(Customer customer) {
        repository.persist(customer);
        return customer;
    }

    @Override
    @Transactional
    public Long createAnonymous(String nickname) {
        Customer c = new Customer();
        c.setNickName(nickname);
        c.setCreatedAt(LocalDateTime.now());
        repository.persist(c);
        return c.getId();
    }


    @Override
    public Customer getById(Long id) {
        return repository.findByIdOptional(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public void insertWallet(Long customerId, Wallet wallet) {
        Customer c = getById(customerId);
        if (wallet.getMain() && c.getWallets().stream().anyMatch(Wallet::getMain)) {
            throw new CustomerTwoMainWalletsException(customerId);
        }
        c.getWallets().add(wallet);
        repository.persist(c);
    }

}
