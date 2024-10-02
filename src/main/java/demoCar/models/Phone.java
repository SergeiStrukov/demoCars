package demoCar.models;

import demoCar.models.Contact;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String telephone;
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;
}
