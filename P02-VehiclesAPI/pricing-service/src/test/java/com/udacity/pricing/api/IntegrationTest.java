package com.udacity.pricing.api;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.udacity.vehicles.domain.car.Car;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTest {

    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getVehicle() {
        ResponseEntity<Car> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/cars/1", Car.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertFalse(response.getBody().getPrice().isEmpty());
    }
}
