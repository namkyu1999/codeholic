package codeholic.controller;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codeholic.domain.Board;
import codeholic.domain.Reply;
import codeholic.domain.Response;
import codeholic.domain.request.RequestNewReply;
import codeholic.domain.request.RequestUpdateReply;
import codeholic.service.BoardService;
import codeholic.service.ReplyService;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("replies")
public class ReplyController {

    
    @Value("${countPerPage}")
    int countPerPage;

    @Autowired
    BoardService boardService;

    @Autowired
    ReplyService replyService;

    @GetMapping("/{board}/{pageNum}")
    public Response returnAllReplies(@PathVariable Optional<Integer> board,@PathVariable Optional<Integer> pageNum){
        Response response = new Response();
        try{
            List<Reply> replies = replyService.getBoardReplies(board.isPresent()?board.get():null, countPerPage, pageNum.isPresent()?pageNum.get():1);
            response.setData(replies);
            response.setMessage("댓글 조회 성공");
            response.setResponse("success");
        }catch(EmptyResultDataAccessException | NoSuchElementException e){
            response.setMessage("댓글 조회 실패");
            response.setResponse("fail");
        }
        return response;
    }
    @PostMapping("/{board}")
    public Response addReply(@PathVariable Optional<Integer> board, @RequestBody RequestNewReply requestNewReply){
        Response response = new Response();
        try{
            Board getBoard = boardService.findById(board.isPresent()?board.get():null);
            Reply reply = new Reply();
            reply.setBody(requestNewReply.getBody());
            reply.setUser_id(requestNewReply.getUser_id());
            reply.setBoard(getBoard);
            replyService.addReply(reply);
            response.setData(reply);
            response.setMessage("댓글 생성 성공");
            response.setResponse("success");
        }catch(EmptyResultDataAccessException | NoSuchElementException e){
            response.setMessage("댓글 생성 실패");
            response.setResponse("fail");
        }
        return response;
    }
    @PutMapping("/{reply}")
    public Response updateReply(@PathVariable Optional<Integer> reply, @RequestBody RequestUpdateReply requestupDateReply){
        Response response = new Response();
        try{
            Reply updatedReply = replyService.findById(reply.isPresent()?reply.get():null);
            updatedReply.setBody(requestupDateReply.getBody());
            updatedReply.setUpdated_at(new Date());
            replyService.updateReply(updatedReply);
            response.setMessage("댓글 수정 성공");
            response.setResponse("success");
        }catch(EmptyResultDataAccessException | NoSuchElementException e){
            response.setMessage("댓글 수정 실패");
            response.setResponse("fail");
        }
        return response;
    }
    @DeleteMapping("/{reply}")
    public Response deleteReply(@PathVariable Optional<Integer> reply){
        Response response = new Response();
        try{
            Reply deletedReply = replyService.findById(reply.isPresent()?reply.get():null);
            replyService.deleteReply(deletedReply);
            response.setMessage("댓글 삭제 성공");
            response.setResponse("success");
        }catch(EmptyResultDataAccessException | NoSuchElementException e){
            response.setMessage("댓글 삭제 실패");
            response.setResponse("fail");
        }
        return response;
    }
    @PutMapping("/recommend/{reply}")
    public Response addRecommend(@PathVariable Optional<Integer> reply){
        Response response = new Response();
        try{
            Reply updatedReply = replyService.findById(reply.isPresent()?reply.get():null);
            updatedReply.addRecommend();
            replyService.updateReply(updatedReply);
            response.setMessage("댓글 추천수 증가 성공");
            response.setResponse("success");
        }catch(EmptyResultDataAccessException | NoSuchElementException e){
            response.setMessage("댓글 추천수 증가 실패");
            response.setResponse("fail");
        }
        return response;
    }
    @PutMapping("/adopted/{reply}")
    public Response adoptedReply(@PathVariable Optional<Integer> reply){
        Response response = new Response();
        try{
            Reply updatedReply = replyService.findById(reply.isPresent()?reply.get():null);
            updatedReply.adopted();
            replyService.updateReply(updatedReply);
            response.setMessage("댓글 채택 성공");
            response.setResponse("success");
        }catch(EmptyResultDataAccessException | NoSuchElementException e){
            response.setMessage("댓글 채택 실패");
            response.setResponse("fail");
        }
        return response;
    }
    
}