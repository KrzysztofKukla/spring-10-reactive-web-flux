package pl.kukla.krzys.reactivewebflux.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.kukla.krzys.reactivewebflux.domain.Vendor;
import pl.kukla.krzys.reactivewebflux.repository.VendorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private VendorController vendorController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void getAll() throws Exception {
        Vendor vendor1 = Vendor.builder().id("1").firstName("f1").lastName("l1").build();
        Vendor vendor2 = Vendor.builder().id("2").firstName("f2").lastName("l2").build();

        BDDMockito.given(vendorRepository.findAll()).willReturn(Flux.just(vendor1, vendor2));

        webTestClient.get()
            .uri(VendorController.V1_VENDORS_BASE_URL)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectBodyList(Vendor.class)
            .hasSize(2);

        BDDMockito.then(vendorRepository).should().findAll();
    }

    @Test
    void getById() throws Exception {
        Vendor vendor1 = Vendor.builder().id("1").firstName("f1").lastName("l1").build();
        BDDMockito.given(vendorRepository.findById(ArgumentMatchers.anyString())).willReturn(Mono.just(vendor1));
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", "1");

        webTestClient.get()
            .uri(VendorController.V1_VENDORS_BASE_URL + "/{id}", "1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectBody(Vendor.class);

        BDDMockito.then(vendorRepository).should().findById(ArgumentMatchers.anyString());
    }

}