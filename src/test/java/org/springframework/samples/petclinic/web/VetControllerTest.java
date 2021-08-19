package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private ClinicService clinicService;

    @Mock
    Map<String, Object> model = new HashMap<>();

    @InjectMocks
    private VetController vetController;

    List<Vet> vetsList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Vet vet = new Vet();
        vetsList.add(vet);
        given(this.clinicService.findVets()).willReturn(vetsList);
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