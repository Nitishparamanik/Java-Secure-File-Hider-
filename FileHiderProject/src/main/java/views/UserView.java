package views;

import dao.DataDAO;
import model.Data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;
    UserView(String email){
        this.email = email;
    }
    public void home(){
        do{
            System.out.println("Welcome "+this.email);
            System.out.println("Press 1 : Show Hiiden Files");
            System.out.println("Press 2 : Hide New File");
            System.out.println("Press 3 : UnHide a File");
            System.out.println("Press 0 : To Exit App");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch){
                case 1 -> {
                    try {
                        List<Data> files = DataDAO.getAllFiles(this.email);
                        System.out.println("ID  -  File Name");
                        for(Data file :files){
                            System.out.println(file.getId()+" - "+file.getFileName());
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    System.out.println("Enter the Filepath:");
                    String filepath = sc.nextLine();
                    File f = new File(filepath);
                    Data file = new Data(0,f.getName(), filepath, this.email);
                    try {
                        DataDAO.hideFile(file);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 ->{
                    List<Data> files = null;
                    try {
                        files = DataDAO.getAllFiles(this.email);
                    System.out.println("ID  -  File Name");
                    for(Data file :files){
                        System.out.println(file.getId()+" - "+file.getFileName());
                    }
                    System.out.println("Enter  the id file to unhide:");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean isValidID = false;
                    for(Data file: files){
                        if(file.getId()==id){
                            isValidID = true;
                            break;
                        }
                    }
                    if(isValidID){
                        DataDAO.unhide(id);
                    }
                    else {
                        System.out.println("Wrong ID");
                    }
                    } catch (SQLException  e) {
                        throw new RuntimeException(e);
                    } catch (IOException e ){
                        e.printStackTrace();
                    }
                }
                case 0 ->{
                    System.exit(0);
                }
            }
        } while (true);
    }
}
