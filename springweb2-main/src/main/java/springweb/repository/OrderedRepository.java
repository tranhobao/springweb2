package springweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springweb.entity.Ordered;

public interface OrderedRepository extends JpaRepository<Ordered, Integer> {

}
