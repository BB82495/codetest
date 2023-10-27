package com.real.matcher;

import java.util.Date;

public class Xbox {
    String mediaId;
    String title;
    Date originalReleaseDate;
    String mediaType;
    String actors;
    String director;
    String xboxLiveURL;

    public Xbox(String mediaId, String title, Date originalReleaseDate, String mediaType, String actors, String director, String xboxLiveURL) {
        this.mediaId = mediaId;
        this.title = title;
        this.originalReleaseDate = originalReleaseDate;
        this.mediaType = mediaType;
        this.actors = actors;
        this.director = director;
        this.xboxLiveURL = xboxLiveURL;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginalReleaseDate(Date originalReleaseDate) {
        this.originalReleaseDate = originalReleaseDate;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setXboxLiveURL(String xboxLiveURL) {
        this.xboxLiveURL = xboxLiveURL;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getTitle() {
        return title;
    }

    public Date getOriginalReleaseDate() {
        return originalReleaseDate;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getActors() {
        return actors;
    }

    public String getDirector() {
        return director;
    }

    public String getXboxLiveURL() {
        return xboxLiveURL;
    }
}
