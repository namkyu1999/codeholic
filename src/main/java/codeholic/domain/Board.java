package codeholic.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "board")
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String user_id;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "본문은 필수 입력 값입니다.")
    private String body;

    @OneToMany(mappedBy = "board", fetch= FetchType.LAZY)
    @NotBlank(message = "태그는 필수 입력 값입니다.")
    private List<Tag> tags;

    @NotBlank(message = "조회수는 필수 입력 값입니다.")
    private int view = 0;

    @NotBlank(message = "추천수는 필수 입력 값입니다.")
    private int recommend = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created_at;


    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch= FetchType.LAZY)
    private List<Reply> replies;
}