package com.Steven.movieApplication.model;

/**
 * Created by steven on 6/4/2018.
 * model for the movie ticket.
 */

public  class TicketDetail {
    private String movieName;
    private String amount;
    private String snakes;
    private String cinmema;
    private String time;

    public TicketDetail() {
    }

    public TicketDetail(String movieName, String amount, String snakes, String cinmema, String time) {
        this.movieName = movieName;
        this.amount = amount;
        this.snakes = snakes;
        this.cinmema = cinmema;
        this.time = time;
    }

    public String getMovieName() {

        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSnakes() {
        return snakes;
    }

    public void setSnakes(String snakes) {
        this.snakes = snakes;
    }

    public String getCinmema() {
        return cinmema;
    }

    public void setCinmema(String cinmema) {
        this.cinmema = cinmema;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
