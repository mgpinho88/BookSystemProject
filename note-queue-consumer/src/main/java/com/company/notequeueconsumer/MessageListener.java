package com.company.notequeueconsumer;

import com.company.notequeueconsumer.util.feign.NoteClient;
import com.company.notequeueconsumer.util.messages.Note;
import com.company.notequeueconsumer.notequeueconsumer.NoteQueueConsumerApplication;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    NoteClient client;

    public MessageListener(NoteClient client) {
        this.client = client;
    }

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Note msg) {
        System.out.println(msg.toString());

        if (msg.getNoteId() == 0) {
            client.addNote(msg);
        } else {
            client.updateNote(msg.getNoteId(), msg);
        }
    }
}
