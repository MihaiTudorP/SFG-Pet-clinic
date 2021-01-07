package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VetMapServiceTest {

    VetMapService vetMapService;

    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialtyMapService());
        vetMapService.save(Vet.builder().firstName("Mick").lastName("Jagger").build());
    }

    @Test
    void findAll() {
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals("Mick", vetMapService.findById(1L).getFirstName());
    }

    @Test
    void save() {
        assertEquals(2L, vetMapService.save(Vet.builder().firstName("Keith").lastName("Richards").build()).getId());
    }

    @Test
    void delete() {
        vetMapService.delete(vetMapService.findById(1L));
        assertTrue(vetMapService.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        vetMapService.deleteById(1L);
        assertTrue(vetMapService.findAll().isEmpty());
    }
}