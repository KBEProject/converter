package com.kbe.service.converter.controller;

import com.kbe.service.converter.model.Conversion;
import com.kbe.service.converter.model.Converter;
import com.kbe.service.converter.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversions")
@CrossOrigin(origins = {
        "*"
},
        allowedHeaders = "*",
        allowCredentials = "false",

        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.DELETE
        })
public class ConversionController {

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private Converter converter;


    @GetMapping("/findAll")
    public ResponseEntity<?> getAllConversions() {
        var conversions = conversionRepository.findAll();
        if (conversions.size() > 0) {
            return new ResponseEntity<>(conversions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no conversions found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findOne")
    public ResponseEntity<?> getOneCoversion(@RequestParam String id) {
        var conversion = conversionRepository.findConversionByID(id);
        if (!conversion.equals(null)) {
            return new ResponseEntity<>(conversion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conversion with id " + id + "not found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findConversionOfUser")
    public ResponseEntity<?> getUsersConversions(@RequestParam String user) {

        var conversions = conversionRepository.findConversionsOfUser(user);
        if (conversions.size() > 0) {
            return new ResponseEntity<>(conversions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conversions of user " + user + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Conversion entity) {
        converter.setConversion(entity);
        if (converter.convert()) {
            var conversion = conversionRepository.insert(converter.getConversion());
            if (!conversion.equals(null)) {
                return new ResponseEntity<>(conversion, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("conversion failed", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String id) {
        if (conversionRepository.findConversionByID(id) == null) {
            return new ResponseEntity<>("id: " + id + " doesn't exist", HttpStatus.NOT_FOUND);
        }

        conversionRepository.deleteById(id);
        if (conversionRepository.findConversionByID(id) == null) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        conversionRepository.deleteAll();
        if (conversionRepository.findAll().size() == 0) {
            return new ResponseEntity<>("All Conversions deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.NOT_FOUND);

        }
    }

    /**
     * In other words, the way for getting browsers to relax the same-origin policy
     * is for servers to use the Access-Control-Allow-Origin header
     * to indicate they’re opting in to cross-origin requests.
     * - StackOverflow
     */

}
