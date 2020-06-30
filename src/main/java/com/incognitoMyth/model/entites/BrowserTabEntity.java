package com.incognitoMyth.model.entites;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BROWSER_TAB")
public class BrowserTabEntity {

    @Id
    @NotNull
    @Column(name = "FINGER_PRINT")
    private String fingerprint;

    @Column(name = "NAME")
    private String name;

    public BrowserTabEntity() {
    }

    public BrowserTabEntity(@NotNull String fingerprint, String name) {
        this.fingerprint = fingerprint;
        this.name = name;
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
