package ma.zyn.app.unit.ws.facade.admin.exigence;

import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence;
import ma.zyn.app.service.impl.admin.exigence.ReferentielFamilleExigenceAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.exigence.ReferentielFamilleExigenceRestAdmin;
import ma.zyn.app.ws.converter.exigence.ReferentielFamilleExigenceConverter;
import ma.zyn.app.ws.dto.exigence.ReferentielFamilleExigenceDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReferentielFamilleExigenceRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ReferentielFamilleExigenceAdminServiceImpl service;
    @Mock
    private ReferentielFamilleExigenceConverter converter;

    @InjectMocks
    private ReferentielFamilleExigenceRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllReferentielFamilleExigenceTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ReferentielFamilleExigenceDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ReferentielFamilleExigenceDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveReferentielFamilleExigenceTest() throws Exception {
        // Mock data
        ReferentielFamilleExigenceDto requestDto = new ReferentielFamilleExigenceDto();
        ReferentielFamilleExigence entity = new ReferentielFamilleExigence();
        ReferentielFamilleExigence saved = new ReferentielFamilleExigence();
        ReferentielFamilleExigenceDto savedDto = new ReferentielFamilleExigenceDto();

        // Mock the converter to return the referentielFamilleExigence object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved referentielFamilleExigence DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ReferentielFamilleExigenceDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ReferentielFamilleExigenceDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved referentielFamilleExigence DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
    }

}
