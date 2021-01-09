package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
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
class PetTypeSDJpaServiceTest {

    public static final String DESCRIPTION = "Cat";
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    PetTypeSDJpaService service;

    PetType returnPetType;

    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(1L).name(DESCRIPTION).build();
    }

    @Test
    void findAll() {
        Set<PetType> returnPetTypes = Set.of(PetType.builder().id(1L).build(),
                PetType.builder().id(2L).build());
        when(petTypeRepository.findAll()).thenReturn(returnPetTypes);
        Set<PetType> petTypes = service.findAll();

        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());
        verify(petTypeRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(1L)).thenReturn(Optional.of(returnPetType));
        PetType petType = service.findById(1L);
        assertNotNull(petType);
        assertEquals(1L, petType.getId());
        assertEquals(DESCRIPTION, petType.getName());
        verify(petTypeRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        PetType petType = service.findById(1L);

        assertNull(petType);
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().name(DESCRIPTION).build();
        when(petTypeRepository.save(petTypeToSave)).thenReturn(returnPetType);
        PetType savedOwner = service.save(petTypeToSave);
        assertNotNull(savedOwner);
        verify(petTypeRepository, times(1)).save(petTypeToSave);
    }

    @Test
    void delete() {
        service.delete(returnPetType);
        verify(petTypeRepository, times(1)).delete(returnPetType);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(petTypeRepository, times(1)).deleteById(1L);
    }
}