package com.example.loadtest.controller;

import com.example.loadtest.message.Producer;
import com.example.loadtest.model.Transaction;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;


@RestController
@AllArgsConstructor
public class TransactionController {

    private final Producer producer;


    @PostMapping("transaction/publish")
    public void publish(@RequestBody Transaction transaction) throws Exception {
        producer.send(transaction);
    }

    @PostMapping("transactions/publish")
    public void generateRandom() throws Exception {
        var random = new Random();

        for (int i = 0; i < 1000; i++) {
            var transaction = new Transaction();
            transaction.setTransactionId("TXN" + RandomStringUtils.randomNumeric(6));
            transaction.setToAccount("TAC" + RandomStringUtils.randomNumeric(6));
            transaction.setFromAccount("FAC" + RandomStringUtils.randomNumeric(6));
            transaction.setDescription(generateString(random.nextInt(6), i).trim());
            transaction.setAmount(new BigDecimal(Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP));
            producer.send(transaction);
        }
    }


    private String generateString(int length, int messageCount) {

        switch (messageCount) {
            case 10:
                return "Hope you die";
            case 20:
                return "choke on this money";
            case 30:
                return "stupid bitch";
            default:
                length = length == 0 ? 1 : length;
                var description = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    description.append(RandomStringUtils.randomAlphabetic(2, 10) + " ");
                }
                return description.toString();
        }


    }

}
