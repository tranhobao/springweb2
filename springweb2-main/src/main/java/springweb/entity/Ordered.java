package springweb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="ordered")
public class Ordered {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "date_created")
    private String dateCreated;

    @Column(name = "total")
    private Double total;

    @Column(name = "note")
    private String note;

    @JsonManagedReference
    @OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL)
    private Set<OrderedDetail> orderedDetails;
}
