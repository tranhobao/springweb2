package springweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import springweb.requests.Login;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends Login {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;
}
