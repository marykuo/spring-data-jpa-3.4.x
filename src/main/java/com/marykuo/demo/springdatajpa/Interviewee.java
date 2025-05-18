package com.marykuo.demo.springdatajpa;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Interviewee extends Person {
    private LocalDate interviewDate;

    protected Interviewee() {
        super();
    }

    public Interviewee(String name, LocalDate interviewDate) {
        super(name);
        this.interviewDate = interviewDate;
    }

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }
}