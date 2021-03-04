package org.innso.api;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.innso.api.model.ClientFolder;
import org.innso.api.service.ClientFolderService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/clientFolders")
@Tag(name = "Client folder resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientFolderResource {

    @Inject ClientFolderService clientFolderService;

    @POST
    public ClientFolder newClientFolders(ClientFolder clientFolder) {

        return clientFolderService.newClientFolders(clientFolder);
    }

    @PUT
    @Path("/id/{id}/reference/{reference}")
    public ClientFolder updateReference(@PathParam("id") Long id, @PathParam("reference") String reference) {

        return clientFolderService.updateClientFolders(id, reference);
    }

    @GET
    public List<ClientFolder> getClientFolders() {

        return clientFolderService.getClientFolders();
    }
}
