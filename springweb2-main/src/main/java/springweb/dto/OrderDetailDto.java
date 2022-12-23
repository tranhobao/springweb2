package springweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("vegetable_id")
    private Integer vegetableId;

    @JsonProperty("vegetable_name")
    private String vegetableName;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("price")
    private Double price;
}
