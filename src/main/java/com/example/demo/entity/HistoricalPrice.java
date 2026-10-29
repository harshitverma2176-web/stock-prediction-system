package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historical_prices")
public class HistoricalPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One: Many historical price records belong to one Stock
    @ManyToOne
    @JoinColumn(name = "symbol", nullable = false)
    private Stock stock;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "open", nullable = false)
    private Double open;

    @Column(name = "high", nullable = false)
    private Double high;

    @Column(name = "low", nullable = false)
    private Double low;

    @Column(name = "close", nullable = false)
    private Double close;

    @Column(name = "volume", nullable = false)
    private Long volume;

    public HistoricalPrice() {
    }

    public HistoricalPrice(Stock stock, LocalDate date, Double open, Double high, Double low, Double close, Long volume) {
        this.stock = stock;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
}
