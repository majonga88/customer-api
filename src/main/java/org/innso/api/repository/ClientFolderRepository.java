package org.innso.api.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.innso.api.model.ClientFolder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientFolderRepository implements PanacheRepository<ClientFolder> {

    public ClientFolder findClientFolderByReference(String reference){
        return find("reference", reference).firstResult();
    }
}
