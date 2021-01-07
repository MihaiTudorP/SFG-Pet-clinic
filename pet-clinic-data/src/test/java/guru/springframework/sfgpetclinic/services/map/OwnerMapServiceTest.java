package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp(){
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().firstName("Mike").lastName("Pence").build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        assertEquals(1L, ownerMapService.findById(1L).getId());
    }

    @Test
    void save() {
        Owner owner = ownerMapService.save(Owner.builder().firstName("Donald").lastName("Trump").build());
        assertEquals(2L, owner.getId());
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        Owner owner = ownerMapService.findAll().stream().findFirst().orElse(null);
        ownerMapService.delete(owner);
        assertTrue(ownerMapService.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(1L);
        assertTrue(ownerMapService.findAll().isEmpty());
    }

    @Test
    void findByLastName() {
        assertEquals(1L, ownerMapService.findByLastName("Pence").getId());
        assertThrows(IllegalArgumentException.class, () -> ownerMapService.findByLastName(null));
    }
}