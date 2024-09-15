package tech.getarrays.schedulermanager.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Task implements Serializable{ //help transform this class to string
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ID;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    private int frequency;

    public Task(){}
    public Task(String iD, LocalDateTime startTime, LocalDateTime endTime, int frequency) {
        this.ID = iD;
        this.startTime = startTime;
        this.endTime = endTime;
        this.frequency = frequency;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getfrequency() {
        return frequency;
    }

    public void setfrequency(int freq) {
        frequency = freq;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID='" + ID + '\'' +
                ", StartTime=" + startTime +
                ", EndTime=" +endTime +
                ", frequency=" + frequency +
                '}';
    }


}



