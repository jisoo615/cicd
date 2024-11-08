package com.haru.doyak.harudoyak.repository.querydsl.impl;

import com.haru.doyak.harudoyak.dto.sharedoyak.ResCommentDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ResReplyCommentDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ResShareDoyakDTO;
import com.haru.doyak.harudoyak.repository.querydsl.ShareDoyakCustomRepository;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.haru.doyak.harudoyak.entity.QComment.comment;
import static com.haru.doyak.harudoyak.entity.QDoyak.doyak;
import static com.haru.doyak.harudoyak.entity.QFile.file;
import static com.haru.doyak.harudoyak.entity.QMember.member;
import static com.haru.doyak.harudoyak.entity.QShareDoyak.shareDoyak;

@Repository
@RequiredArgsConstructor
public class ShareDoyakCustomRepositoryImpl implements ShareDoyakCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ResReplyCommentDTO> findeCommentAll(Long shareDoyakId) {
        List<ResReplyCommentDTO> comments = jpaQueryFactory
                .select(Projections.bean(
                        ResReplyCommentDTO.class,
                        comment.shareDoyak.shareDoyakId.as("commentShareDoyakId"),
                        comment.commentId,
                        comment.content.as("commentContent"),
                        member.nickname.as("commentAuthorNickname"),

                        // 서브쿼리
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(comment.count())
                                        .from(comment)
                                        .where(comment.parentCommentId.eq(comment.commentId)),
                        "replyCommentCount")

                        // 좀 더 공부해야 함
                       /* ExpressionUtils.as(
                                JPAExpressions
                                        .select(comment.commentId)
                                        .from(comment)
                                        .where(comment.parentCommentId.eq(comment.commentId))
                                        .fetch()
                                        .stream()
                                        .collect(Collectors.toList()),

                        "replyCommentIds")*/

                ))
                .from(comment)
                .leftJoin(member).on(comment.member.memberId.eq(member.memberId))
                .where(comment.parentCommentId.isNull(), comment.shareDoyak.shareDoyakId.eq(shareDoyakId))
                .orderBy(comment.creationDate.desc())
                .fetch();
        return comments;
    }

    @Override
    public List<ResShareDoyakDTO> findeAll() {

        // 서로도약의 데이터 목록을 select
        List<ResShareDoyakDTO> resShareDoyakDTOS = jpaQueryFactory
                .select(Projections.bean(ResShareDoyakDTO.class,
                                member.nickname.as("shareAuthorNickname"),
                                member.goalName,
                                shareDoyak.shareDoyakId,
                                shareDoyak.content.as("shareContent"),
                                file.filePathName.as("shareImageUrl"),
                                comment.commentId.countDistinct().as("commentCount"),
                                doyak.shareDoyak.shareDoyakId.countDistinct().as("doyakCount")
                        )
                )
                .from(shareDoyak)
                .join(member).on(shareDoyak.member.memberId.eq(member.memberId))
                .join(file).on(shareDoyak.file.fileId.eq(file.fileId))
                .leftJoin(comment).on(comment.shareDoyak.shareDoyakId.eq(shareDoyak.shareDoyakId))
                .leftJoin(doyak).on(doyak.shareDoyak.shareDoyakId.eq(shareDoyak.shareDoyakId))
                .groupBy(shareDoyak.shareDoyakId)
                .orderBy(shareDoyak.creationDate.desc())
                .fetch();

        // 댓글의 데이터 목록을 select
        List<ResCommentDTO> resCommentDTOS = jpaQueryFactory
                .select(Projections.bean(ResCommentDTO.class,
                        comment.shareDoyak.shareDoyakId.as("commentShareDoyakId"),
                        comment.commentId,
                        comment.content.as("commentContent"),
                        comment.member.nickname.as("commentAuthorNickname")
                ))
                .from(comment)
                .join(member).on(comment.member.memberId.eq(member.memberId))
                .fetch();

        // 게시글 Id를 기준으로 댓글을 그룹화하여 매핑
        return resShareDoyakDTOS.stream()
                        .peek(resShareDoyakDTO -> resShareDoyakDTO.setResComments(
                                resCommentDTOS.stream()
                                        .filter(comment -> comment.getCommentShareDoyakId().equals(resShareDoyakDTO.getShareDoyakId()))
                                        .collect(Collectors.toList())
                        ))
                        .collect(Collectors.toList());
    }
}
