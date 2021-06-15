package ly.cook.cookly.service;

import ly.cook.cookly.model.Comment;
import ly.cook.cookly.model.User;
import ly.cook.cookly.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment loadCommentById(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment loadAndUpdateUndatedComment(LocalDate postTime) {
        Comment c = commentRepository.findByPostTime(postTime);
        c.setPostTime(LocalDate.now());
        commentRepository.save(c);

        return c;
    }

    public List<Comment> loadAll() {
        return commentRepository.findAll();
    }
}
