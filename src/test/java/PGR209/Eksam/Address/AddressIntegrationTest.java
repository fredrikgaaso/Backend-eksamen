package PGR209.Eksam.Address;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AddressIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    void shouldGetAddresses() throws Exception {
        mockMvc.perform(get("/api/address"))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }
    @Test
    void shouldGetAddressByID() throws Exception {
        long addressId = 1L;
        Address address = new Address();
        address.setAddressId(addressId);
        when(addressService.getAddressById(addressId)).thenReturn(address);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/address/{id}", addressId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressId").value(addressId));
    }


}
