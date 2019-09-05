package board.board.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="BoardDto : 게시글 내용", description = "게시글 내용") //ApiModel  어노테이션으로 모델에 설명 추가
@Data //롬복을 활용하여 getter, setter를 자동으로 만들어주게 함
public class BoardDto {
	
	@ApiModelProperty(value="게시글 번호") //ApiModelProperty 어노테이션으로 모델의 요소 설명 추가
	private int boardIdx;
	
	@ApiModelProperty(value="게시글 제목")
	private String title;
	
	@ApiModelProperty(value="게시글 내용")
	private String contents;
	
	@ApiModelProperty(value="조회수")
	private int hitCnt;
	
	@ApiModelProperty(value="작성자 아이디")
	private String creatorId;
	
	@ApiModelProperty(value="작성시간")
	private String createdDatetime;
	
	@ApiModelProperty(value="수정자 아이디")
	private String updaterId;
	
	@ApiModelProperty(value="수정시간")
	private String updatedDatetime;
	
	@ApiModelProperty(value="첨부파일 목록")
	private List<BoardFileDto> fileList;
}
