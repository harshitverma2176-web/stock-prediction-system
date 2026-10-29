package com.example.demo.repository;

import com.example.demo.entity.Watchlist;
import com.example.demo.entity.WatchlistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, WatchlistId> {
}
