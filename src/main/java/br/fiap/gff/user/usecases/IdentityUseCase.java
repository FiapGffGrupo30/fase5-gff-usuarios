package br.fiap.gff.user.usecases;

import br.fiap.gff.user.models.Identity;

public interface IdentityUseCase {
    Identity save(Identity identity);
}
