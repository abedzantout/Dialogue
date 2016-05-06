package ateam.dialogue;

public class Thread {

    private int ID;
    private String title, poster;

    public Thread() {
    }

    public Thread(int ID, String title, String poster) {
        this.ID = ID;
        this.title = title;
        this.poster = poster;
    }

    public int getID(){
        return ID;
    }

    public void setID(int id){
        this.ID = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
