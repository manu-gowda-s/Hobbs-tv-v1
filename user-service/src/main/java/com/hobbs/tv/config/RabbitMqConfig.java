package com.hobbs.tv.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig
{
    private static final String USER_RK = "user_routing_key_1";

//    private static final String EMAIL_RK = "email_routing_key_1";

    // spring bean for queue - USER queue
    @Bean
    public Queue userQueue(){
        return new Queue("user.producer", false);
    }

    // spring bean for queue - Email queue
//    @Bean
//    public Queue emailQueue(){
//        return new Queue("email.producer",false);
//    }

    // spring bean for exchange
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange("user_details_exchange_1");
    }

//    @Bean
//    public DirectExchange emailExchange(){
//        return new DirectExchange("email_details_exchange_1");
//    }

    // spring bean for binding between exchange and queue using routing key
    @Bean
    public Binding binding(Queue userQueue, DirectExchange exchange){
        return BindingBuilder
                .bind(userQueue)
                .to(exchange)
                .with(USER_RK);
    }

//    // spring bean for binding between exchange and queue using routing key
//    @Bean
//    public Binding emailBinding(Queue emailQueue, DirectExchange emailExchange ){
//        return BindingBuilder
//                .bind(emailQueue)
//                .to(emailExchange)
//                .with(EMAIL_RK);
//    }

    // message converter
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    // configure RabbitTemplate
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
