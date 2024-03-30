package br.fiap.gff.user.usecases;

import br.fiap.gff.user.dto.OrderCreateRequest;
import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Order;

import java.util.List;
import java.util.UUID;

public interface OrderUseCase {

    void create(OrderCreateRequest request, Customer customer);

    List<Order> filterAllByCustomerId(Long customerId);

    List<Order> filterAllByCustomerIdAndStatus(Long customerId, String status);

    void updateStatusByTransactionId(UUID correlationalId, String status);

    void deleleByUserId(Long id);
}
