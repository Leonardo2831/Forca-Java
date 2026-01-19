package Users;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseUser {
    final String pathDatabase;
    Path path;
    ArrayList<User> usersList;

    public DatabaseUser(String pathDatabase) throws Exception {
        this.pathDatabase = pathDatabase;
        this.usersList = getAllUsers();
    }

    private void setUser() throws Exception {
        try {
            boolean isPath = this.verifyExistFile(this.path);
            if(!isPath) throw new Exception("Arquivo de banco de usuários não encontrado.");

            StringBuilder usersDataString = new StringBuilder();

            for(User user : this.usersList) {
                String userInfo = user.getId() + ";" + user.getUsername() + ";" + user.getPassword() + "\n";
                usersDataString.append(userInfo);
            }

            Files.writeString(this.path, usersDataString.toString());
        } catch(Exception err){
            System.err.println("Erro ao salvar os dados do usuário no banco de dados: " + err.getMessage());
            throw new Exception(err);
        }
    }

    public ArrayList<User> getAllUsers() throws Exception{
        try {
            this.path = Paths.get(this.pathDatabase);

            boolean isPath = this.verifyExistFile(this.path);
            if(!isPath){
                throw new Exception("Arquivo de banco de usuários não encontrado.");
            }

            String usersDocument = Files.readString(this.path);
            if(usersDocument.isBlank()){
                return new ArrayList<>();
            }

            String[] users = usersDocument.split("\n");

            // cria uma lista adicionando todas as linhas de dados.
            ArrayList<String> usersData = new ArrayList<>(Arrays.asList(users));

            ArrayList<User> usersList = new ArrayList<>();
            for (String userInfo : usersData) {
                String[] userValues = userInfo.split(";");

                // setando valores do usuário.
                User newUser = new User();
                newUser.setId(Integer.parseInt(userValues[0]));
                newUser.setUsername(userValues[1]);
                newUser.setPassword(userValues[2]);

                usersList.add(newUser);
            }

            return usersList;
        } catch(Exception err){
            System.err.println("Erro ao acessar o banco de dados de usuários: " + err.getMessage());
            throw new Exception(err);
        }
    }

    public User createUser(String username, String password) throws Exception{
        try{
            if(username.isBlank() || password.isBlank()){
                throw new Exception("Nome de usuário ou senha inválidos.");
            }

            this.usersList = this.getAllUsers();
            int newId = DatabaseUser.getIdLastUser(this.usersList) + 1;

            User newUser = new User();
            newUser.setId(newId);
            newUser.setUsername(username);
            newUser.setPassword(password);

            this.usersList.add(newUser);
            this.setUser();

            return newUser;
        } catch(Exception err){
            System.out.println("Erro ao criar o seu usuário");
            throw new Exception(err);
        }
    }

    private boolean verifyExistFile(Path pathFile) throws Exception{
        try {
            if(Files.notExists(pathFile)){
                Files.createDirectories(pathFile.getParent());
                Files.createFile(pathFile);
            }

            return true;
        } catch(Exception err){
            System.err.println("Erro ao verificar a existência ou na criação do arquivo: " + err.getMessage());
            throw new Exception(err);
        }
    }

    public static User getUser(ArrayList<User> users,String username, String password){
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }

        return null;
    }

    static int getIdLastUser(ArrayList<User> listUsers){
        if(listUsers.isEmpty()) return 0;
        return listUsers.getLast().getId();
    }

    public static boolean checkUserExists(ArrayList<User> listUsers, String username){
        for(User user : listUsers){
            if(user.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }

        return false;
    }

    public static boolean verifyData(ArrayList<User> listUsers, String username, String password){
        if(listUsers.isEmpty() || username.isBlank() || password.isBlank()) return false;

        for(User user : listUsers){
            int id = user.getId();
            String usernameData = user.getUsername();
            String passwordData = user.getPassword();

            if(usernameData.isBlank() || passwordData.isBlank() || id < 1){
                System.err.println("Erro ao pegar os dados do usuário.");
                return false;
            }

            if(username.equals(usernameData) && password.equals(passwordData)){
                System.out.println(username + ", você está logado! Bem-vindo de volta!");
                return true;
            }
        }

        return false;
    }
}
