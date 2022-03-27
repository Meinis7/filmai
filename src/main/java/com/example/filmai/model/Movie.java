package com.example.filmai.model;

public class Movie {
    private int id;
    private String title;
    private int length;
    private String actors;
    private String director;
    private int year;
    private int user_id;

    public Movie(){
    }

    public Movie(int id, String title, int length, String actors, String director, int year, int user_id) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.actors = actors;
        this.director = director;
        this.year = year;
        this.user_id = user_id;
    }

    public Movie(int id, String title, int length, String actors, String director, int year) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.actors = actors;
        this.director = director;
        this.year = year;
    }

    public Movie(String title, int length, String actors, String director, int year, int user_id) {
        this.title = title;
        this.length = length;
        this.actors = actors;
        this.director = director;
        this.year = year;
        this.user_id=user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", actors='" + actors + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", user_id=" + user_id +
                '}';
    }
}
