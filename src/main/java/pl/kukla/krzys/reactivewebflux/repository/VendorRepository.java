package pl.kukla.krzys.reactivewebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.kukla.krzys.reactivewebflux.domain.Vendor;

/**
 * @author Krzysztof Kukla
 */
public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
