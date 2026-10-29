package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "watchlist")
@IdClass(WatchlistId.class)
public class Watchlist {

    // Part of Composite Primary Key & ManyToOne relationship to User
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Part of Composite Primary Key & ManyToOne relationship to Stock
    @Id
    @ManyToOne
    @JoinColumn(name = "symbol", nullable = false)
    private Stock stock;

    public Watchlist() {
    }

    public Watchlist(User user, Stock stock) {
        this.user = user;
        this.stock = stock;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
