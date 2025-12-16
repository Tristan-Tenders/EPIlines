package com.timeoutairline.epiline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "discount_codes")
public class DiscountCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @Column(unique = true, nullable = false, length = 10)
    private String code;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @Column(nullable = false)
    private Boolean isUsed = false;

    @Column
    private LocalDateTime usedAt;

    @Column(nullable = false)
    private Float discountPercentage = 10.0f; // Default 10% discount

    // Constructors
    public DiscountCode() {
    }

    public DiscountCode(Long discountId, Client client, String code, Integer year,
                        LocalDateTime generatedAt, Boolean isUsed, LocalDateTime usedAt,
                        Float discountPercentage) {
        this.discountId = discountId;
        this.client = client;
        this.code = code;
        this.year = year;
        this.generatedAt = generatedAt;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
        this.discountPercentage = discountPercentage;
    }

    // Getters
    public Long getDiscountId() {
        return discountId;
    }

    public Client getClient() {
        return client;
    }

    public String getCode() {
        return code;
    }

    public Integer getYear() {
        return year;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }

    public Float getDiscountPercentage() {
        return discountPercentage;
    }

    @JsonProperty("clientId")
    public Long getClientId() {
        return client != null ? client.getClientId() : null;
    }

    // Setters
    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void setUsedAt(LocalDateTime usedAt) {
        this.usedAt = usedAt;
    }

    public void setDiscountPercentage(Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @JsonProperty("clientId")
    public void setClientId(Long clientId) {
        if (clientId != null) {
            if (this.client == null) {
                this.client = new Client();
            }
            this.client.setClientId(clientId);
        }
    }

    @Override
    public String toString() {
        return "DiscountCode{" +
                "discountId=" + discountId +
                ", clientId=" + (client != null ? client.getClientId() : "null") +
                ", code='" + code + '\'' +
                ", year=" + year +
                ", generatedAt=" + generatedAt +
                ", isUsed=" + isUsed +
                ", usedAt=" + usedAt +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}