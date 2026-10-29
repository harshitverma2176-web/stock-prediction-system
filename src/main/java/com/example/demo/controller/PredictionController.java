package com.example.demo.controller;

import com.example.demo.entity.Prediction;
import com.example.demo.repository.PredictionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    private static final Logger logger = LoggerFactory.getLogger(PredictionController.class);

    private final PredictionRepository predictionRepository;

    public PredictionController(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @GetMapping
    public List<Prediction> getAllPredictions() {
        logger.info("Getting all predictions");
        return predictionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Prediction getPredictionById(@PathVariable Long id) {
        logger.info("Getting prediction by id: " + id);
        return predictionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Prediction createPrediction(@RequestBody Prediction prediction) {
        logger.info("Adding a prediction");
        return predictionRepository.save(prediction);
    }

    @PutMapping("/{id}")
    public Prediction updatePrediction(@PathVariable Long id, @RequestBody Prediction updatedPrediction) {
        logger.info("Updating a prediction");
        Prediction existingPrediction = predictionRepository.findById(id).orElse(null);
        if (existingPrediction != null) {
            if (updatedPrediction.getStock() != null) {
                existingPrediction.setStock(updatedPrediction.getStock());
            }
            existingPrediction.setPredictedPrice(updatedPrediction.getPredictedPrice());
            existingPrediction.setRecommendation(updatedPrediction.getRecommendation());
            existingPrediction.setConfidence(updatedPrediction.getConfidence());
            existingPrediction.setPredictionDate(updatedPrediction.getPredictionDate());
            return predictionRepository.save(existingPrediction);
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Prediction patchPrediction(@PathVariable Long id, @RequestBody Prediction partialPrediction) {
        logger.info("Patching a prediction");
        Prediction existingPrediction = predictionRepository.findById(id).orElse(null);
        if (existingPrediction != null) {
            if (partialPrediction.getStock() != null) {
                existingPrediction.setStock(partialPrediction.getStock());
            }
            if (partialPrediction.getPredictedPrice() != null) {
                existingPrediction.setPredictedPrice(partialPrediction.getPredictedPrice());
            }
            if (partialPrediction.getRecommendation() != null) {
                existingPrediction.setRecommendation(partialPrediction.getRecommendation());
            }
            if (partialPrediction.getConfidence() != null) {
                existingPrediction.setConfidence(partialPrediction.getConfidence());
            }
            if (partialPrediction.getPredictionDate() != null) {
                existingPrediction.setPredictionDate(partialPrediction.getPredictionDate());
            }
            return predictionRepository.save(existingPrediction);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deletePrediction(@PathVariable Long id) {
        logger.info("Deleting a prediction");
        predictionRepository.deleteById(id);
        return "Prediction record deleted successfully with id: " + id;
    }
}
