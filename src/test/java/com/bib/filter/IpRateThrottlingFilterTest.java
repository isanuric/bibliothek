package com.bib.filter;


import com.bib.BaseTest;
import org.junit.Test;


public class IpRateThrottlingFilterTest extends BaseTest {

    @Test
    public void ip_throttling() throws InterruptedException {

        int i = 3;
        while (i > 0) {
            System.out.println("Start ------ " + i);
            webTestClient
                    .get().uri("/login")
                    .exchange()
                    .expectStatus().isOk();
            i--;
        }

        System.out.println("--------");
        webTestClient
                .get().uri("/login")
                .exchange()
                .expectStatus().isEqualTo(429);
    }

}