package board.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.entity.BoardEntity;
import board.board.entity.BoardFileEntity;
import board.board.repository.JpaBoardRepository;
import board.common.FileUtils;

@Service
public class JpaBoardServiceImpl implements JpaBoardService{
	
	@Autowired
	JpaBoardRepository jpaBoardRepository;
	
	@Autowired
	FileUtils fileUtils;

	@Override
	public List<BoardEntity> selectBoardList() throws Exception {
		return jpaBoardRepository.findAllByOrderByBoardIdxDesc(); //게시글 번호로 정렬, 전체 게시글 목록을 조회
	}

	@Override
	public void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		board.setCreatorId("admin");
		List<BoardFileEntity> list = fileUtils.parseFileInfo(multipartHttpServletRequest); //정보저장 클래스가 BoardFileDto -> BoardFileEntity로 변경되었기에 메서드 추가
		if(CollectionUtils.isEmpty(list) == false){
			board.setFileList(list);
		}
		jpaBoardRepository.save(board); //저장할 내용이 새로 생성되었으면 insert, 기존의 내용에서 변경되었을 경우 update를 수행하는 save메서드
	}
	
	@Override
	public BoardEntity selectBoardDetail(int boardIdx) throws Exception{
		Optional<BoardEntity> optional = jpaBoardRepository.findById(boardIdx); //Optional 클래스는 절대로 Null이 될 수가 없음
		if(optional.isPresent()){ //isPresent메서느는 객체의 값이 존재한다면 true를 반환, .get으로 객체의 값을 가져올 수 있음
			BoardEntity board = optional.get();
			board.setHitCnt(board.getHitCnt() + 1);
			jpaBoardRepository.save(board);
			
			return board;
		}
		else {
			throw new NullPointerException();
		}
	}

	@Override
	public void deleteBoard(int boardIdx) {
		jpaBoardRepository.deleteById(boardIdx);
	}

	@Override
	public BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception {
		BoardFileEntity boardFile = jpaBoardRepository.findBoardFile(boardIdx, idx);
		return boardFile;
	}
}
