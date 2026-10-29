package com.example.demo.controller;

import com.example.demo.entity.Stock;
import com.example.demo.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    private final StockRepository stockRepository;

    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        logger.info("Getting all stocks");
        return stockRepository.findAll();
    }

    @GetMapping("/{symbol}")
    public Stock getStockBySymbol(@PathVariable String symbol) {
        logger.info("Getting stock by symbol: " + symbol);
        return stockRepository.findById(symbol).orElse(null);
    }

    @PostMapping
    public Stock createStock(@RequestBody Stock stock) {
        logger.info("Adding a stock");
        return stockRepository.save(stock);
    }

    @PutMapping("/{symbol}")
    public Stock updateStock(@PathVariable String symbol, @RequestBody Stock updatedStock) {
        logger.info("Updating a stock");
        Stock existingStock = stockRepository.findById(symbol).orElse(null);
        if (existingStock != null) {
            existingStock.setCompanyName(updatedStock.getCompanyName());
            existingStock.setExchange(updatedStock.getExchange());
            return stockRepository.save(existingStock);
        }
        return null;
    }

    @PatchMapping("/{symbol}")
    public Stock patchStock(@PathVariable String symbol, @RequestBody Stock partialStock) {
        logger.info("Patching a stock");
        Stock existingStock = stockRepository.findById(symbol).orElse(null);
        if (existingStock != null) {
            if (partialStock.getCompanyName() != null) {
                existingStock.setCompanyName(partialStock.getCompanyName());
            }
            if (partialStock.getExchange() != null) {
                existingStock.setExchange(partialStock.getExchange());
            }
            return stockRepository.save(existingStock);
        }
        return null;
    }

    @DeleteMapping("/{symbol}")
    public String deleteStock(@PathVariable String symbol) {
        logger.info("Deleting a stock");
        stockRepository.deleteById(symbol);
        return "Stock deleted successfully with symbol: " + symbol;
    }
}
