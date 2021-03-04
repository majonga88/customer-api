package org.innso.api;

import org.innso.api.model.Message;

import org.innso.api.service.MessageService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/messages")
@Tag(name = "Message resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Inject MessageService messageService;

    @POST
    public Message newMessage(Message message) {

        return messageService.newMessage(message, null);
    }

    @POST
    @Path("/{reference}")
    public Message newMessageWithClientReference(Message message, @PathParam String reference) {

        return messageService.newMessage(message, reference);
    }
}
