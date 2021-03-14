package building.registry.api.Controller;

import building.registry.api.Model.Tax;
import building.registry.api.Service.PropertyService;
import building.registry.api.Service.TaxService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaxController.class)
class TaxControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private TaxService taxService;
    @MockBean
    private PropertyService propService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void AddTaxTest () throws Exception {

        Tax tax = new Tax ("Residential", 15);
        int id = 1;
        String json = objectMapper.writeValueAsString(tax);

        mockMvc.perform(post("/v1/property/tax")
                .contentType("application/json;charset=UTF-8")
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        json = "hello";
        mockMvc.perform(post("/v1/property/tax")
                .contentType("application/json;charset=UTF-8")
                .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void getTaxTest() throws Exception {
        Tax tax = new Tax ("Residential", 15);
        tax.setId(1);
        String json = objectMapper.writeValueAsString(tax);

        Mockito.when(taxService.getTax("Residential")).thenReturn(tax);
        mockMvc.perform(get("/tax/Residential"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updateTaxTest() {
    }

    @Test
    void removeTaxTest() {
    }

    @Test
    void getTaxesTest() {
    }

    @Test
    void getYearlyTaxesTest() {
    }
}