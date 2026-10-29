package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class WatchlistId implements Serializable {

    private Long user;
    private String stock;

    public WatchlistId() {
    }

    public WatchlistId(Long user, String stock) {
        this.user = user;
        this.stock = stock;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchlistId that = (WatchlistId) o;
        return Objects.equals(user, that.user) && Objects.equals(stock, that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, stock);
    }
}
