package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    PetMapService petMapService;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().name("Duke").petType(PetType.builder().name("Dog").build()).build());
    }

    @Test
    void findAll() {
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals("Duke", petMapService.findById(1L).getName());
    }

    @Test
    void save() {
        assertEquals(2L, petMapService.save(Pet.builder().name("Lucy").petType(PetType.builder().name("Cat").build()).build()).getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(1L));
        assertTrue(petMapService.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(1L);
        assertTrue(petMapService.findAll().isEmpty());
    }
}