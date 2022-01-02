package operations;

public class Users {

    String username,password,loginStatus;
    Users(String username,String password,String loginStatus){
        this.username = username;
        this.password = password;
        this.loginStatus = loginStatus;
    }
    public boolean verifyLogin(String username,String password){
        return true;
    }

    public void logout(){

    }

    public void updateProfile(){

    }
}
