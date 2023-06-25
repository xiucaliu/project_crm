package model;

public class Status {
    private int id;
    private String name;
    private float taskStatusPercent;
    private int countTaskStatus;

    public int getCountTaskStatus() {
        return countTaskStatus;
    }

    public void setCountTaskStatus(int countTaskStatus) {
        this.countTaskStatus = countTaskStatus;
    }

    public float getTaskStatusPercent() {
        return taskStatusPercent;
    }

    public void setTaskStatusPercent(float taskStatusPercent) {
        this.taskStatusPercent = taskStatusPercent;
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
}
