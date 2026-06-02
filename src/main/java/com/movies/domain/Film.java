package com.movies.domain;

public class Film {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String director;
    private String genre;

    public Film() {}

    public Film(String title, Integer releaseYear, String director, String genre) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.director = director;
        this.genre = genre;
    }

    public Film(Long id, String title, Integer releaseYear, String director, String genre) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.director = director;
        this.genre = genre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String toString() {
        return "Film{id=" + id + ", title='" + title + "', releaseYear=" + releaseYear
                + ", director='" + director + "', genre='" + genre + "'}";
    }
}
