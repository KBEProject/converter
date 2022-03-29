package com.kbe.service.converter.integrationTests;

import com.google.gson.Gson;
import com.kbe.service.converter.model.Conversion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Here we test if the functionality returns
 * the correct result after the conversion
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ConversionsToDatabaseTest {

    String BASEURL = "http://localhost:8083";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Conversion conversion;

    /*****************Insert-Tests****************/

    /**
     * check if saving conversion to database works
     *
     * @throws Exception
     */
    @Test
    void addConversionToDatabaseTest() throws Exception {
        String email = "suheib";

        conversion = new Conversion(UUID.randomUUID().toString(), email, "btc", "name", 0, 10);
        Gson gson = new Gson();

        RequestBuilder request = MockMvcRequestBuilders.post(BASEURL+"/conversions/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(conversion))
                .characterEncoding("utf-8");

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(gson.toJson(conversion), result.getResponse().getContentAsString());

        RequestBuilder request2 = MockMvcRequestBuilders.delete(BASEURL+"/conversions/deleteAllUserConversions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", email)
                .characterEncoding("utf-8");

        mockMvc.perform(request2).andReturn();
    }

    /**
     * check if conversion with both values set to 0 will
     * not be saved into the database
     *
     * @throws Exception
     */
    @Test
    void valuesToZeroTest() throws Exception {
        conversion = new Conversion(UUID.randomUUID().toString(), "suheib", "btc", "name", 0, 0);
        Gson gson = new Gson();

        RequestBuilder request = MockMvcRequestBuilders.post(BASEURL+"/conversions/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(conversion))
                .characterEncoding("utf-8");

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals("conversion failed", result.getResponse().getContentAsString());
    }

    /**
     * check if conversion with both values set to 0 will
     * not be saved into the database
     *
     * @throws Exception
     */
    @Test
    void bothValuesSetTest() throws Exception {
        conversion = new Conversion(UUID.randomUUID().toString(), "suheib", "btc", "name", 10, 10);
        Gson gson = new Gson();

        RequestBuilder request = MockMvcRequestBuilders.post(BASEURL+"/conversions/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(conversion))
                .characterEncoding("utf-8");

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals("conversion failed", result.getResponse().getContentAsString());
    }

    /*****************Find Conversions-Tests****************/

    /**
     * testing if it can find the conversions
     * of a specific user
     *
     * @throws Exception
     */
    @Test
    void findConversionsOfUserTest() throws Exception {
        String email = "findAllConv@mail.de";

        conversion = new Conversion(UUID.randomUUID().toString(), email, "btc", "name", 0, 10);
        Gson gson = new Gson();

        RequestBuilder request = MockMvcRequestBuilders.post(BASEURL+"/conversions/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(conversion))
                .characterEncoding("utf-8");


        mockMvc.perform(request).andReturn();

        RequestBuilder request2 = MockMvcRequestBuilders.get(BASEURL+"/conversions/findConversionsOfUser")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", email)
                .characterEncoding("utf-8");

        MvcResult result = mockMvc.perform(request2).andReturn();
        assertEquals("["+gson.toJson(conversion)+"]", result.getResponse().getContentAsString());

        RequestBuilder request3 = MockMvcRequestBuilders.delete(BASEURL+"/conversions/deleteAllUserConversions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", email)
                .characterEncoding("utf-8");

        mockMvc.perform(request3).andReturn();
    }

    /**
     * testing if it can find the conversions
     * of a specific user
     *
     * @throws Exception
     */
    @Test
    void findConversionsOfUserFailsTest() throws Exception {
        String email = "unknown@mail.de";

        RequestBuilder request2 = MockMvcRequestBuilders.get(BASEURL+"/conversions/findConversionsOfUser")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", email)
                .characterEncoding("utf-8");

        MvcResult result = mockMvc.perform(request2).andReturn();
        assertEquals("Conversions of user " + email + " not found!", result.getResponse().getContentAsString());
    }

    /*****************Delete Conversions-Tests****************/

    /**
     * check if deleting conversions of user from database works
     *
     * @throws Exception
     */
    @Test
    void deleteConversionTest() throws Exception {
        String email = "deleteUser@mail.de";

        conversion = new Conversion(UUID.randomUUID().toString(), email, "btc", "name", 0, 10);
        Gson gson = new Gson();

        RequestBuilder request = MockMvcRequestBuilders.post(BASEURL+"/conversions/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(conversion))
                .characterEncoding("utf-8");

        mockMvc.perform(request).andReturn();

        RequestBuilder request2 = MockMvcRequestBuilders.delete(BASEURL+"/conversions/deleteAllUserConversions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", email)
                .characterEncoding("utf-8");

        MvcResult result2 = mockMvc.perform(request2).andReturn();
        assertEquals("All conversions of user deleted", result2.getResponse().getContentAsString());
    }

    /**
     * testing sending a false email address to the api
     *
     * @throws Exception
     */
    @Test
    void deleteConversionFailsTest() throws Exception {
        String email = "randomFalseEmail@Test.net";


        RequestBuilder request2 = MockMvcRequestBuilders.delete(BASEURL+"/conversions/deleteAllUserConversions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", email)
                .characterEncoding("utf-8");

        MvcResult result2 = mockMvc.perform(request2).andReturn();
        assertEquals("Email doesn't exist", result2.getResponse().getContentAsString());
    }


    /*****************Duplicate Conversions-Tests****************/

    /**
     * test if conversion with duplicate key is send to database
     *
     * @throws Exception
     */

    @Test
    void conversionAlreadyExistsTest() throws Exception {
        String email = "deletetest@mail.de";
        conversion = new Conversion("sameID", email, "btc", "name", 0, 10);
        Gson gson = new Gson();

        RequestBuilder request = MockMvcRequestBuilders.post(BASEURL+"/conversions/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(conversion))
                .characterEncoding("utf-8");

        mockMvc.perform(request).andReturn(); //first conversion
        MvcResult result = mockMvc.perform(request).andReturn(); //second conversion with same key
        assertEquals("duplicate key", result.getResponse().getContentAsString());

        RequestBuilder request2 = MockMvcRequestBuilders.delete(BASEURL+"/conversions/deleteAllUserConversions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", email)
                .characterEncoding("utf-8");

        mockMvc.perform(request2).andReturn();
    }

    @Test
    void contextLoads() {
    }
}
