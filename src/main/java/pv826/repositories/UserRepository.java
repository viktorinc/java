package pv826.repositories;

import pv826.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Ramesh Fadatare
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>
{

	User findByEmail(String email);

}
