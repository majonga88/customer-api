package com.innso.demo.controller;

import com.innso.demo.model.ClientFolder;
import com.innso.demo.service.ClientFolderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientFolders")
@AllArgsConstructor
public class ClientFolderEndpoint {

    private final ClientFolderService clientFolderService;

    @PostMapping
    public ClientFolder newClientFolders(@RequestBody ClientFolder clientFolder) {

        return clientFolderService.newClientFolders(clientFolder);
    }

    @PutMapping("/id/{id}/reference/{reference}")
    public ClientFolder updateClientFolders(@PathVariable(name = "id") Long id, @PathVariable(name = "reference") String reference) {

        return clientFolderService.updateClientFolders(id, reference);
    }

    @GetMapping
    public List<ClientFolder> getClientFolders() {

        return clientFolderService.getClientFolders();
    }
}
