package board.board.dto;

import java.util.List;

import lombok.Data;

//롬복을 활용하여 getter, setter를 자동으로 만들어주게 함
@Data
public class BoardDto {
	
	private int boardIdx;
	
	private String title;
	
	private String contents;
	
	private int hitCnt;
	
	private String creatorId;
	
	private String createdDatetime;
	
	private String updaterId;
	
	private String updatedDatetime;
	
	private List<BoardFileDto> fileList;
}
