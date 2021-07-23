package com.shetty.moviesdashboard.data;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class Movies {

    @Id
    private long movieid;
    private String title;
    private String mpaa_rating;
    private long budget;
    private long gross;
    private LocalDate release_date;
    private String genre;
    private String runtime;
    private String rating;
    private String rating_count;

    @Lob
    @Column(name="summary", length = 1000)
    private String summary;

    public long getMovieid() {
        return movieid;
    }

    public void setMovieid(long movieid) {
        this.movieid = movieid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMpaa_rating() {
        return mpaa_rating;
    }

    public void setMpaa_rating(String mpaa_rating) {
        this.mpaa_rating = mpaa_rating;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getGross() {
        return gross;
    }

    public void setGross(long gross) {
        this.gross = gross;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating_count() {
        return rating_count;
    }

    public void setRating_count(String rating_count) {
        this.rating_count = rating_count;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Movies(long movieid, String title, String mpaa_rating, long budget, long gross, LocalDate release_date,
            String genre, String runtime, String rating, String rating_count, String summary) {
        this.movieid = movieid;
        this.title = title;
        this.mpaa_rating = mpaa_rating;
        this.budget = budget;
        this.gross = gross;
        this.release_date = release_date;
        this.genre = genre;
        this.runtime = runtime;
        this.rating = rating;
        this.rating_count = rating_count;
        this.summary = summary;
    }

    public Movies(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    

    public Movies() {
    }

    @Override
    public String toString() {
        return "Title: " + this.title + ", Genre: " + this.genre;
    }

}
