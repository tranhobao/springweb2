package springweb.config;

import springweb.repository.CategoryRepository;
import springweb.repository.OrderedRepository;
import springweb.services.CategoryService;
import springweb.services.CustomerService;
import springweb.repository.CustomerRepository;
import springweb.repository.VegetableRepository;
import springweb.services.OrderedService;
import springweb.services.VegetableService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("springweb.entity")
@EnableJpaRepositories("springweb.repository")
public class Config {
    @Bean
    public VegetableService vegetableService(final VegetableRepository vegetableRepository) {
        return new VegetableService(vegetableRepository);
    }

    @Bean
    public CustomerService customerService(final CustomerRepository customerRepository) {
        return new CustomerService(customerRepository);
    }

    @Bean
    public OrderedService orderedService(final OrderedRepository orderedRepository, VegetableRepository vegetableRepository) {
        return new OrderedService(orderedRepository, vegetableRepository);
    }

    @Bean
    public CategoryService categoryService(final CategoryRepository categoryRepository) {
        return new CategoryService(categoryRepository);
    }
}
