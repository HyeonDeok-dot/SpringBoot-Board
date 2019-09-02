package board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;

@Service //비즈니스 로직을 처리하는 서비스 클래스임을 나타내는 어노테이션
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList(); //게시글목록 조회
	}
	
	@Override
	public void insertBoard(BoardDto board) throws Exception {
		boardMapper.insertBoard(board); //게시글 등록
	}
	
	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		boardMapper.updateHitCount(boardIdx); //조회수 증가
		
		BoardDto board = boardMapper.selectBoardDetail(boardIdx); //게시글 조회
		
		return board;
	}
}	

