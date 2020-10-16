package com.example.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class WorkingTime {

    // member variablese
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonDeserialize(as = LocalDate.class)
    private LocalDate date;
    @JsonDeserialize(as = LocalTime.class)
    private LocalTime startTime;
    @JsonDeserialize(as = LocalTime.class)
    private LocalTime endTime;

    @ManyToOne
    private Worker worker;

    // Constructors
    public WorkingTime() {

    }

    public WorkingTime(Long id) {
        this.id = id;
    }

    // Getter and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
