package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private ClinicService clinicService;

    @Mock
    Map<String, Object> model = new HashMap<>();

    @InjectMocks
    private VetController vetController;

    List<Vet> vetsList = new ArrayList<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vetsList.add(new Vet());

        given(this.clinicService.findVets()).willReturn(vetsList);

        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void testControllerShowVetList() throws Exception {
        mockMvc.perform(get("/vets.html")) //ask the request
                .andExpect(status().isOk()) //get response back
                .andExpect(model().attributeExists("vets")) //expect vets in the model
                .andExpect(view().name("vets/vetList")); //expect to return to the view
    }

    @Test
    @DisplayName("Test Show Vet List")
    void showVetList() {
        //when
        String view = vetController.showVetList(model);

        //then
        assertThat(view).isEqualTo("vets/vetList");
        then(clinicService).should().findVets();
        then(model).should().put(anyString(), any());
    }

    @Test
    @DisplayName("Test Show Resources Vet List")
    void showResourcesVetList() {
        Vets vets = vetController.showResourcesVetList();

        assertThat(vets).isNotNull();
        then(clinicService).should().findVets();
    }
}