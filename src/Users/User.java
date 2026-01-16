package Users;

public class User {
    private int id;
    private String username;
    private String password;

    void setId(int id){
        this.id = id;
    }

    void setUsername(String username){
        this.username = username;
    }

    void setPassword(String password){
        this.password = password;
    }

    int getId() {
        return id;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}
