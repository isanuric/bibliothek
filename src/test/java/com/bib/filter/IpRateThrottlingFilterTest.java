package com.bib.filter;


import com.bib.BaseTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class IpRateThrottlingFilterTest extends BaseTest {

    @Ignore
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