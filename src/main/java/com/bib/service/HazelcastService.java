package com.bib.service;

import static com.hazelcast.core.Hazelcast.newHazelcastInstance;
import static java.lang.Thread.sleep;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.IMap;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import io.github.bucket4j.grid.GridBucketState;
import io.github.bucket4j.grid.ProxyManager;
import io.github.bucket4j.grid.RecoveryStrategy;
import io.github.bucket4j.grid.hazelcast.Hazelcast;
import io.github.bucket4j.grid.hazelcast.HazelcastBucketBuilder;
import java.time.Duration;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;


@Service
public class HazelcastService {

    private final IMap<String, GridBucketState> map;
    private ProxyManager proxyManager;

    public HazelcastService() {
        System.out.println("HazelcastService");
        Config config = new Config();
        config.setLiteMember(false);
        CacheSimpleConfig cacheConfig = new CacheSimpleConfig();
        cacheConfig.setName("buckets");
        config.addCacheConfig(cacheConfig);
        var hazelcastInstance = newHazelcastInstance(config);

        this.map = hazelcastInstance.getMap("buckets-a");


        this.proxyManager = Bucket4j.extension(Hazelcast.class).proxyManagerForMap(this.map);


        Refill refill = Refill.intervally(1, Duration.ofSeconds(2));
        var limit = Bandwidth.classic(3, refill);
        Bucket4j.extension(Hazelcast.class).builder().addLimit(limit)
                        .build(map, "ip", RecoveryStrategy.RECONSTRUCT);

        Bucket4j.extension(Hazelcast.class).builder().addLimit(limit)
                        .build(map, "ip-2", RecoveryStrategy.RECONSTRUCT);

    }

    public void useBucket() throws InterruptedException {

//        Optional<Bucket> proxy = this.buckets.getProxy("buckets");
//        System.out.println("useBucket " + proxy);
//        this.buckets.tryConsume(1);
//        System.out.println(this.buckets.getAvailableTokens());
//        this.buckets.

        BucketConfiguration bucketConfiguration = Bucket4j.configurationBuilder()
                .addLimit(Bandwidth.simple(10, Duration.ofMinutes(1)))
                .build();

//        // prepare configuration supplier which will be called(on first interaction with proxy) if bucket was not saved yet previously.
//        Supplier<BucketConfiguration> configurationLazySupplier = () -> bucketConfiguration;
        // prepare configuration supplier which will be called(on first interaction with proxy) if bucket was not saved yet previously.
//        Supplier<BucketConfiguration> configurationLazySupplier = getConfigSupplier();

        // acquire cheap proxy to bucket, the real
        Bucket bucket = this.proxyManager.getProxy("ip", getConfigSupplier());

        System.out.println(bucket.getAvailableTokens());
        System.out.println(bucket.tryConsumeAndReturnRemaining(1));
        System.out.println(bucket.tryConsumeAndReturnRemaining(1));
        System.out.println(bucket.tryConsumeAndReturnRemaining(1));
        System.out.println(bucket.tryConsumeAndReturnRemaining(1));
        sleep(1000);
        System.out.println(bucket.getAvailableTokens());
        sleep(2000);
        System.out.println(bucket.getAvailableTokens());

        System.out.println("----");
        Bucket bucket02 = this.proxyManager.getProxy("ip-2", bucketConfiguration);
        System.out.println(bucket02.getAvailableTokens());

        System.out.println("----");
        Bucket bucket03 = this.proxyManager.getProxy("not-exist", getConfigSupplier());
        System.out.println(bucket03.getAvailableTokens());
        System.out.println(bucket03.tryConsumeAndReturnRemaining(1));
        System.out.println(bucket03.tryConsumeAndReturnRemaining(1));
        System.out.println(bucket03.tryConsumeAndReturnRemaining(1));
        System.out.println(bucket03.tryConsumeAndReturnRemaining(1));

        System.out.println("----");
        sleep(2000);
        Bucket bucket031 = this.proxyManager.getProxy("not-exist", getConfigSupplier());
        System.out.println(bucket031.getAvailableTokens());

    }

    private Supplier<BucketConfiguration> getConfigSupplier() {
        Refill refill = Refill.intervally(1, Duration.ofSeconds(2));
        var limit = Bandwidth.classic(500, refill);
        return () -> {
            return Bucket4j.configurationBuilder()
                    .addLimit(limit)
                    .build();
        };
    }

//    public void atest() {
//
//        Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
//        var limit = Bandwidth.classic(3, refill);
//        var limitShort = Bandwidth.simple(1, Duration.ofSeconds(1));
//        Bucket bucket = Bucket4j.builder()
//                .addLimit(limit)
//                .addLimit(limitShort)
//                .build();

//        IMap<String, GridBucketState> map = this.hzInstance.getMap("bucket-map");
//        bucket = Bucket4j.extension(Hazelcast.class).builder().addLimit(limit)
//                .build(map, "rate-limit", RecoveryStrategy.RECONSTRUCT);

//        Bucket hazelcast = Bucket4j.extension(Hazelcast.class).builder().addLimit(limit)
//                .build(map, "key", RecoveryStrategy.RECONSTRUCT);
//
//        ConsumptionProbe consumptionProbe = hazelcast.tryConsumeAndReturnRemaining(1);
//
//        if (consumptionProbe.isConsumed()) {
//            System.out.println("is consumed");
//            ;
//        } else {
//            System.out.println("not consumed");
//        }
//    }


//    public void mytest(HttpServletRequest httpRequest) {
//        BucketConfiguration bucketConfiguration = Bucket4j.configurationBuilder()
//                .addLimit(Bandwidth.simple(10, Duration.ofMinutes(1)))
//                .build();

//        // prepare configuration supplier which will be called(on first interaction with proxy) iff bucket was not saved yet previously.
//        Supplier<BucketConfiguration> configurationLazySupplier = () -> bucketConfiguration;
//
//        // acquire cheap proxy to bucket, the real
//        Bucket bucket = this.proxyManager.getProxy(httpRequest.getRemoteAddr(), configurationLazySupplier);

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
//    }


//    private Bucket createBucket() {
//        Refill refill = Refill.greedy(6, Duration.ofHours(5));
//        Bandwidth bandwidth = Bandwidth.classic(25, refill);
//
//        HazelcastBucketBuilder hazelcast = Bucket4j.extension(Hazelcast.class).builder();
//        return hazelcast.addLimit(bandwidth).build(map, "key", RecoveryStrategy.RECONSTRUCT);
//    }


}

