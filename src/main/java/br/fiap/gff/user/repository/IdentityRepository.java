package br.fiap.gff.user.repository;

import br.fiap.gff.user.models.Identity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IdentityRepository implements PanacheRepository<Identity> {
}
