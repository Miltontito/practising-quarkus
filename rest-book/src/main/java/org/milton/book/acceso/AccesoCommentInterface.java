package org.milton.book.acceso;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.milton.book.modelo.Comment;

import java.util.List;

@ApplicationScoped
public interface AccesoCommentInterface extends PanacheRepository<Comment> {
    List<Comment> findAllComments();
    Comment findCommentById(Long id);
    Comment persistComment(Comment comment);
    Comment updateComment(Comment comment);
    void deleteComment(Long id);
}
