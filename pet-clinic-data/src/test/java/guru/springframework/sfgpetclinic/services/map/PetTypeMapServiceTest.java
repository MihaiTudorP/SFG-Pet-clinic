package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;

    @BeforeEach
    void setUp(){
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder().name("Dog").build());
    }

    @Test
    void findAll() {
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(1L);
        assertTrue(petTypeMapService.findAll().isEmpty());
    }

    @Test
    void delete() {
        petTypeMapService.delete(petTypeMapService.findById(1L));
        assertTrue(petTypeMapService.findAll().isEmpty());
    }

    @Test
    void save() {
        assertEquals(2L, petTypeMapService.save(PetType.builder().name("Cat").build()).getId());
    }

    @Test
    void findById() {
        assertEquals("Dog", petTypeMapService.findById(1L).getName());
    }
}