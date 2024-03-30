package br.fiap.gff.user.usecases.services;

import br.fiap.gff.user.application.events.OrderSendEvent;
import br.fiap.gff.user.dto.OrderCreateRequest;
import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Order;
import br.fiap.gff.user.repository.OrderRepository;
import br.fiap.gff.user.usecases.OrderUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {

    private final OrderRepository repository;
    private final OrderSendEvent orderEvent;


    @Override
    @Transactional
    public void create(OrderCreateRequest request, Customer customer) {
        Order order = Order.builder().customer(customer).transactionId(request.transactionId()).status("PENDING").build();
        repository.persist(order);
        orderEvent.send(request);
    }

    @Override
    public List<Order> filterAllByCustomerId(Long customerId) {
        return repository.list("customer.id = ?1", customerId);
    }

    @Override
    public List<Order> filterAllByCustomerIdAndStatus(Long customerId, String status) {
        return repository.list("customer.id = ?1 and status = ?2", customerId, status);
    }

    @Override
    @Transactional
    public void updateStatusByTransactionId(UUID transactionId, String status) {
        Order o = filterByTransactionId(transactionId);
        repository.update("status = ?1 where id = ?2", status, o.getId());
    }

    @Override
    @Transactional
    public void deleleByUserId(Long customerId) {

        repository.delete("customer.id = ?1", customerId);
    }

    private Order filterByTransactionId(UUID transactionId) {
        return repository.find("transactionId = ?1", transactionId).firstResult();
    }

}
