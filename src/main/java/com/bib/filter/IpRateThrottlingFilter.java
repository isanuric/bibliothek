package com.bib.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.grid.GridBucketState;
import io.github.bucket4j.grid.ProxyManager;
import io.github.bucket4j.grid.jcache.JCache;
import java.io.IOException;
import java.time.Duration;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;


@Component
public class IpRateThrottlingFilter implements Filter {

    private static final BucketConfiguration configuration = Bucket4j.configurationBuilder()
            .addLimit(Bandwidth.simple(3, Duration.ofSeconds(4)))
            .build();

    // cache for storing token buckets, where IP is key.
    private javax.cache.Cache<String, GridBucketState> cache;

    private ProxyManager<String> buckets;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // init bucket registry
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        this.cache = cacheManager.createCache("ip-rate-limiting", getConfig());
        buckets = Bucket4j.extension(JCache.class).proxyManagerForCache(cache);
    }

    private MutableConfiguration<String, GridBucketState> getConfig() {
        return new MutableConfiguration<String, GridBucketState>().setTypes(String.class, GridBucketState.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        Bucket bucket = buckets.getProxy(request.getRemoteAddr(), configuration);
        System.out.println("Number of tokens: " + bucket.getAvailableTokens());

        boolean tryConsume = bucket.tryConsume(1);
        if (tryConsume) {
            filterChain.doFilter(request, response);
        } else {
            System.out.println("- " + bucket.getAvailableTokens());
            // limit is exceeded
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(429);
            httpResponse.getWriter().append("Too Many Requests");
        }
    }

}
