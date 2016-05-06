package org.mvnsearch.spring.boot.okhttp3;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * okhttp3 metrics interceptor
 *
 * @author linux_china
 */
public class OkHttp3MetricsInterceptor implements Interceptor {
    @Autowired
    private MetricRegistry metrics;

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String host = request.url().host();
        Response response;
        final Timer timer = metrics.timer(name(OkHttpClient.class, host, request.method()));
        final Timer.Context context = timer.time();
        try {
            response = chain.proceed(request);
        } finally {
            context.stop();
        }
        return response;
    }
}
