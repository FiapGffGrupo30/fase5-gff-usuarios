package br.fiap.gff.user.dto;

import br.fiap.gff.user.models.Address;
import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Identity;
import br.fiap.gff.user.models.Wallet;
import io.quarkus.elytron.security.common.BcryptUtil;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerCreateRequest {

    private String username;
    private String password;
    private String fullName;
    private LocalDate birthDate;
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
        Set<Wallet> wallets = new HashSet<>();
        return new Customer(
                null,
                this.fullName,
                nickName,
                this.birthDate,
                this.document,
                this.email,
                this.phone,
                getAddress(),
                wallets,
                LocalDateTime.now()
        );
    }

    private Address getAddress() {
        return new Address(
                this.street,
                this.number,
                this.neighborhood,
                this.city,
                this.state,
                this.zipCode
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
