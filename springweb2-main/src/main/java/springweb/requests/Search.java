package springweb.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    @JsonProperty("categoryId")
    private Integer categoryId;

    @JsonProperty("banChay")
    private Boolean banChay;

    @JsonProperty("name")
    private String name;

    public void initInput() {
        if (banChay == null) {
            banChay = false;
        }
    }

    public Integer checkInput() {
        if (banChay == true)
        {
            if (categoryId != null) {
                if (name != null) {
                    return 1;
                }
                return 2;
            }
            else if (name != null) {
                return 3;
            }
            return 4;
        }
        else if (categoryId != null) {
            if (name != null) {
                return 5;
            }
            return 6;
        }
        else if (name != null) {
            return 7;
        }
        return 0;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
