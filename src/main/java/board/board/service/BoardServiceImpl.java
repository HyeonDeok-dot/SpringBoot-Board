package board.board.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardDto;
import board.board.dto.BoardFileDto;
import board.board.mapper.BoardMapper;
import board.common.FileUtils;

@Service //비즈니스 로직을 처리하는 서비스 클래스임을 나타내는 어노테이션
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList(); //게시글목록 조회
	}
	
	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
//		boardMapper.insertBoard(board); //게시글 번호를 이용하기위해 먼저 게시글 등록
//		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest); //업로드된 파일을 서버에 저장하고 파일정보 가져옴
//		if(CollectionUtils.isEmpty(list) == false){
//			boardMapper.insertBoardFileList(list);		 //파일의 정보를 맵에 저장	
//		}
	}
	
	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		BoardDto board = boardMapper.selectBoardDetail(boardIdx); //게시글 조회
		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);
		
		boardMapper.updateHitCount(boardIdx); //조회수 증가
		
		return board;
	}
	
	@Override
	public BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception {
		return boardMapper.selectBoardFileInformation(idx, boardIdx); //파일 정보 조회
	}
	
	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board); //게시글 수정
	}
	
	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx); //게시글 삭제
	}
}	

