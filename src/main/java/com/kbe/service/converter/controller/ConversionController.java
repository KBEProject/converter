package com.kbe.service.converter.controller;

import com.kbe.service.converter.model.Conversion;
import com.kbe.service.converter.model.Converter;
import com.kbe.service.converter.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/conversions")
public class ConversionController {

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private Converter converter;

    @GetMapping("/findConversionsOfUser")
    public ResponseEntity<?> getUsersConversions(@RequestParam String email) {

        var conversions = conversionRepository.findConversionsOfUser(email);
        if (conversions.size() > 0) {
            return new ResponseEntity<>(conversions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conversions of user " + email + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Conversion entity) {
        converter.setConversion(entity);
        Conversion conversion;
        if (converter.convert()) {
            try{
                conversion = conversionRepository.insert(converter.getConversion());
            } catch (Exception e){
                return new ResponseEntity<>("duplicate key", HttpStatus.BAD_REQUEST);
            }
            if (!conversion.equals(null)) {
                return new ResponseEntity<>(conversion, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("conversion failed", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/deleteAllUserConversions")
    public ResponseEntity<?> deleteAll(@RequestParam String email) {
        var conversions = conversionRepository.findConversionsOfUser(email);
        if(conversions.size() == 0){
            return new ResponseEntity<>("Email doesn't exist", HttpStatus.BAD_REQUEST);
        }

        conversions.stream().forEach(conversion -> conversionRepository.delete(conversion));

        if (conversionRepository.findConversionsOfUser(email).size() == 0) {
            return new ResponseEntity<>("All conversions of user deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.NOT_FOUND);
        }
    }
}
