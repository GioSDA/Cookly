package ly.cook.cookly.service;

import ly.cook.cookly.model.Comment;
import ly.cook.cookly.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment loadCommentById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

}
