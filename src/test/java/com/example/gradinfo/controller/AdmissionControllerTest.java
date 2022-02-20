package com.example.gradinfo.controller;

import com.example.gradinfo.GradInfoApplicationTests;
import com.example.gradinfo.service.AdmissionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
/**
 * AdmissionController Tester.
 *
 * @author <mingzhe ruan>
 * @version 1.0
 * @since <pre>Feb 2, 2022</pre>
 */

public class AdmissionControllerTest extends GradInfoApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: getStudentPostDataByStudentIDAndPostNumber(@RequestParam String studentId, String spPostNumber)
     */


    @Test
    public void testGetStudentPostDataByStudentIDAndPostNumber() throws Exception {
        mockMvc.perform(get("/v1/users/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString("\"name\":\"lyTongXue\"")));
    }

    /**
     * Method: getAdmissionCourseTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber)
     */
    @Test
    public void testGetAdmissionCourseTableDataByIDAndPostNumber() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: postAdmissionCourseTableDataByNewArr(@RequestBody AdmissionCourseRequest admissionCourseRequest)
     */
    @Test
    public void testPostAdmissionCourseTableDataByNewArr() throws Exception {
//TODO: Test goes here... 
    }


} 
