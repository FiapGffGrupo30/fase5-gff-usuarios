package br.fiap.gff.user.services;

import br.fiap.gff.user.broker.OrderProducer;
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
    private final OrderProducer producer;


    @Override
    @Transactional
    public UUID create(OrderCreateRequest request, Customer customer) {
        Order order = Order.builder().customer(customer).correlationalId(UUID.randomUUID()).status("PENDING").build();
        repository.persist(order);
        UUID correlationalId = order.getCorrelationalId();
        producer.sendOrder(request, correlationalId);
        return correlationalId;
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
    public void updateStatusByCorrelationalId(UUID correlationalId, String status) {
        Order o = filterByCorrelationalId(correlationalId);
        o.setStatus(status);
        repository.persist(o);
    }

    private Order filterByCorrelationalId(UUID correlationalId) {
        return repository.find("correlationalId = ?1", correlationalId).firstResult();
    }

}
