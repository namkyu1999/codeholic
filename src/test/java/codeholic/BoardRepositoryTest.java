package codeholic;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import codeholic.domain.Board;
import codeholic.domain.Reply;
import codeholic.domain.Tag;
import codeholic.repository.BoardRepository;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    

    @Test
    @Transactional
    public void insertBoardDummies() {
        List<Tag> tags = new ArrayList<Tag>();
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();
        tag1.setTag("web");
        tag2.setTag("backend");
        tags.add(tag1);
        tags.add(tag2);

        Reply reply1 = new Reply();
        reply1.setUser_id("lak9348@gmail.com");
        reply1.setBody("reply body1");
        
        Reply reply2 = new Reply();
        reply2.setUser_id("lak93@naver.com");
        reply2.setBody("reply body2");

        List<Reply> replies = new ArrayList<Reply>();
        
        Board board = new Board();
        board.setBody("test body");
        board.setTags(tags);
        board.setReplies(replies);
        board.setTitle("title 1");
        board.setUser_id("admin id");
        
        boardRepository.save(board);
        log.info("test1 finished");
    }
}