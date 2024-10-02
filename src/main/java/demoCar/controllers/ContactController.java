package demoCar.controllers;

import demoCar.models.Contact;
import demoCar.models.Phone;
import demoCar.security.JwtTokenUtil;
import demoCar.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/create")
    public ResponseEntity<Contact> createContact(@RequestHeader("Authorization") String token, @RequestBody Contact contact) {
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        for (Phone phone : contact.getPhones()) {
            phone.setContact(contact);
        }
        Contact createdContact = contactService.createContact(username, contact);
        return ResponseEntity.status(201).body(createdContact);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getContacts(@RequestHeader("Authorization") String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        List<Contact> contacts = contactService.getContactsByUsername(username);
        return ResponseEntity.ok(contacts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@RequestHeader("Authorization") String token, @PathVariable Integer id, @RequestBody Contact contactDetails) {
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        for (Phone phone : contactDetails.getPhones()) {
            phone.setContact(contactDetails);
        }
        Contact updatedContact = contactService.updateContact(username, id, contactDetails);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        contactService.deleteContact(username, id);
        return ResponseEntity.noContent().build();
    }
}
