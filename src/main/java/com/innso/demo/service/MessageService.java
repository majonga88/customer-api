package com.innso.demo.service;

import com.innso.demo.model.ClientFolder;
import com.innso.demo.model.Message;
import com.innso.demo.repository.ClientFolderRepository;
import com.innso.demo.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ClientFolderRepository clientFolderRepository;

    public Message newMessage(Message message, String reference) {

        Message messageSaved = messageRepository.save(message);

        if (reference != null) {
            ClientFolder clientFolderByReference = clientFolderRepository.findClientFolderByReference(reference);
            List<Message> messages = clientFolderByReference.getMessages();
            messages.add(messageSaved);
            clientFolderByReference.setMessages(messages);
            clientFolderRepository.save(clientFolderByReference);
        }
        return message;
    }
}
