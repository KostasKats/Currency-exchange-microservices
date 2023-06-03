package com.example.currency.currencyservicenew;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {

    Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        String port= environment.getProperty("local.server.port");

//        LOGGER.trace("*** Local server port is: {} ***",port);

        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from,to);

        if(currencyExchange == null)
            throw new RuntimeException(String.format("Unable to find data for %s and %s",from,to));

        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }


}