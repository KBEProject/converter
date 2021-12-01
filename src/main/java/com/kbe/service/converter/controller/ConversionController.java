package com.kbe.service.converter.controller;

import com.kbe.service.converter.model.Coin;
import com.kbe.service.converter.model.Converter;
import com.kbe.service.converter.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversions")
public class ConversionController {

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private Converter converter;


    @GetMapping("/findAll")
    public ResponseEntity<?> getAllConversions() {
        List<Coin> coins = conversionRepository.findAll();
        if (coins.size() > 0) {
            return new ResponseEntity<>(coins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no conversions found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findOne")
    public ResponseEntity<?> getOneCoversion(@RequestParam String id) {
        Coin coin = conversionRepository.findConversionByID(id);
        if (!coin.equals(null)) {
            return new ResponseEntity<>(coin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conversion with id " + id + "not found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findUsers")
    public ResponseEntity<?> getUsersCoversions(@RequestParam String userId) {
        List<Coin> coins = conversionRepository.findConersionsOfUser(userId);
        if (coins.size() > 0) {
            return new ResponseEntity<>(coins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conversion with userId " + userId + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Coin entity) {
        //TODO: throws exception if "id" already exists.. handle that later
        Coin coin;
        converter.setCoin(entity);
        if (converter.convert()) {
            coin = conversionRepository.insert(converter.getCoin());
            if (!coin.equals(null)) {
                return new ResponseEntity<>("added: " + coin.toString(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("conversion failed", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String id) {
        if (conversionRepository.findConversionByID(id) == null) {
            return new ResponseEntity<>("id: " + id + " doesn't exist", HttpStatus.OK);
        }

        conversionRepository.deleteById(id);
        if (conversionRepository.findConversionByID(id) == null) {
            return new ResponseEntity<>("Conversion with id " + id + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.NOT_FOUND);

        }
    }
}
