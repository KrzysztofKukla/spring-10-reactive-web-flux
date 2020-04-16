package pl.kukla.krzys.reactivewebflux.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kukla.krzys.reactivewebflux.domain.Category;
import pl.kukla.krzys.reactivewebflux.domain.Vendor;
import pl.kukla.krzys.reactivewebflux.repository.CategoryRepository;
import pl.kukla.krzys.reactivewebflux.repository.VendorRepository;

import java.util.Arrays;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
public class BootstrapLoader implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    @Override
    public void run(String... args) throws Exception {
        //subscribe and invoke it
        if (categoryRepository.count().block() == 0) {
            populateCategories();
        }
        if (vendorRepository.count().block() == 0) {
            populateVendors();
        }
    }

    private void populateVendors() {
        Vendor vendor1 = Vendor.builder().id("1").firstName("fistName vendor").lastName("first lastName Vendor").build();
        Vendor vendor2 = Vendor.builder().id("2").firstName("second fistName vendor").lastName("second lastName Vendor").build();

        vendorRepository.saveAll(Arrays.asList(vendor1, vendor2)).subscribe();
    }

    private void populateCategories() {
        Category category1 = Category.builder().id("1").description("first description").build();
        Category category2 = Category.builder().id("2").description("second description").build();

        categoryRepository.saveAll(Arrays.asList(category1, category2)).subscribe();
    }

}
