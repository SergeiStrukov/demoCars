package demoCar.services;

import demoCar.models.Contact;
import demoCar.models.Phone;
import demoCar.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact createContact(String username, Contact contact) {
        contact.setOwnerId(username.hashCode()); // простой способ задать ownerId
        for (Phone phone : contact.getPhones()) {
            phone.setContact(contact);
        }
        return contactRepository.save(contact);
    }

    public List<Contact> getContactsByUsername(String username) {
        Integer ownerId = username.hashCode();
        return contactRepository.findByOwnerId(ownerId);
    }

    public Contact updateContact(String username, Integer contactId, Contact contactDetails) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new RuntimeException("Contact not found"));
        if (!contact.getOwnerId().equals(username.hashCode())) {
            throw new RuntimeException("Unauthorized access");
        }
        contact.setName(contactDetails.getName());
        contact.setDescription(contactDetails.getDescription());
        contact.setPhones(contactDetails.getPhones());
        for (Phone phone : contact.getPhones()) {
            phone.setContact(contact);
        }
        return contactRepository.save(contact);
    }

    public void deleteContact(String username, Integer contactId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new RuntimeException("Contact not found"));
        if (!contact.getOwnerId().equals(username.hashCode())) {
            throw new RuntimeException("Unauthorized access");
        }
        contactRepository.delete(contact);
    }
}
