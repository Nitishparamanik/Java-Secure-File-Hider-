package dao;

import db.MyConnection;
import model.Data;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DataDAO {
public static List<Data> getAllFiles(String email) throws SQLException {
    Connection connection = MyConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement("select * from where email = ?");
    ps.setString(1,email);
    ResultSet rs = ps.executeQuery();
    List<Data> files = new ArrayList<>();
    while (rs.next()){
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String filepath = rs.getString(3);
        files.add(new Data(id,name,filepath));
    }
    return files;
}
public static int hideFile(Data file) throws SQLException, IOException {
    Connection connection = MyConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(
            "insert into data(name,filepath,email,bin_data) values(?,?,?,?)");
    ps.setString(1,file.getFileName());
    ps.setString(2,file.getFilepath());
    ps.setString(3,file.getEmail());
    File f  = new File(file.getFilepath());
    FileReader fr = new FileReader(f);
    ps.setCharacterStream(4,fr,f.length());
   int ans = ps.executeUpdate();
   fr.close();
   f.delete();
   return ans;
}
public static void unhide(int id) throws SQLException, IOException{
    Connection connection = MyConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement("select filepath, bin_data from data where id = ?");
    ps.setInt(1,id);
    ResultSet rs = ps.executeQuery();
    rs.next();
    String filepath = rs.getString("filepath");
    Clob c = rs.getClob("bin_data");
    Reader r = c.getCharacterStream();
    FileWriter fw = new FileWriter(filepath);
    int i;
    while((i = r.read())!=-1){
        fw.write((char)i);
    }
    fw.close();
    ps = connection.prepareStatement("delete from data where id= ?");
    ps.setInt(1,id);
    ps.executeUpdate();
    System.out.println("Successfully Unhidden");
}
}
