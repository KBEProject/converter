package com.kbe.service.converter.controller;

import com.kbe.service.converter.model.Conversion;
import com.kbe.service.converter.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversions")
public class ConversionController {

    @Autowired
    private ConversionRepository conversionRepository;


    @GetMapping("/findAll")
    public ResponseEntity<?> getAllConversions() {
        List<Conversion> conversions = conversionRepository.findAll();
        if (conversions.size() > 0) {
            return new ResponseEntity<>(conversions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no conversions found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findOne")
    public ResponseEntity<?> getOneCoversion(@RequestParam String id) {
        Conversion conversion = conversionRepository.findConversionByID(id);
        if (!conversion.equals(null)) {
            return new ResponseEntity<>(conversion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conversion with id " + id + "not found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findUsers")
    public ResponseEntity<?> getUsersCoversions(@RequestParam String userId) {
        List<Conversion> conversions = conversionRepository.findConersionsOfUser(userId);
        if (conversions.size()>0) {
            return new ResponseEntity<>(conversions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conversion with userId " + userId + "not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Conversion entity) {
        Conversion conversion = conversionRepository.insert(entity);
        if (!conversion.equals(null)) {
            return new ResponseEntity<>("added: " + conversion.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no conversions found!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String id) {
        if (conversionRepository.findConversionByID(id) == null) {
            return new ResponseEntity<>("id: "+id+" doesn't exist", HttpStatus.OK);
        }

        conversionRepository.deleteById(id);
        if (conversionRepository.findConversionByID(id) == null) {
            return new ResponseEntity<>("Conversion with id " + id + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.NOT_FOUND);

        }
    }
}
