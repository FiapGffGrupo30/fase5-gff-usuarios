package br.fiap.gff.user.usecases;

import br.fiap.gff.user.dto.CreateOrderRequest;
import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Order;

import java.util.List;
import java.util.UUID;

public interface OrderUseCase {

    UUID create(CreateOrderRequest request, Customer customer);

    List<Order> filterAllByCustomerId(Long customerId);

    List<Order> filterAllByCustomerIdAndStatus(Long customerId, String status);

    void updateStatusByCorrelationalId(UUID correlationalId, String status);

}
