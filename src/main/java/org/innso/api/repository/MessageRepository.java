package org.innso.api.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.innso.api.model.Message;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MessageRepository implements PanacheRepository<Message> {

    public List<Message> findAllByAuthorName(String authorName){
        return find("authorName", authorName).list();
    }
}
