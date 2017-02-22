package com.example.manasaa.remindertask;

/**
 * Created by Sowjanya on 20-02-2017.
 */

public class Reminder {
    int id;
    String nameTask,timeTask;
    public Reminder(){
    }
    public Reminder(String name,String time){
        this.nameTask=name;
        this.timeTask=time;
    }
    public Reminder(int id,String name,String time){
        this.id=id;
        this.nameTask=name;
        this.timeTask=time;
    }
    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(String timeTask) {
        this.timeTask = timeTask;
    }


}
