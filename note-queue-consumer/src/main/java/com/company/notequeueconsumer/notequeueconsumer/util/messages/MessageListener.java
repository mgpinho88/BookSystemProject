package com.company.notequeueconsumer.notequeueconsumer.util.messages;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @RabbitListener(queues = NoteQueueConsumer.QUEUE_NAME)
    public void receiveMessage(Note msg) {
        System.out.println(msg.toString());
    }
}
