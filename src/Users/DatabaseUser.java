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

    DatabaseUser(String pathDatabase){
        this.pathDatabase = pathDatabase;
        this.usersList = new ArrayList<>();
    }

    private void setUser() throws Exception {
        try {
            boolean isPath = this.verifyExistFile(this.path);
            if(!isPath) throw new Exception("Arquivo de banco de usuários não encontrado.");

            StringBuilder usersDataString = new StringBuilder();

            for(User user : this.usersList){
                String userData = user.getId() + ";" + user.getUsername() + ";" + user.getPassword() + "\n";
                usersDataString.append(userData);
            }

            Files.writeString(this.path, usersDataString);
        } catch(Exception err){
            System.err.println("Erro ao salvar os dados do usuário no banco de dados: " + err.getMessage());
            throw new Exception(err);
        }
    }

    public void createUser(String username, String password) throws Exception{
        try{
            if(username.isBlank() || password.isBlank()){
                throw new Exception("Nome de usuário ou senha inválidos.");
            }

            getAllUsers();
            int newId = DatabaseUser.getIdLastUser(this.usersList) + 1;

            this.usersList.add(new User());
            this.usersList.getLast().setId(newId);
            this.usersList.getLast().setUsername(username);
            this.usersList.getLast().setPassword(password);

            setUser();
        } catch(Exception err){
            System.out.println("Erro ao criar o seu usuário: " + err.getMessage());
            throw new Exception(err);
        }

    }

    private boolean verifyExistFile(Path pathFile) throws Exception{
        try {
            if(Files.notExists(pathFile)){
                Files.createDirectories(pathFile.getParent());
            }

            return true;
        } catch(Exception err){
            System.err.println("Erro ao verificar a existência ou na criação do arquivo: " + err.getMessage());
            throw new Exception(err);
        }
    }

    void getAllUsers() throws Exception{
        try {
            this.path = Paths.get(this.pathDatabase);

            boolean isPath = this.verifyExistFile(this.path);
            if(!isPath) throw new Exception("Arquivo de banco de usuários não encontrado.");

            String usersDocument = Files.readString(this.path);
            String[] users = usersDocument.split("\n");

            // cria uma lista adicionando todas as linhas de dados.
            ArrayList<String> usersData = new ArrayList<>(Arrays.asList(users));

            for(int i = 0; i < usersData.size(); i++){
                String[] user = usersData.get(i).split(";");

                this.usersList.add(new User());

                // setando valores do usuário.
                this.usersList.get(i).setId(Integer.parseInt(user[0]));
                this.usersList.get(i).setUsername(user[1]);
                this.usersList.get(i).setPassword(user[2]);
            }
        } catch(Exception err){
            System.err.println("Erro ao acessar o banco de dados de usuários: " + err.getMessage());
            throw new Exception(err);
        }
    }

    static int getIdLastUser(ArrayList<User> listUsers){
        return listUsers.getLast().getId();
    }

    static boolean verifyData(ArrayList<User> listUsers, String username, String password){
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
