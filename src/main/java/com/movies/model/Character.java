package com.movies.model;

public class Character {
    private int id;
    private String characterName;
    private String actorName;
    private String status;
    private int filmId;
    private String filmTitle;

    public Character() {}

    public Character(int id, String characterName, String actorName, String status, int filmId) {
        this.id = id;
        this.characterName = characterName;
        this.actorName = actorName;
        this.status = status;
        this.filmId = filmId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCharacterName() { return characterName; }
    public void setCharacterName(String characterName) { this.characterName = characterName; }

    public String getActorName() { return actorName; }
    public void setActorName(String actorName) { this.actorName = actorName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getFilmId() { return filmId; }
    public void setFilmId(int filmId) { this.filmId = filmId; }

    public String getFilmTitle() { return filmTitle; }
    public void setFilmTitle(String filmTitle) { this.filmTitle = filmTitle; }
}
