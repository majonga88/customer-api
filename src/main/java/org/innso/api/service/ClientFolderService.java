package org.innso.api.service;

import org.innso.api.model.ClientFolder;
import org.innso.api.repository.ClientFolderRepository;
import org.innso.api.repository.MessageRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClientFolderService {

    @Inject ClientFolderRepository clientFolderRepository;
    @Inject MessageRepository messageRepository;

    @Transactional
    public ClientFolder newClientFolders(ClientFolder clientFolder) {

        clientFolder.setMessages(messageRepository.findAllByAuthorName(clientFolder.getClientName()));
        clientFolderRepository.persist(clientFolder);
        return clientFolder;
    }

    @Transactional
    public ClientFolder updateClientFolders(Long id, String reference) {

        ClientFolder clientFolder = clientFolderRepository.findById(id);
        if (clientFolder != null) {
            clientFolder.setReference(reference);
            //avoid LazyInitializationException
            clientFolder.getMessages();
            clientFolderRepository.persist(clientFolder);
            return clientFolder;
        }

        throw new IllegalArgumentException("No Client Folder with id " + id + " exists");
    }

    public List<ClientFolder> getClientFolders() {

        return clientFolderRepository.findAll().list();
    }
}
