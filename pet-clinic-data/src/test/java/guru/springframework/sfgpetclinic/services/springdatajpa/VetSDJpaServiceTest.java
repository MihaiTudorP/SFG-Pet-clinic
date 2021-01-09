package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    public static final String LAST_NAME = "Jones";
    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService service;

    Vet returnVet;

    @BeforeEach
    void setUp(){
        returnVet = Vet.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Vet> returnVets = Set.of(Vet.builder().id(1L).build(),
                Vet.builder().id(2L).build());
        when(vetRepository.findAll()).thenReturn(returnVets);
        Set<Vet> vets = service.findAll();

        assertNotNull(vets);
        assertEquals(2, vets.size());
        verify(vetRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(vetRepository.findById(1L)).thenReturn(Optional.of(returnVet));
        Vet vet = service.findById(1L);
        assertNotNull(vet);
        assertEquals(1L, vet.getId());
        assertEquals(LAST_NAME, vet.getLastName());
        verify(vetRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Vet vet = service.findById(1L);

        assertNull(vet);
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().lastName(LAST_NAME).build();
        when(vetRepository.save(vetToSave)).thenReturn(returnVet);
        Vet savedVet = service.save(vetToSave);
        assertNotNull(savedVet);
        verify(vetRepository, times(1)).save(vetToSave);
    }

    @Test
    void delete() {
        service.delete(returnVet);
        verify(vetRepository, times(1)).delete(returnVet);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(vetRepository, times(1)).deleteById(1L);
    }
}