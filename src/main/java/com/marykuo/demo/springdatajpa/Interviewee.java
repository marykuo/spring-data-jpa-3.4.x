package com.marykuo.demo.springdatajpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Interviewee")
public class Interviewee extends Person {
    private LocalDate interviewDate;

    protected Interviewee() {
    }

    public Interviewee(long personId, String name, LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }
}