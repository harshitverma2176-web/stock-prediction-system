package com.example.demo.repository;

import com.example.demo.entity.HistoricalPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalPriceRepository extends JpaRepository<HistoricalPrice, Long> {
}
