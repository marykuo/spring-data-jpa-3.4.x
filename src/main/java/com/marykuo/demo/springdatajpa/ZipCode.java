package com.marykuo.demo.springdatajpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ZipCode {
    @Column(name = "zip_code", nullable = false, length = 10)
    private String value;

    protected ZipCode() {
    }

    public ZipCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

