package pv826.repositories;

import pv826.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ramesh Fadatare
 *
 */
public interface MessageRepository extends JpaRepository<Message, Integer>{

}
