package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {
    VisitMapService visitMapService;
    PetTypeMapService petTypeMapService;
    PetMapService petMapService;
    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp(){
        visitMapService = new VisitMapService();
        petMapService = new PetMapService();
        petTypeMapService = new PetTypeMapService();
        ownerMapService = new OwnerMapService(petTypeMapService, petMapService);
        PetType dog = petTypeMapService.save(PetType.builder().name("Dog").build());
        Owner owner = ownerMapService.save(Owner.builder().firstName("John").lastName("Deacon").build());
        Pet pet = petMapService.save(Pet.builder().name("Molly").petType(dog).owner(owner).build());
        visitMapService.save(Visit.builder()
                .date(LocalDate.of(2021,01,04))
                .description("Vaccination")
                .pet(pet)
                .build());
    }

    @Test
    void findAll() {
        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void deleteById() {
        visitMapService.deleteById(1L);
        assertTrue(visitMapService.findAll().isEmpty());
    }

    @Test
    void delete() {
        visitMapService.delete(visitMapService.findById(1L));
        assertTrue(visitMapService.findAll().isEmpty());
    }

    @Test
    void save() {
        assertEquals(2L, visitMapService.save(Visit.builder()
                .date(LocalDate.of(2021,01,04))
                .description("Vaccination")
                .pet(petMapService.findById(1L))
                .build()).getId());
        assertThrows(RuntimeException.class, () -> visitMapService.save(Visit.builder().build()));
        assertThrows(RuntimeException.class, () -> visitMapService.save(Visit.builder().pet(Pet.builder().build()).build()));
        assertThrows(RuntimeException.class, () -> visitMapService.save(Visit.builder().pet(Pet.builder().owner(Owner.builder().build()).build()).build()));
        assertThrows(RuntimeException.class, () -> visitMapService.save(Visit.builder().pet(Pet.builder().id(1L).owner(Owner.builder().build()).build()).build()));
        assertEquals(2, visitMapService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(LocalDate.of(2021,01,04), visitMapService.findById(1L).getDate());
    }
}