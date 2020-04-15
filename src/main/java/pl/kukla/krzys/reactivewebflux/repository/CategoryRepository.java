package pl.kukla.krzys.reactivewebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.kukla.krzys.reactivewebflux.domain.Category;

/**
 * @author Krzysztof Kukla
 */
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
