package springweb.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("password")
    private String password;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
