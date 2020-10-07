package codeholic.repository;

import org.springframework.data.repository.CrudRepository;

import codeholic.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Integer> {
    
}