package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One: Many portfolio entries belong to one User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many-to-One: Many portfolio entries refer to one Stock
    @ManyToOne
    @JoinColumn(name = "symbol", nullable = false)
    private Stock stock;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;

    public Portfolio() {
    }

    public Portfolio(User user, Stock stock, Integer quantity, Double purchasePrice) {
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
