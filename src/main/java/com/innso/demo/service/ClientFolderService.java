package com.innso.demo.service;

import com.innso.demo.model.ClientFolder;
import com.innso.demo.repository.ClientFolderRepository;
import com.innso.demo.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ClientFolderService {

    private final ClientFolderRepository clientFolderRepository;
    private final MessageRepository messageRepository;

    public ClientFolder newClientFolders(ClientFolder clientFolder) {

        clientFolder.setMessages(messageRepository.findAllByAuthorName(clientFolder.getClientName()));

        return clientFolderRepository.save(clientFolder);
    }

    public ClientFolder updateClientFolders(Long id, String reference) {

        Optional<ClientFolder> clientFolderOptional = clientFolderRepository.findById(id);

        if (clientFolderOptional.isPresent()) {
            ClientFolder clientFolder = clientFolderOptional.get();
            clientFolder.setReference(reference);
            return clientFolderRepository.save(clientFolder);
        }

        throw new IllegalArgumentException("No Client Folder with id " + id + " exists");
    }

    public List<ClientFolder> getClientFolders() {

        return StreamSupport
                .stream(clientFolderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
