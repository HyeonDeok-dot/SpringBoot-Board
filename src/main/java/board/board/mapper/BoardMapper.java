package board.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.board.dto.BoardDto;

@Mapper //마이바티스의 매퍼 인터페이스임을 나타내는 인터페이스 
public interface BoardMapper {
	List<BoardDto> selectBoardList() throws Exception; //게시판 목록
	
	void insertBoard(BoardDto board) throws Exception; //게시글 작성
	
	void updateHitCount(int boardIdx) throws Exception; //조회수 증가
	
	BoardDto selectBoardDetail(int boardIdx) throws Exception; //게시글 조회
	
	void updateBoard(BoardDto board) throws Exception; //게시글 수정
	
	void deleteBoard(int boardIdx) throws Exception; //게시글 삭제
	
	
}
