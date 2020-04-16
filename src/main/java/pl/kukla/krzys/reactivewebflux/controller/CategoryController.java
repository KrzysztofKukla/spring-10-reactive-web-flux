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
import pl.kukla.krzys.reactivewebflux.domain.Category;
import pl.kukla.krzys.reactivewebflux.repository.CategoryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Krzysztof Kukla
 */
@RestController
@RequestMapping(CategoryController.V1_CATEGORIES_BASE_URL)
@RequiredArgsConstructor
public class CategoryController {
    static final String V1_CATEGORIES_BASE_URL = "/v1/categories";

    private final CategoryRepository categoryRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Flux<Category> getAll() {
        return categoryRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Mono<Category> getById(@PathVariable String id) {
        return categoryRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Void> createCategory(@Valid @RequestBody Publisher<Category> category) {
        return categoryRepository.saveAll(category).then();
    }

}
