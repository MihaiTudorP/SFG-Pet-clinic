package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialtyMapServiceTest {

    public static final String SPECIALTY_NAME = "Oncologist";
    SpecialtyMapService specialtyMapService;

    @BeforeEach
    void setUp() {
        specialtyMapService = new SpecialtyMapService();
        specialtyMapService.save(Specialty.builder().description(SPECIALTY_NAME).build());
    }

    @Test
    void findAll() {
        assertEquals(1, specialtyMapService.findAll().size());
    }

    @Test
    void deleteById() {
        specialtyMapService.deleteById(1L);
        assertTrue(specialtyMapService.findAll().isEmpty());
    }

    @Test
    void delete() {
        specialtyMapService.delete(specialtyMapService.findById(1L));
        assertTrue(specialtyMapService.findAll().isEmpty());
    }

    @Test
    void save() {
        assertEquals(2L, specialtyMapService.save(Specialty.builder().description("Radiologist").build()).getId());
        assertEquals(2, specialtyMapService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(SPECIALTY_NAME, specialtyMapService.findById(1L).getDescription());
    }
}