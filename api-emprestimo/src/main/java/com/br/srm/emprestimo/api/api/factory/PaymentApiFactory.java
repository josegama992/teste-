package com.br.srm.emprestimo.api.api.factory;

import com.br.srm.emprestimo.api.api.PaymentApi;
import com.br.srm.emprestimo.api.utils.Environments;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.concurrent.TimeUnit;

@Configuration
public class PaymentApiFactory {

    @Autowired
    private Environment env;

    @Bean
    public PaymentApi paymentApi() {

        String host = env.getProperty(Environments.PAYMENT_API_HOST, String.class);

        Integer connectionTimeout = env.getProperty(Environments.PAYMENT_API_CONNECT_TIMEOUT, Integer.class);
        Integer readTimeout = env.getProperty(Environments.PAYMENT_API_READ_TIMEOUT, Integer.class);

        return Feign.builder()
                .options(new Request.Options(connectionTimeout, TimeUnit.SECONDS, readTimeout, TimeUnit.SECONDS, false))
                .logger(new Slf4jLogger())
                .retryer(Retryer.NEVER_RETRY)
                .logLevel(Logger.Level.FULL)
                .target(PaymentApi.class, host);

    }
}
