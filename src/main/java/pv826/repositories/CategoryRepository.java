package pv826.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pv826.entities.Category;

/**
 * @author Ramesh Fadatare
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
