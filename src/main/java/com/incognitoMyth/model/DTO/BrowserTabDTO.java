package com.incognitoMyth.model.DTO;

import java.util.ArrayList;
import java.util.List;

public class BrowserTabDTO {
    private String fingerprint;
    private List<String> queries;
    private String status;

    public BrowserTabDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }
}