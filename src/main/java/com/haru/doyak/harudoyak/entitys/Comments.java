package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comments {
    // 댓글 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;        // 댓글 아이디

    @ManyToOne
    @JoinColumn(name = "shareDoyakId")
    private ShareDoyak shareDoyak;// 서로도약 아이디(외래키)

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;// 회원 아이디(외래키)

    @NotNull
    private Long parentCommentId;     // 상위댓글 아이디

    @NotNull
    private String content;     // 댓글 내용

    @CreationTimestamp
    private LocalDateTime creationDate;         // 댓글 등록일

    @NotNull
    private Boolean isRemoved;  // 댓글 삭제여부

//    private Boolean isRead; // 알림읽기 여부

    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
    @PrePersist
    public void prePersist() {
        if (this.isRemoved == null) {
            this.isRemoved = false;
        }
    }

}
