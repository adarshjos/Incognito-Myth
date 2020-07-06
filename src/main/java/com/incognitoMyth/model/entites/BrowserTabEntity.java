package com.incognitoMyth.model.entites;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "BROWSER_TAB")
public class BrowserTabEntity implements Serializable {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "FINGER_PRINT")
    private String fingerprint;

    @Column(name = "NAME")
    private String name;

    @Column(name = "Search_Time",updatable = false)
    @CreationTimestamp
    private LocalDateTime searchedTime;

    public BrowserTabEntity() {}

    public BrowserTabEntity(String fingerprint, String name) {
        this.fingerprint=fingerprint;
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSearchedTime() {
        return searchedTime;
    }

    public void setSearchedTime(LocalDateTime searchedTime) {
        this.searchedTime = searchedTime;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
