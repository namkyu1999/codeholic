package codeholic.domain.request;

import lombok.Data;

@Data
public class RequestLoginUser {
    private String username;
    private String password;

    public RequestLoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}