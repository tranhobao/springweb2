package springweb.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder {
    @JsonProperty("customer_id")
    private Integer id;

    @JsonProperty("note")
    private String note;

    @JsonProperty("lst_product_order")
    private List<ProductOrder> orderList;

    public boolean validate() {
        if (id == null) {
            return false;
        }
        if (orderList == null || orderList.isEmpty()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
