package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
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
class SpecialtySDJpaServiceTest {
    public static final String DESCRIPTION = "Otorhinolaryngologist";
    @Mock
    SpecialtyRepository specialtyRepository;
    @InjectMocks
    SpecialtySDJpaService service;

    Specialty returnSpecialty;

    @BeforeEach
    void setUp() {
        returnSpecialty = Specialty.builder().id(1L).description(DESCRIPTION).build();
    }

    @Test
    void findAll() {
        Set<Specialty> returnSpecialties = Set.of(Specialty.builder().id(1L).build(),
                Specialty.builder().id(2L).build());
        when(specialtyRepository.findAll()).thenReturn(returnSpecialties);
        Set<Specialty> specialties = service.findAll();

        assertNotNull(specialties);
        assertEquals(2, specialties.size());
        verify(specialtyRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(returnSpecialty));
        Specialty specialty = service.findById(1L);
        assertNotNull(specialty);
        assertEquals(1L, specialty.getId());
        assertEquals(DESCRIPTION, specialty.getDescription());
        verify(specialtyRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());

        Specialty specialty = service.findById(1L);

        assertNull(specialty);
    }

    @Test
    void save() {
        Specialty specialtyToSave = Specialty.builder().description(DESCRIPTION).build();
        when(specialtyRepository.save(specialtyToSave)).thenReturn(returnSpecialty);
        Specialty savedSpecialty = service.save(specialtyToSave);
        assertNotNull(savedSpecialty);
        verify(specialtyRepository, times(1)).save(specialtyToSave);
    }

    @Test
    void delete() {
        service.delete(returnSpecialty);
        verify(specialtyRepository, times(1)).delete(returnSpecialty);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(specialtyRepository, times(1)).deleteById(1L);
    }
}