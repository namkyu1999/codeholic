package codeholic.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "reply")
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String user_id;

    // 0이 미채택, 1이 채택
    @NotBlank(message = "채택여부는 필수 입력 값입니다.")
    private int adopted = 0;

    @NotBlank(message = "추천수는 필수 입력 값입니다.")
    private int recommned = 0;

    @NotBlank(message = "본문은 필수 입력 값입니다.")
    private String body;
    

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created_at;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    private Board board;
}