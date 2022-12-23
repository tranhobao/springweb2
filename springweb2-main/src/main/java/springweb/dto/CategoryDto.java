package springweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
}
