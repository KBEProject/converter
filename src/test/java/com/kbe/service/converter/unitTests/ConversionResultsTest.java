package com.kbe.service.converter.unitTests;

import com.kbe.service.converter.model.Conversion;
import com.kbe.service.converter.model.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Here we test if the functionality returns
 * the correct result after the conversion
 */
@SpringBootTest
class ConversionResultsTest {

    Converter converter;
    Conversion conversion;

    /**
     * before every test we instantiate a converter
     * with a conversion object with the currency value of 10
     */
    @BeforeEach
    void setup(){
        conversion = new Conversion();
        converter = new Converter(conversion);
        converter.setCurrencyValue(10);
    }

    // check the result after the conversion from usd value to coin value
    @Test
    void usdValueToCoinValueTEST(){
        conversion.setUsdvalue(77);
        converter.convert();
        assertEquals(conversion.getCoinvalue(), 7.7);
    }
    // check the result after the conversion from coin value to usd value
    @Test
    void coinValueToUsdValueTEST(){
        conversion.setCoinvalue(50);
        converter.convert();
        assertEquals(conversion.getUsdvalue(), 500);
    }

    @Test
    void contextLoads() {
    }

}
