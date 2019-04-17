package mongo.entity;

import java.util.Date;

public class Data {
    private String id;
    private String text;
    private Integer number;
    private Date date;

    public Data() {
    }

    public Data(String id, String text, Integer number, Date date) {
        this.id=id;
        this.text = text;
        this.number = number;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
