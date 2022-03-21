package com.kbe.service.converter;

import com.kbe.service.converter.model.Conversion;
import com.kbe.service.converter.model.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Here we only test if the functionality return true if one of
 * the value (coinvalue or usdvalue) is set
 * if both or none are set, it should return false
 */
@SpringBootTest
class ConversionValuesTest {

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

	// test the conversion from usd value to coin value
	@Test
	void usdValueToCoinValueTEST(){
		conversion.setUsdvalue(100);
		assertTrue(converter.convert());
	}
	// test the conversion from coin value to usd value
	@Test
	void coinValueToUsdValueTEST(){
		conversion.setCoinvalue(100);
		assertTrue(converter.convert());
	}
	//both the usd and the coin values are null / zero
	@Test
	void bothValuesZeroTEST(){
		conversion.setCoinvalue(0);
		conversion.setUsdvalue(0);
		assertFalse(converter.convert());
	}
	//both the usd and the coin values are not null / zero
	@Test
	void bothValuesAreSETTEST(){
		conversion.setCoinvalue(100);
		conversion.setUsdvalue(100);
		assertFalse(converter.convert());
	}

	@Test
	void contextLoads() {
	}

}
