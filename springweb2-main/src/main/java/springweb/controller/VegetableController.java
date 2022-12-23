package springweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import springweb.dto.CategoryDto;
import springweb.dto.VegetableDto;

import springweb.requests.Search;
import springweb.services.CategoryService;
import springweb.services.VegetableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/v1/shop/vegetables")
public class VegetableController {
    private VegetableService service;
    private CategoryService categoryService;

    public VegetableController(VegetableService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getAll(Model m, Search search) {
        List<CategoryDto> categoryDtoList = categoryService.getAll();
        categoryDtoList.add(CategoryDto.builder().id(null).name("All").build());
        m.addAttribute("categories", categoryDtoList);
        log.info(search.toString());
        search.initInput();
        m.addAttribute("name", search.getName());
        m.addAttribute("categoryId", search.getCategoryId());
        m.addAttribute("banChay", search.getBanChay());
        List<VegetableDto> list = service.search(search);
        m.addAttribute("data", list);
        return "HomeShop";
    }
    
}
