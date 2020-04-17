package pl.kukla.krzys.reactivewebflux.controller;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kukla.krzys.reactivewebflux.domain.Vendor;
import pl.kukla.krzys.reactivewebflux.repository.VendorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Krzysztof Kukla
 */
@RestController
@RequestMapping(VendorController.V1_VENDORS_BASE_URL)
@RequiredArgsConstructor
public class VendorController {

    static final String V1_VENDORS_BASE_URL = "/v1/vendors";
    private final VendorRepository vendorRepository;

    @GetMapping
    public Flux<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Vendor> getById(@PathVariable String id) {
        return vendorRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public Mono<Void> createVendor(@Valid @RequestBody Publisher<Vendor> vendorStream) {
        return vendorRepository.saveAll(vendorStream).then();
    }

}
