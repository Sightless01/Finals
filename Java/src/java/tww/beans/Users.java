package tww.beans;

public class Users {
    private final String username;
    private final int client_id;

    public Users(String username, int client_id) {
        this.username = username;
        this.client_id = client_id;
    }

    public String getUsername() {
        return username;
    }

    public int getClient_id() {
        return client_id;
    }

}
