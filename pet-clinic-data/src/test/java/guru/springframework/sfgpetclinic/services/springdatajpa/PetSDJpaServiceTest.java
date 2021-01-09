package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    public static final String NAME = "Duke";
    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService service;

    Pet returnPet;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(1L).name(NAME).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPets = Set.of(Pet.builder().id(1L).build(),
                Pet.builder().id(2L).build());
        when(petRepository.findAll()).thenReturn(returnPets);
        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(returnPet));
        Pet pet = service.findById(1L);
        assertNotNull(pet);
        assertEquals(1L, pet.getId());
        assertEquals(NAME, pet.getName());
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet pet = service.findById(1L);

        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().name(NAME).build();
        when(petRepository.save(petToSave)).thenReturn(returnPet);
        Pet savedPet = service.save(petToSave);
        assertNotNull(savedPet);
        verify(petRepository).save(petToSave);
    }

    @Test
    void delete() {
        service.delete(returnPet);
        verify(petRepository, times(1)).delete(returnPet);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(petRepository, times(1)).deleteById(1L);
    }
}