package springweb.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springweb.entity.Vegetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, Integer> {
    // case 1:
    @Query("SELECT v FROM Vegetable v WHERE v.id IN (SELECT DISTINCT od.vegetableId FROM OrderedDetail od)" +
            " AND v.categoryId = :categoryId AND LOWER(v.name) like CONCAT('%', LOWER(:name), '%') ")
    List<Vegetable> findTrueByNameAndCategoryId(@Param("categoryId") final Integer categoryId, @Param("name") final String name);

    // case 2:
    @Query("SELECT v FROM Vegetable v WHERE v.id IN (SELECT DISTINCT od.vegetableId FROM OrderedDetail od)" +
            " AND v.categoryId = :categoryId")
    List<Vegetable> findTrueByCategoryId(@Param("categoryId") final Integer categoryId);

    // case 3:
    @Query("SELECT v FROM Vegetable v WHERE v.id IN (SELECT DISTINCT od.vegetableId FROM OrderedDetail od)" +
            " AND LOWER(v.name) like CONCAT('%', LOWER(:name), '%') ")
    List<Vegetable> findTrueByName(@Param("name") final String name);

    // case 4:
    @Query("SELECT v FROM Vegetable v WHERE v.id IN (SELECT DISTINCT od.vegetableId FROM OrderedDetail od)")
    List<Vegetable> findTrue();

    // case 5:
    @Query("SELECT v FROM Vegetable v WHERE v.categoryId = :categoryId AND LOWER(v.name) like CONCAT('%', LOWER(:name), '%')")
    List<Vegetable> findByCategoryIdAndNameLike(@Param("categoryId") final Integer categoryId, @Param("name") final String name);

    // case 6:
    @Query("SELECT v FROM Vegetable v WHERE v.categoryId = :categoryId")
    List<Vegetable> findByCategoryId(@Param("categoryId") final Integer categoryId);

    // case 7:
    @Query("SELECT v FROM Vegetable v WHERE LOWER(v.name) like CONCAT('%', LOWER(:name), '%')")
    List<Vegetable> findByNameLike(@Param("name") final String name);

}
