package board.board.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //JPA의 엔티티임을 나타냄
@Table(name="t_jpa_board")
@NoArgsConstructor
@Data
public class BoardEntity {
	@Id //엔티티의 기본키임을 나타냄
	@GeneratedValue(strategy=GenerationType.AUTO) //DB에서 제공하는 기본키생성 전략을 따르게 설정, MySQL이니 기본키가 자동으로 증가
	private int boardIdx;

	@Column(nullable=false) //컬럼에 Not Null 속성 지정
	private String title;
	
	@Column(nullable=false)
	private String contents;
	
	@Column(nullable=false)
	private int hitCnt = 0;
	
	@Column(nullable=false)
	private String creatorId;
	
	@Column(nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createdDatetime = LocalDateTime.now(); //작성시간의 초깃값 설정
	
	private String updaterId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime updatedDatetime;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL) // 1:N의 관계를 표현하는 JPA어노테이션, 게시글 하나에 첨부파일이 없거나 1개이상의 파일을 가질 수 있으니
	@JoinColumn(name="board_idx") //릴레이션 관계가 있는 테이블의 컬럼 지정
	private Collection<BoardFileEntity> fileList;
}
