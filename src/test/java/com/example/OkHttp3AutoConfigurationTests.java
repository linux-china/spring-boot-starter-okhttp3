package com.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.mvnsearch.spring.boot.okhttp3.OkHttp3AutoConfiguration;
import org.mvnsearch.spring.boot.okhttp3.OkHttp3Endpoint;
import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * okhttp3 client auto configuration tests
 *
 * @author linux_china
 */
@Configuration
public class OkHttp3AutoConfigurationTests {
    private static ApplicationContext context;

    @BeforeClass
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(
                OkHttp3AutoConfigurationTests.class, OkHttp3AutoConfiguration.class);
    }

    @Bean
    public MetricRegistry registry() {
        return new MetricRegistry();
    }

    @Test
    public void testHttpClient() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        OkHttpClient client = context.getBean(OkHttpClient.class);
        String url = "http://ip.mvnsearch.org";
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        OkHttp3Endpoint endpoint = context.getBean(OkHttp3Endpoint.class);
        System.out.println(objectMapper.writeValueAsString(endpoint));
    }

}
