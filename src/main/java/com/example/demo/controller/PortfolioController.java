package com.example.demo.controller;

import com.example.demo.entity.Portfolio;
import com.example.demo.repository.PortfolioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    private final PortfolioRepository portfolioRepository;

    public PortfolioController(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @GetMapping
    public List<Portfolio> getAllPortfolios() {
        logger.info("Getting all portfolios");
        return portfolioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Portfolio getPortfolioById(@PathVariable Long id) {
        logger.info("Getting portfolio by id: " + id);
        return portfolioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
        logger.info("Adding a portfolio");
        return portfolioRepository.save(portfolio);
    }

    @PutMapping("/{id}")
    public Portfolio updatePortfolio(@PathVariable Long id, @RequestBody Portfolio updatedPortfolio) {
        logger.info("Updating a portfolio");
        Portfolio existingPortfolio = portfolioRepository.findById(id).orElse(null);
        if (existingPortfolio != null) {
            if (updatedPortfolio.getUser() != null) {
                existingPortfolio.setUser(updatedPortfolio.getUser());
            }
            if (updatedPortfolio.getStock() != null) {
                existingPortfolio.setStock(updatedPortfolio.getStock());
            }
            existingPortfolio.setQuantity(updatedPortfolio.getQuantity());
            existingPortfolio.setPurchasePrice(updatedPortfolio.getPurchasePrice());
            return portfolioRepository.save(existingPortfolio);
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Portfolio patchPortfolio(@PathVariable Long id, @RequestBody Portfolio partialPortfolio) {
        logger.info("Patching a portfolio");
        Portfolio existingPortfolio = portfolioRepository.findById(id).orElse(null);
        if (existingPortfolio != null) {
            if (partialPortfolio.getUser() != null) {
                existingPortfolio.setUser(partialPortfolio.getUser());
            }
            if (partialPortfolio.getStock() != null) {
                existingPortfolio.setStock(partialPortfolio.getStock());
            }
            if (partialPortfolio.getQuantity() != null) {
                existingPortfolio.setQuantity(partialPortfolio.getQuantity());
            }
            if (partialPortfolio.getPurchasePrice() != null) {
                existingPortfolio.setPurchasePrice(partialPortfolio.getPurchasePrice());
            }
            return portfolioRepository.save(existingPortfolio);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deletePortfolio(@PathVariable Long id) {
        logger.info("Deleting a portfolio");
        portfolioRepository.deleteById(id);
        return "Portfolio deleted successfully with id: " + id;
    }
}
