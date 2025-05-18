package com.marykuo.demo.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervieweeRepository extends JpaRepository<Interviewee, Long> {

}
