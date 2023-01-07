public class User {
    int id;
    String name;
    String password;

    public void user(String name, String password){
        this.name = name;
        this.password = password;
    }

    public void download(){

    }
    public void settings(){

    }
    public void notification(){

    }
    public void next(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
