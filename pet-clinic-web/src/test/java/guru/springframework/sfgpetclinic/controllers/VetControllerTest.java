package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    VetService vetService;
    @InjectMocks
    VetController controller;

    MockMvc mockMvc;

    Set<Vet> vets;

    @BeforeEach
    void setUp() {
        vets = Set.of(Vet.builder().id(1L).build(),
                Vet.builder().id(2L).build());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listVets() throws Exception {
        when(vetService.findAll()).thenReturn(vets);
        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.model().attribute("vets", hasSize(2)));
    }
}