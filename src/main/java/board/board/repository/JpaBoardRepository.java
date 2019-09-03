package board.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import board.board.entity.BoardEntity;
import board.board.entity.BoardFileEntity;

public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer>{ //CrudRepository인터페이스는 도메인 클래스와 도메인의 id타입을 파라미터로 받음

	List<BoardEntity> findAllByOrderByBoardIdxDesc(); //게시글 번호로 정렬해서 전체 게시글 조회
	
	@Query("SELECT file FROM BoardFileEntity file WHERE board_idx = :boardIdx AND idx = :idx") //첨부파일의 정보 조회, @Query로 실행하고 싶은 쿼리를 직접 정의할 수 있음
	BoardFileEntity findBoardFile(@Param("boardIdx") int boardIdx, @Param("idx") int idx);
}
