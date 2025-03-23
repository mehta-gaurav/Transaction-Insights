package com.og.transactioninsight.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "transaction.exchange";
    public static final String CREATED_QUEUE = "transaction.created.queue";
    public static final String DELETED_QUEUE = "transaction.deleted.queue";
    public static final String CREATED_ROUTING_KEY = "transaction.created";
    public static final String DELETED_ROUTING_KEY = "transaction.deleted";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue createdQueue() {
        return new Queue(CREATED_QUEUE);
    }

    @Bean
    public Queue deletedQueue() {
        return new Queue(DELETED_QUEUE);
    }

    @Bean
    public Binding createdBinding(Queue createdQueue, TopicExchange exchange) {
        return BindingBuilder.bind(createdQueue).to(exchange).with(CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding deletedBinding(Queue deletedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(deletedQueue).to(exchange).with(DELETED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
