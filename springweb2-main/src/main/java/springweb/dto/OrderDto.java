package springweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("customer_id")
    private Integer customerId;

    @JsonProperty("date_created")
    private String dateCreated;

    @JsonProperty("total")
    private Double total;

    @JsonProperty("note")
    private String note;

    @JsonProperty("order_detail")
    private List<OrderDetailDto> orderDetailDtoList;
}
