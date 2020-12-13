package guru.springframework.sfgpetclinic.model;

import java.util.Set;

public class Vet extends Person {
    private Set<VetSpecialty> specialties;

    public Set<VetSpecialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<VetSpecialty> specialties) {
        this.specialties = specialties;
    }
}
