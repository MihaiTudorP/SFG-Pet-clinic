package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerservice;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerservice, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerservice = ownerservice;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count ==0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = Owner.builder()
                .firstName("Michael")
                .lastName("Weston")
                .address("123 Brickerel St.")
                .city("Miami")
                .telephone("1234567890")
                .build();
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.of(1990,4, 1));
        mikesPet.setName("Rex");
        owner1.getPets().add(mikesPet);

        ownerservice.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("456 Wall St.");
        owner2.setAddress("New York");
        owner2.setTelephone("10263188080");
        Pet fionasCat = new Pet();
        fionasCat.setName("Missy");
        fionasCat.setBirthDate(LocalDate.of(2009, 8, 9));
        fionasCat.setOwner(owner2);
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionasCat);

        ownerservice.save(owner2);

        System.out.println("Loaded owners....");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);


        Specialty dentistry = new Specialty();
        radiology.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);


        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Leann");
        vet2.setLastName("Rhymes");
        vet2.getSpecialties().add(savedSurgery);
        vetService.save(vet2);
        System.out.println("Loaded Vets");

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.of(2020,12,31));
        catVisit.setDescription("Rabies vaccination");
        catVisit.setPet(fionasCat);
        visitService.save(catVisit);
    }
}
