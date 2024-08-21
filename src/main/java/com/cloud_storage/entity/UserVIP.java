package com.cloud_storage.entity;

import jakarta.persistence.*;


@Entity
public class UserVIP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private Long yob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getYob() {
        return yob;
    }

    public void setYob(Long yob) {
        this.yob = yob;
    }
}
