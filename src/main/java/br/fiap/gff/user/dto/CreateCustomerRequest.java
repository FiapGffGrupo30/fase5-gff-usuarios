package br.fiap.gff.user.dto;

import br.fiap.gff.user.models.Address;
import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Identity;
import io.quarkus.elytron.security.common.BcryptUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCustomerRequest {

    private String username;
    private String password;
    private String fullName;
    private String document;
    private String email;
    private String phone;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

    public Customer toCustomer() {
        String nickName = this.fullName.split(" ")[0];
        return new Customer(
                null,
                this.fullName,
                nickName,
                this.document,
                this.email,
                this.phone,
                new Address(
                        this.street,
                        this.number,
                        this.neighborhood,
                        this.city,
                        this.state,
                        this.zipCode
                ),
                LocalDateTime.now()
        );
    }

    public Identity toIdentity(Customer customer) {
        String encryptedPassword = BcryptUtil.bcryptHash(this.password);
        return new Identity(
                null,
                this.username,
                encryptedPassword,
                "CUSTOMER",
                customer,
                LocalDateTime.now()
        );
    }
}
