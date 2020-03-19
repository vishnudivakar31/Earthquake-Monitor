package edu.njit.earthquakemonitor.model;

public class EarthQuake {
    private String place;
    private Double magnitude;
    private Long timestamp;
    private String detailLink;
    private String type;
    private Double lat;
    private Double lon;

    public EarthQuake() {
    }

    public EarthQuake(String place, Double magnitude, Long timestamp, String detailLink, String type, Double lat, Double lon) {
        this.place = place;
        this.magnitude = magnitude;
        this.timestamp = timestamp;
        this.detailLink = detailLink;
        this.type = type;
        this.lat = lat;
        this.lon = lon;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
