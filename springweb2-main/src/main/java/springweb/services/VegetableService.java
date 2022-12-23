package springweb.services;

import springweb.requests.Search;
import springweb.dto.VegetableDto;
import springweb.entity.Vegetable;
import springweb.repository.VegetableRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;


public class VegetableService {
    private VegetableRepository vegetableRepository;
    public VegetableService(VegetableRepository vegetableRepository) {
        this.vegetableRepository = vegetableRepository;
    }

    /**
     * Find All vegetable from local db
     * @return List<VegetableDto>
     */
    public List<VegetableDto> findAll() {
        List<Vegetable> vegetableList = vegetableRepository.findAll();
        return toListVegetableDto(vegetableList);
    }

    /**
     * Convert List<Vegetable> to List<VegetableDto> to safe entity
     * @param vegetableList
     * @return List<VegetableDto>
     */
    private List<VegetableDto> toListVegetableDto(List<Vegetable> vegetableList) {
        return vegetableList.stream().map(this::toVegetableDto).collect(Collectors.toList());
    }

    /**
     * Convert Class Vegetable to Class VegetableDto
     * @param vegetable
     * @return VegetableDto
     */
    private VegetableDto toVegetableDto(Vegetable vegetable) {
        VegetableDto vegetableDto = VegetableDto.builder().build();
        BeanUtils.copyProperties(vegetable, vegetableDto);
        return vegetableDto;
    }

    /**
     * Find Vegetables by banchay=true && categoryId && name
     * @param categoryId
     * @param name
     * @return List<VegetableDto>
     */
    private List<VegetableDto> findTrueByNameAndCategoryId (Integer categoryId, String name) {
        List<Vegetable> vegetableList = vegetableRepository.findTrueByNameAndCategoryId(categoryId, name);
        return toListVegetableDto(vegetableList);
    }

    /**
     * Find Vegetables by banchay=true && categoryId
     * @param categoryId
     * @return List<VegetableDto>
     */
    private List<VegetableDto> findTrueByCategoryId (Integer categoryId) {
        List<Vegetable> vegetableList = vegetableRepository.findTrueByCategoryId(categoryId);
        return toListVegetableDto(vegetableList);
    }

    /**
     * Find Vegetables by banchay=true && name
     * @param name
     * @return List<VegetableDto>
     */
    private List<VegetableDto> findTrueByName (String name) {
        List<Vegetable> vegetableList = vegetableRepository.findTrueByName(name);
        return toListVegetableDto(vegetableList);
    }

    /**
     * Find Vegetables by banchay=true
     * @return List<VegetableDto>
     */
    private List<VegetableDto> findTrue () {
        List<Vegetable> vegetableList = vegetableRepository.findTrue();
        return toListVegetableDto(vegetableList);
    }

    /**
     * Find Vegetables by categoryId && name
     * @param categoryId
     * @param name
     * @return List<VegetableDto>
     */
    private List<VegetableDto> findByCategoryIdAndNameLike(Integer categoryId, String name) {
        List<Vegetable> vegetableList = vegetableRepository.findByCategoryIdAndNameLike(categoryId, name);
        return toListVegetableDto(vegetableList);
    }

    /**
     * Find Vegetables by categoryId
     * @param categoryId
     * @return List<VegetableDto>
     */
    private List<VegetableDto> findByCategoryId(Integer categoryId) {
        List<Vegetable> vegetableList = vegetableRepository.findByCategoryId(categoryId);
        return toListVegetableDto(vegetableList);
    }

    /**
     * Find Vegetables by && name
     * @param name
     * @return List<VegetableDto>
     */
    private List<VegetableDto> findByNameLike(String name) {
        List<Vegetable> vegetableList = vegetableRepository.findByNameLike(name);
        return toListVegetableDto(vegetableList);
    }

    /**
     * Get product search filter (categoryId - ban chay - name) (Nguyên cái chức năng 2 xem sản phẩm)
     * @param search
     * @return List<VegetableDto>
     */
    public List<VegetableDto> search(final Search search) {
        switch (search.checkInput()) {
            case 1: // ban chay - categoryId - name
                return findTrueByNameAndCategoryId(search.getCategoryId(), search.getName());
            case 2: // ban chay - categoryId
                return findTrueByCategoryId(search.getCategoryId());
            case 3: // ban chay - name
                return findTrueByName(search.getName());
            case 4: // ban chay
                return findTrue();
            case 5: // categoryId - name
                return findByCategoryIdAndNameLike(search.getCategoryId(), search.getName());
            case 6: // categoryId
                return findByCategoryId(search.getCategoryId());
            case 7: // name
                return findByNameLike(search.getName());
            default:
                return findAll();
        }
    }

    public VegetableDto findById(Integer vegetableId) {
        Vegetable vegetable = vegetableRepository.findById(vegetableId).get();
        VegetableDto vegetableDto = VegetableDto.builder().build();
        BeanUtils.copyProperties(vegetable, vegetableDto);
        return vegetableDto;
    }
}
