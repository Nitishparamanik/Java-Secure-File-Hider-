package model;

public class Data {
    private int id;
    private String fileName;
    private String filepath;
    private String email;

    public Data(int id, String fileName, String filepath, String email) {
        this.id = id;
        this.fileName = fileName;
        this.filepath = filepath;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Data(int id, String filepath, String fileName) {
        this.id = id;
        this.filepath = filepath;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
