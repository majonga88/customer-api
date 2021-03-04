package org.innso.api.service;

import org.innso.api.model.ClientFolder;
import org.innso.api.model.Message;
import org.innso.api.repository.ClientFolderRepository;
import org.innso.api.repository.MessageRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class MessageService {

    @Inject MessageRepository messageRepository;
    @Inject ClientFolderRepository clientFolderRepository;

    public Message newMessage(Message message, String reference) {

        messageRepository.persist(message);

        if (reference != null) {
            ClientFolder clientFolderByReference = clientFolderRepository.findClientFolderByReference(reference);
            List<Message> messages = clientFolderByReference.getMessages();
            messages.add(message);
            clientFolderByReference.setMessages(messages);
            clientFolderRepository.persist(clientFolderByReference);
        }
        return message;
    }
}
