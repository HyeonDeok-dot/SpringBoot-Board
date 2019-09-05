package board.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardDto;
import board.board.dto.BoardFileDto;

public interface BoardService {
	
	List<BoardDto> selectBoardList() throws Exception; //게시글 목록
	
//	void insertBoard(BoardDto board) throws Exception; //기존의 첨부파일  없는 게시글 작성
	void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception; //게시글 작성에 첨부파일기능 추가
	
	BoardDto selectBoardDetail(int boardIdx) throws Exception; //게시글 상세보기
	
	void updateBoard(BoardDto board) throws Exception; //게시글 수정
	
	void deleteBoard(int boardIdx) throws Exception; //게시글 삭제
	
	BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception;
	
}
