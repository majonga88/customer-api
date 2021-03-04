package com.innso.demo.repository;

import com.innso.demo.model.ClientFolder;
import org.springframework.data.repository.CrudRepository;

public interface ClientFolderRepository extends CrudRepository<ClientFolder, Long> {

    ClientFolder findClientFolderByReference(String reference);

}
