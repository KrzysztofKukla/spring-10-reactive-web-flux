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
import org.springframework.test.web.servlet.MockMvc;
import pl.kukla.krzys.reactivewebflux.domain.Category;
import pl.kukla.krzys.reactivewebflux.repository.CategoryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryRepository categoryRepository;

    private WebTestClient webTestClient;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void getAll() throws Exception {
        Category cat1 = Category.builder().id("1").description("d").build();
        Category cat2 = Category.builder().id("2").description("e").build();
        Flux<Category> categoryFlux = Flux.just(cat1, cat2);

        BDDMockito.given(categoryRepository.findAll()).willReturn(categoryFlux);

        webTestClient.get().uri(CategoryController.V1_CATEGORIES_BASE_URL)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectBodyList(Category.class)
            .hasSize(2);

        BDDMockito.then(categoryRepository).should().findAll();
    }

    @Test
    void getById() throws Exception {
        Category category = Category.builder().id("1").description("d").build();

        BDDMockito.given(categoryRepository.findById(ArgumentMatchers.anyString())).willReturn(Mono.just(category));

        webTestClient.get().uri(CategoryController.V1_CATEGORIES_BASE_URL + "/{id}", "1")
            .exchange()
            .expectBody(Category.class);

        BDDMockito.then(categoryRepository).should().findById(ArgumentMatchers.anyString());
    }

}