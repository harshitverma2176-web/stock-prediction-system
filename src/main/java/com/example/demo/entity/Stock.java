package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @Column(name = "symbol")
    private String symbol;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "exchange", nullable = false)
    private String exchange;

    // One-to-Many: One stock has many portfolio entries
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Portfolio> portfolios = new ArrayList<>();

    // One-to-Many: One stock has many watchlist entries
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Watchlist> watchlists = new ArrayList<>();

    // One-to-Many: One stock has many historical price records
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HistoricalPrice> historicalPrices = new ArrayList<>();

    // One-to-Many: One stock has many predictions
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Prediction> predictions = new ArrayList<>();

    public Stock() {
    }

    public Stock(String symbol, String companyName, String exchange) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public List<Watchlist> getWatchlists() {
        return watchlists;
    }

    public void setWatchlists(List<Watchlist> watchlists) {
        this.watchlists = watchlists;
    }

    public List<HistoricalPrice> getHistoricalPrices() {
        return historicalPrices;
    }

    public void setHistoricalPrices(List<HistoricalPrice> historicalPrices) {
        this.historicalPrices = historicalPrices;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }
}
