package springweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VegetableDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("image")
    private String image;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("catagoryId")
    private Integer catagoryId;

    public String toString() {
        return new Gson().toJson(this);
    }
}
