package com.movies.domain;

public class Character {
    private Long id;
    private String characterName;
    private String actorName;
    private String status;
    private Long filmId;
    private Film film;

    public Character() {}

    public Character(String characterName, String actorName, String status, Long filmId, Film film) {
        this.characterName = characterName;
        this.actorName = actorName;
        this.status = status;
        this.filmId = filmId;
        this.film = film;
    }

    public Character(Long id, String characterName, String actorName, String status, Long filmId, Film film) {
        this.id = id;
        this.characterName = characterName;
        this.actorName = actorName;
        this.status = status;
        this.filmId = filmId;
        this.film = film;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCharacterName() { return characterName; }
    public void setCharacterName(String characterName) { this.characterName = characterName; }

    public String getActorName() { return actorName; }
    public void setActorName(String actorName) { this.actorName = actorName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getFilmId() { return filmId; }
    public void setFilmId(Long filmId) { this.filmId = filmId; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public String getFilmTitle() {
        return film != null ? film.getTitle() : null;
    }

    @Override
    public String toString() {
        return "Character{id=" + id + ", characterName='" + characterName + "', actorName='" + actorName
                + "', status='" + status + "', filmId=" + filmId + '}';
    }
}
