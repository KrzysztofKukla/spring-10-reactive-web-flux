package pl.kukla.krzys.reactivewebflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import pl.kukla.krzys.reactivewebflux.repository.CategoryRepository;

/**
 * @author Krzysztof Kukla
 */
@SpringJUnitConfig
class CategoryControllerIT {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

}