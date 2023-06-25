package model;

import java.util.Date;

public class Jobs {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private int leaderId;
    public int getLeaderId() {
        return leaderId;
    }
    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
