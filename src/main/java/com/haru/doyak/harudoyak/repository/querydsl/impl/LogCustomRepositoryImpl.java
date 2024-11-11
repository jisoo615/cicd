package com.haru.doyak.harudoyak.repository.querydsl.impl;

import com.haru.doyak.harudoyak.dto.log.ResLogDTO;
import com.haru.doyak.harudoyak.dto.log.ResLogDetailDTO;
import com.haru.doyak.harudoyak.repository.querydsl.LogCustomRepository;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.haru.doyak.harudoyak.entity.QFile.file;
import static com.haru.doyak.harudoyak.entity.QLetter.letter;
import static com.haru.doyak.harudoyak.entity.QLog.log;
import static com.haru.doyak.harudoyak.entity.QLogTag.logTag;
import static com.haru.doyak.harudoyak.entity.QMember.member;
import static com.haru.doyak.harudoyak.entity.QTag.tag;

@Repository
@RequiredArgsConstructor
public class LogCustomRepositoryImpl implements LogCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /*
    * 일간 도약기록 상세 조회
    * */
    @Override
    public List<ResLogDetailDTO> findLogByLogIdAndMemberId(Long logId, Long memberId){
        List<ResLogDetailDTO> resLogDetailDTOS = jpaQueryFactory
                .select(Projections.bean(
                        ResLogDetailDTO.class,
                        log.logId,
                        log.creationDate.as("logCreationDate"),
                        log.emotion,
                        log.content.as("logContent"),
                        file.filePathName.as("logImageUrl"),
                        member.aiNickname,

                        ExpressionUtils.as(
                                JPAExpressions.select(tag.name)
                                        .from(logTag)
                                        .join(tag).on(logTag.tag.tagId.eq(tag.tagId))
                                        .where(logTag.log.logId.eq(logId))
                                        .orderBy(tag.tagId.asc())
                                        .groupBy(log.logId),
                                "tagNames"
                        ),
                        JPAExpressions
                                .select(letter.content.coalesce("도약이 답변 내용이 없습니다.")
                                        .as("letterContent"))
                                .from(log)
                                .leftJoin(letter).on(log.logId.eq(letter.log.logId))
                                .where(log.logId.eq(logId)),

                        JPAExpressions
                                .select(letter.creationDate.coalesce(LocalDateTime.now())
                                        .as("letterCreationDate"))
                                .from(log)
                                .leftJoin(letter).on(log.logId.eq(letter.log.logId))
                                .where(log.logId.eq(logId))
                        // 도약이 편지가 null일시 CaseBuilder 사용해서 기본값 설정
                        /*new CaseBuilder()
                                .when(letter.content.isNotNull())
                                .then(letter.content)
                                .otherwise("도약이 답변 내용이 없습니다.")
                                .as("letterContent"),
                        new CaseBuilder()
                                .when(letter.arrivedDate.isNotNull())
                                .then(letter.arrivedDate)
                                .otherwise(LocalDateTime.now())
                                .as("letterCreationDate")*/
                ))
                .from(log)
                .join(member).on(log.member.memberId.eq(member.memberId))
                .leftJoin(file).on(log.file.fileId.eq(file.fileId))
                .where(member.memberId.eq(memberId).and(log.logId.eq(logId)))
                .fetch();

        /*List<TagDTO> tagDTOS = jpaQueryFactory
                .select(Projections.bean(
                        TagDTO.class,
                        tag.name.as("tagName")
                ))
                .from(logTag)
                .join(tag).on(logTag.tag.tagId.eq(tag.tagId))
                .where(logTag.log.logId.eq(logId))
                .fetch();

        resLogDetailDTOS.forEach(resLogDetailDTO -> {
            resLogDetailDTO.setTagNameList(tagDTOS);
        });*/

        return resLogDetailDTOS;
    }

    /*
    * 해당 회원이 작성한 도약기록 목록 조회
    * @param : meberId(Long)
    * */
    @Override
    public List<ResLogDTO> findLogAllByMemberId(Long memberId) {
        List<ResLogDTO> resLogDTOs = jpaQueryFactory
                .select(Projections.bean(ResLogDTO.class,
                        log.logId,
                        log.creationDate.as("logCreationDate")
                ))
                .from(log)
                .where(log.member.memberId.eq(memberId))
                .fetch();
        return resLogDTOs;
    }
}
