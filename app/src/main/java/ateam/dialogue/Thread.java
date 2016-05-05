package ateam.dialogue;

public class Thread {
    private String title, author, datePosted;

    public Thread() {
    }

    public Thread(String title, String author, String datePosted) {
        this.title = title;
        this.author = author;
        this.datePosted = datePosted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return datePosted;
    }

    public void setDate(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String genre) {
        this.author = genre;
    }
}