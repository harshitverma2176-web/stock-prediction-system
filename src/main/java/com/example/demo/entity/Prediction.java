package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One: Many predictions belong to one Stock
    @ManyToOne
    @JoinColumn(name = "symbol", nullable = false)
    private Stock stock;

    @Column(name = "predicted_price", nullable = false)
    private Double predictedPrice;

    @Column(name = "recommendation", nullable = false)
    private String recommendation;

    @Column(name = "confidence", nullable = false)
    private Double confidence;

    @Column(name = "prediction_date", nullable = false)
    private LocalDate predictionDate;

    public Prediction() {
    }

    public Prediction(Stock stock, Double predictedPrice, String recommendation, Double confidence, LocalDate predictionDate) {
        this.stock = stock;
        this.predictedPrice = predictedPrice;
        this.recommendation = recommendation;
        this.confidence = confidence;
        this.predictionDate = predictionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Double getPredictedPrice() {
        return predictedPrice;
    }

    public void setPredictedPrice(Double predictedPrice) {
        this.predictedPrice = predictedPrice;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public LocalDate getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(LocalDate predictionDate) {
        this.predictionDate = predictionDate;
    }
}
