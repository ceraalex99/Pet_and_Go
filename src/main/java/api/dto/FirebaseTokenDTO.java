package api.dto;

public class FirebaseTokenDTO {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public FirebaseTokenDTO(String token) {
        this.token = token;
    }

    public FirebaseTokenDTO() {
    }
}
