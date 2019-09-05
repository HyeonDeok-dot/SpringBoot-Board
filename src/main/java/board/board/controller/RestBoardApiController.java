package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import board.board.dto.BoardDto;
import board.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "게시판 REST API") //API어노테이션으로 스웨거에 설명 추가
@RestController //@Controller 와 @ResponseBody 어노테이션을 합친 어노테이션, 응답결과를 Web response body를 이용하여 보내줌
public class RestBoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@ApiOperation(value = "게시글 목록 조회")
	@RequestMapping(value="/api/board", method=RequestMethod.GET)
	public List<BoardDto> openBoardList() throws Exception{
		return boardService.selectBoardList();
	}
	
	@ApiOperation(value = "게시글 작성")
	@RequestMapping(value="/api/board/write", method=RequestMethod.POST)
	public void insertBoard(@RequestBody BoardDto board) throws Exception{ //POST나 PUT을 사용하는 메서드에는 @RequestBody 어노테이션 사용
		boardService.insertBoard(board, null);
	}
	
	@ApiOperation(value = "게시글 상세 내용 조회")
	@RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.GET)
	public BoardDto openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception{
		
		return boardService.selectBoardDetail(boardIdx);
	}
	
	@ApiOperation(value = "게시글 상세 내용 수정")
	@RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.PUT)
	public String updateBoard(@RequestBody BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board";
	}
	
	@ApiOperation(value = "게시글 삭제")
	@RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("boardIdx")
		@ApiParam(value="게시글 번호") int boardIdx) throws Exception{ //ApiParam 어노테이션으로 API의 파라미터에 설명 추가
		boardService.deleteBoard(boardIdx);
		return "redirect:/board";
	}
}
