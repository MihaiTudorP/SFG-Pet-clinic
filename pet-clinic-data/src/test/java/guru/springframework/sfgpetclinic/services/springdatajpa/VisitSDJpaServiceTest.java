package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
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
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
    public static final String DESCRIPTION = "Periodic check-up";
    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    Visit returnVisit;

    @BeforeEach
    void setUp(){
        returnVisit = Visit.builder().id(1L).description(DESCRIPTION).build();
    }

    @Test
    void findAll() {
        Set<Visit> returnVisits = Set.of(Visit.builder().id(1L).build(),
                Visit.builder().id(2L).build());
        when(visitRepository.findAll()).thenReturn(returnVisits);
        Set<Visit> visits = service.findAll();

        assertNotNull(visits);
        assertEquals(2, visits.size());
        verify(visitRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(visitRepository.findById(1L)).thenReturn(Optional.of(returnVisit));
        Visit visit = service.findById(1L);
        assertNotNull(visit);
        assertEquals(1L, visit.getId());
        assertEquals(DESCRIPTION, visit.getDescription());
        verify(visitRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());

        Visit visit = service.findById(1L);

        assertNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().description(DESCRIPTION).build();
        when(visitRepository.save(visitToSave)).thenReturn(returnVisit);
        Visit savedVisit = service.save(visitToSave);
        assertNotNull(savedVisit);
        verify(visitRepository, times(1)).save(visitToSave);
    }

    @Test
    void delete() {
        service.delete(returnVisit);
        verify(visitRepository, times(1)).delete(returnVisit);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository, times(1)).deleteById(1L);
    }
}