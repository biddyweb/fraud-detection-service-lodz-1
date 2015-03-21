package com.ofg.fraud

public class ApplicationDecisionDTO {
    String firstName;
    String lastName;
    String job;
    BigDecimal amount;
    String fraudStatus;

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() {
        return lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getJob() {
        return job
    }

    void setJob(String job) {
        this.job = job
    }

    BigDecimal getAmount() {
        return amount
    }

    void setAmount(BigDecimal amount) {
        this.amount = amount
    }

    String getFraudStatus() {
        return fraudStatus
    }

    void setFraudStatus(String fraudStatus) {
        this.fraudStatus = fraudStatus
    }
}
