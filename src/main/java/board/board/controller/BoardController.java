package board.board.controller;

import board.board.dto.BoardDto;
import board.board.service.BoardService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller 
public class BoardController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass()); //클래스 객체를 넘기면 "패키지이름+클래스이름"으로 인스턴스를 반환하게 로거 생성
	
	@Autowired
	private BoardService boardService;
	
//	게시판 글목록
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
		log.debug("openBoardList"); //debug레벨의 로그를 출력
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
//	게시글 작성 화면 호출
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		return "/board/boardWrite";
	}
	
//	게시글 등록
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board) throws Exception {
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
//	게시글 상세보기
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
//	게시글 수정	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	
//	게시글 삭제
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}

}
