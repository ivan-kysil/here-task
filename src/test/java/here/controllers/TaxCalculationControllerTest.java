package here.controllers;

import here.Application;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
public class TaxCalculationControllerTest {
    static {
        Locale.setDefault(Locale.US);
    }
    private static final String TAX_URL = "/tax";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void functionalTest1() throws Exception {
        mockMvc.perform(post(TAX_URL)
                .content(readDataFromClasspathFile("json/tax_calculate_test1.json"))
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesTax", is("1.50")));
    }

    @Test
    public void functionalTest2() throws Exception {
        mockMvc.perform(post(TAX_URL)
                .content(readDataFromClasspathFile("json/tax_calculate_test2.json"))
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesTax", is("7.65")));
    }

    @Test
    public void functionalTest3() throws Exception {
        mockMvc.perform(post(TAX_URL)
                .content(readDataFromClasspathFile("json/tax_calculate_test3.json"))
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesTax", is("6.70")));
    }

    @Test
    public void shouldProcessEmptyArrayTest() throws Exception {
        mockMvc.perform(post(TAX_URL)
                .content("[]")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesTax", is("0.00")));
    }

    @Test
    public void shouldReturnBadRequestIfNullArrayTest() throws Exception {
        mockMvc.perform(post(TAX_URL)
                .content("")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesTax", is("0.00")));
    }

    private String readDataFromClasspathFile(String fileName) throws IOException {
        Resource resource = webApplicationContext.getResource("classpath:" + fileName);
        return IOUtils.toString(resource.getInputStream(), Charset.forName("utf8"));
    }
}
