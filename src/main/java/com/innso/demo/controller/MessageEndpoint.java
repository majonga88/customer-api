package com.innso.demo.controller;

import com.innso.demo.model.Message;
import com.innso.demo.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageEndpoint {

    private final MessageService messageService;

    @PostMapping
    public Message newMessage(@RequestBody Message message) {

        return messageService.newMessage(message, null);
    }

    @PostMapping("/{reference}")
    public Message newMessageWithClientReference(@RequestBody Message message, @PathVariable String reference) {

        return messageService.newMessage(message, reference);
    }


}
