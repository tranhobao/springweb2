package springweb.services;

import org.springframework.beans.BeanUtils;
import springweb.dto.CategoryDto;
import springweb.entity.Category;
import springweb.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream()
            .map(this::toCategoryDto)
            .collect(Collectors.toList());
    }

    private CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder().build();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }
}
