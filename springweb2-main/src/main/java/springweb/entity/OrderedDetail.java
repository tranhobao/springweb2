package springweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="ordered_detail")
public class OrderedDetail {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ordered_id", nullable = false)
    private Ordered ordered;

    @Column(name = "vegetable_id")
    private Integer vegetableId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;
}
