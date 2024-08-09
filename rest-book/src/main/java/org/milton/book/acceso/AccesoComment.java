package org.milton.book.acceso;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.milton.book.modelo.Author;
import org.milton.book.modelo.Comment;

import java.util.List;

@ApplicationScoped
public class AccesoComment implements AccesoCommentInterface{

    @Inject
    EntityManager em;

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Comment> findAllComments() {
        return listAll();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Comment findCommentById(Long id) {
        return findById(id);
    }

    @Override
    @Transactional
    public Comment persistComment(Comment comment) {
        persist(comment);
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        return em.merge(comment);
    }

    @Override
    public void deleteComment(Long id) {
        deleteById(id);
    }
}
