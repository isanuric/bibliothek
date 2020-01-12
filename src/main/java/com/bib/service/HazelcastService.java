package com.bib.service;

import static com.hazelcast.core.Hazelcast.newHazelcastInstance;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.IMap;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import io.github.bucket4j.grid.GridBucketState;
import io.github.bucket4j.grid.ProxyManager;
import io.github.bucket4j.grid.RecoveryStrategy;
import io.github.bucket4j.grid.hazelcast.Hazelcast;
import io.github.bucket4j.grid.hazelcast.HazelcastBucketBuilder;
import java.time.Duration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class HazelcastService {

    private final IMap<String, GridBucketState> cache;
    private ProxyManager<String> buckets;

    public HazelcastService() {
        Config config = new Config();
        config.setLiteMember(false);
        CacheSimpleConfig cacheConfig = new CacheSimpleConfig();
        cacheConfig.setName("buckets");
        config.addCacheConfig(cacheConfig);
        var hazelcastInstance = newHazelcastInstance(config);

        this.cache = hazelcastInstance.getMap("my-distributed-map");
        this.buckets = Bucket4j.extension(Hazelcast.class).proxyManagerForMap(this.cache);
    }

    public void atest() {

        Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
        var limit = Bandwidth.classic(3, refill);
        var limitShort = Bandwidth.simple(1, Duration.ofSeconds(1));
//        Bucket bucket = Bucket4j.builder()
//                .addLimit(limit)
//                .addLimit(limitShort)
//                .build();

//        IMap<String, GridBucketState> map = this.hzInstance.getMap("bucket-map");
//        bucket = Bucket4j.extension(Hazelcast.class).builder().addLimit(limit)
//                .build(map, "rate-limit", RecoveryStrategy.RECONSTRUCT);

        Bucket hazelcast = Bucket4j.extension(Hazelcast.class).builder().addLimit(limit)
                .build(cache, "key", RecoveryStrategy.RECONSTRUCT);

        ConsumptionProbe consumptionProbe = hazelcast.tryConsumeAndReturnRemaining(1);

        if (consumptionProbe.isConsumed()) {
            System.out.println("is consumed");
            ;
        } else {
            System.out.println("not consumed");
        }
    }


    public void mytest(HttpServletRequest httpRequest) {
//        BucketConfiguration bucketConfiguration = Bucket4j.configurationBuilder()
//                .addLimit(Bandwidth.simple(10, Duration.ofMinutes(1)))
//                .buildConfiguration();
//
//        // prepare configuration supplier which will be called(on first interaction with proxy) iff bucket was not saved yet previously.
//        Supplier<BucketConfiguration> configurationLazySupplier = () -> bucketConfiguration;
//
//        // acquire cheap proxy to bucket, the real
//        Bucket bucket = buckets.getProxy(httpRequest.getRemoteAddr(), configurationLazySupplier);

//        Bucket bucket = Bucket4j.extension(Hazelcast.class).builder()
//                .addLimit(Bandwidth.simple(1_000, Duration.ofMinutes(1)))
//                .build(map, key, RecoveryStrategy.RECONSTRUCT);
//        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

//        if (probe.isConsumed()) {
//            // the limit is not exceeded
//            System.out.println("is consumed");
//        } else {
//            // limit is exceeded
//            System.out.println("limit is exceeded");
//        }

    }

    private Bucket createBucket() {
        Refill refill = Refill.greedy(6, Duration.ofHours(5));
        Bandwidth bandwidth = Bandwidth.classic(25, refill);

        HazelcastBucketBuilder hazelcast = Bucket4j.extension(Hazelcast.class).builder();
        return hazelcast.addLimit(bandwidth).build(cache, "key", RecoveryStrategy.RECONSTRUCT);
    }


}

