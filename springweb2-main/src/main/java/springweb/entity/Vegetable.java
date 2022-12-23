package springweb.entity;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vegetable")
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "unit")
    private String unit;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Double price;

    @Column(name = "categoryId")
    private Integer categoryId;
}
