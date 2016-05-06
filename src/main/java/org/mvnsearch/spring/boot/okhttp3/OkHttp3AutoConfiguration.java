package org.mvnsearch.spring.boot.okhttp3;


import com.codahale.metrics.MetricRegistry;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * okhttp3 auto configuration
 *
 * @author linux_china
 */
@Configuration
public class OkHttp3AutoConfiguration {
    @Autowired
    private List<Interceptor> interceptors;

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        interceptors.forEach(builder::addInterceptor);
        return builder.build();
    }

    @Bean
    public OkHttp3MetricsInterceptor okHttp3MetricsInterceptor() {
        return new OkHttp3MetricsInterceptor();
    }

    @Bean
    public OkHttp3Endpoint okHttp3Endpoint(MetricRegistry metrics) {
        return new OkHttp3Endpoint(metrics);
    }
}