package com.stock.capital.enterprise.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stock.core.message.MessageClient;
import com.stock.core.message.RabbitMQMessageClient;

@Configuration
public class MqConfigurer {

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public MessageClient messageClient(RabbitTemplate RabbitTemplate) {
        RabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return new RabbitMQMessageClient();
    }

    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange("amq.topic");
        return topicExchange;
    }

    @Bean
    public Queue FundCalendarTasklistener() {
        return new Queue("cloud.updateIpoData");
    }
//    @Bean
//    public Queue FundCalendarTasklistener() {
//        return new Queue("fund.CalendarTask.listener");
//    }

    
//    @Bean
//    public Queue helloQueue() {
//        return new Queue("helloQueue");
//    }
//    
//    @Bean
//    public Binding myBinding(Queue FundCalendarTasklistener,TopicExchange topicExchange) {
//        return BindingBuilder.bind(FundCalendarTasklistener).to(topicExchange).with("fund.CalendarTask.listener");
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("myQueue");
//        container.setMessageListener(exampleListnerAdapter());
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter exampleListnerAdapter() {
//        return new MessageListenerAdapter(exampleListner());
//    }
//
//    @Bean
//    public ExampleListener exampleListner() {
//        return new ExampleListener();
//    }
    

}
