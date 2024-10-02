package demoCar.repositories;

import demoCar.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByOwnerId(Integer ownerId);
    Optional<Contact> findByIdAndOwnerId(Integer id, Integer ownerId);
}