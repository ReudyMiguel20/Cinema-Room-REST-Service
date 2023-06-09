package cinema.TicketManagement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Token {

    private String token;

    public Token() {
        UUID uuid = UUID.randomUUID();
        this.token = String.valueOf(uuid);
    }

    public Token(String uuid) {
        this.token = uuid;
    }
//    @JsonProperty("token")
//    public String getUuid() {
//        return String.valueOf(token);
//    }


    public String getToken() {
        return token;
    }

    public void setToken(String uuid) {
        this.token = uuid;
    }

}
