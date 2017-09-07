package com.example.sonnv.letsread;

/**
 * Created by SONNV on 9/6/2017.
 */

public class StoryModel {
    private int id;
    private String title;
    private String image;
    private String author;
    private String description;
    private String content;
    private boolean bookmark;

    public StoryModel(int id, String image, String title, String description, String content, String author, boolean bookmark) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.author = author;
        this.description = description;
        this.content = content;
        this.bookmark = bookmark;
    }

    @Override
    public String toString() {
        return "StoryModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", bookmark=" + bookmark +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }
}
