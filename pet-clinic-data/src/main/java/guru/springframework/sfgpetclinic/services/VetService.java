package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findByLastName(String lastName);
    Vet findbyId(Long id);
    Vet save(Vet Vet);
    Set<Vet> findAll();
}
