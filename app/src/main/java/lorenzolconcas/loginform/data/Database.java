package lorenzolconcas.loginform.data;

import java.util.ArrayList;

public class Database {
    private class User{
        private String username, password;

        public User(String username, String password){
            this.password = password;
            this.username = username;
        }
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
        public boolean Login(String username, String password){
            return this.username.equals(username) && this.password.equals(password);
        }
    }
    private  ArrayList<User> Users = new ArrayList<>();

    private static Database persist;

    public Database(){
        Users.clear();
        Users.add(new User("test", "test"));

    }
    public static Database getPersist(){
        if(persist == null)
            persist = new Database();
        return persist;
    }

    public boolean Login(String username, String password){
        if(persist == null)
            return false;

      for(User s : Users){
          if(s.Login(username, password))
              return true;
        }

        return false;
    }

    public boolean insertUser(String username, String password) {
        if (persist == null)
            persist = new Database();

        //controllo se l'utente esiste
        for (User s : Users) {
            if (s.getUsername().equals(username))
                return false;
        }

        return Users.add(new User(username, password));
    }
}
