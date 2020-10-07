package codeholic.repository;

import org.springframework.data.repository.CrudRepository;

import codeholic.domain.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Integer> {
    
}