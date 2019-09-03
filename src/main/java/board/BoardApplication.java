package board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EntityScan(
		basePackageClasses = {Jsr310JpaConverters.class},  //MySQL의 버전에 따라서 시간 관련 클래스가 문제가 생길 수 있는점이 있어 개선을 위해 적용 
		basePackages = {"board"}
		)
@SpringBootApplication(exclude= {MultipartAutoConfiguration.class}) //자동으로 구성되는 첨부파일 관련 설정을 사용하지 않게 함 
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
