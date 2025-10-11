package com.algohire.backend.enums;

public enum ApplicationStatus {
    APPLIED,         // Candidate submitted application
    SHORTLISTED,    // HR or system is screening
    INTERVIEWING,    // Candidate is being interviewed
    OFFERED,         // Offer made
    ACCEPTED,        // Candidate accepted the offer
    REJECTED,        // Candidate rejected or was rejected
    WITHDRAWN        // Candidate withdrew application
}
