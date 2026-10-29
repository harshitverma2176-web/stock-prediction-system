package com.example.demo.controller;

import com.example.demo.entity.HistoricalPrice;
import com.example.demo.repository.HistoricalPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historical-prices")
public class HistoricalPriceController {

    private static final Logger logger = LoggerFactory.getLogger(HistoricalPriceController.class);

    private final HistoricalPriceRepository historicalPriceRepository;

    public HistoricalPriceController(HistoricalPriceRepository historicalPriceRepository) {
        this.historicalPriceRepository = historicalPriceRepository;
    }

    @GetMapping
    public List<HistoricalPrice> getAllHistoricalPrices() {
        logger.info("Getting all historical prices");
        return historicalPriceRepository.findAll();
    }

    @GetMapping("/{id}")
    public HistoricalPrice getHistoricalPriceById(@PathVariable Long id) {
        logger.info("Getting historical price by id: " + id);
        return historicalPriceRepository.findById(id).orElse(null);
    }

    @PostMapping
    public HistoricalPrice createHistoricalPrice(@RequestBody HistoricalPrice historicalPrice) {
        logger.info("Adding historical price");
        return historicalPriceRepository.save(historicalPrice);
    }

    @PutMapping("/{id}")
    public HistoricalPrice updateHistoricalPrice(@PathVariable Long id, @RequestBody HistoricalPrice updatedPrice) {
        logger.info("Updating historical price");
        HistoricalPrice existingPrice = historicalPriceRepository.findById(id).orElse(null);
        if (existingPrice != null) {
            if (updatedPrice.getStock() != null) {
                existingPrice.setStock(updatedPrice.getStock());
            }
            existingPrice.setDate(updatedPrice.getDate());
            existingPrice.setOpen(updatedPrice.getOpen());
            existingPrice.setHigh(updatedPrice.getHigh());
            existingPrice.setLow(updatedPrice.getLow());
            existingPrice.setClose(updatedPrice.getClose());
            existingPrice.setVolume(updatedPrice.getVolume());
            return historicalPriceRepository.save(existingPrice);
        }
        return null;
    }

    @PatchMapping("/{id}")
    public HistoricalPrice patchHistoricalPrice(@PathVariable Long id, @RequestBody HistoricalPrice partialPrice) {
        logger.info("Patching historical price");
        HistoricalPrice existingPrice = historicalPriceRepository.findById(id).orElse(null);
        if (existingPrice != null) {
            if (partialPrice.getStock() != null) {
                existingPrice.setStock(partialPrice.getStock());
            }
            if (partialPrice.getDate() != null) {
                existingPrice.setDate(partialPrice.getDate());
            }
            if (partialPrice.getOpen() != null) {
                existingPrice.setOpen(partialPrice.getOpen());
            }
            if (partialPrice.getHigh() != null) {
                existingPrice.setHigh(partialPrice.getHigh());
            }
            if (partialPrice.getLow() != null) {
                existingPrice.setLow(partialPrice.getLow());
            }
            if (partialPrice.getClose() != null) {
                existingPrice.setClose(partialPrice.getClose());
            }
            if (partialPrice.getVolume() != null) {
                existingPrice.setVolume(partialPrice.getVolume());
            }
            return historicalPriceRepository.save(existingPrice);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteHistoricalPrice(@PathVariable Long id) {
        logger.info("Deleting historical price");
        historicalPriceRepository.deleteById(id);
        return "Historical price record deleted successfully with id: " + id;
    }
}
