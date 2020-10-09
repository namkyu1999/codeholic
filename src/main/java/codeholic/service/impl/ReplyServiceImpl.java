package codeholic.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import codeholic.domain.Board;
import codeholic.domain.Reply;
import codeholic.repository.ReplyRepository;
import codeholic.service.BoardService;
import codeholic.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    BoardService boardService;

    @Autowired
    ReplyRepository replyRepository;
    
    @Override
    public Reply findById(int id) {
       try{
            return replyRepository.findById(id).get();
       }catch(NoSuchElementException e){
           return null;
       }
    }

    @Override
    public List<Reply> getBoardReplies(Board board,int countPerPage,int currentPage) {
        try{
            int boardId = board.getId();
            Pageable pageable = PageRequest.of(currentPage-1,countPerPage,Sort.by("createdAt").ascending());
            Page<Reply> page = replyRepository.findReplyByOrderByIdAsc(boardId, pageable);
            return page.getContent();
        }catch(NoSuchElementException e){
            return null;
        }
    }
    

    @Override
    public void addReply(Reply reply, Board board) {
        reply.setBoard(board);
        replyRepository.save(reply);
    }

    @Override
    public void updateReply(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public void deleteReply(Reply reply) {
        replyRepository.delete(reply);
    }

    @Override
    public List<Reply> findReplyByBoard_id(int id) {
        return replyRepository.findReplyByBoard_id(id);
    }

    
}