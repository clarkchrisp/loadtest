package com.example.loadtest.message;

import com.example.loadtest.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Producer {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;

    public void send(Transaction transaction) throws JsonProcessingException {
        jmsTemplate.convertAndSend("transactionListener", mapper.writeValueAsString(transaction));
    }
}
