package com.haru.doyak.harudoyak.domains.sharedoyak;

import com.haru.doyak.harudoyak.dto.file.FileDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ReqCommentDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ReqShareDoyakDTO;
import com.haru.doyak.harudoyak.entitys.*;
import com.haru.doyak.harudoyak.repository.FileRepository;
import com.haru.doyak.harudoyak.repository.MemberRepository;
import com.haru.doyak.harudoyak.repository.ShareDoyakRepository;
import com.haru.doyak.harudoyak.util.S3FileManager;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class ShareDoyakService {
    private final ShareDoyakRepository shareDoyakRepository;
    private final EntityManager entityManager;
    private final MemberRepository memberRepository;
    private final S3FileManager s3FileManager;
    private final FileRepository fileRepository;

    /*
     * 댓글 작성
     * req : memberId(Long), shareDoyakId(Long), commentContent(String)
     * res :
     * */
    @Transactional
    public void setCommentAdd(Long memberId, Long shareDoyakId, ReqCommentDTO reqCommentDTO){
        // 회원아이디와 서로도약아이디 null체크
        if(memberId == 0 && shareDoyakId == 0){

        }

        // 회원 존재 여부 확인
        boolean isExistsMember = memberRepository.existsByMemberId(memberId);


        // 서로도약 존재 여부 확인
        boolean isExistsShareDoyak = shareDoyakRepository.existsById(shareDoyakId);

        if(isExistsMember && isExistsShareDoyak){

            // 댓글 insert


        }



    }

    /*
     * 도약하기 추가
     * req : memberId(Long), shareDoyakId(Long)
     * res : doyakCount(Long)
     * */
    @Transactional
    public Long setDoyakAdd(Long memberId, Long shareDoyakId) {

        // 도약 테이블에 memberId 존재 여부
        boolean isExistsDoyak = shareDoyakRepository.existsByMemberId(memberId);

        //
        if (isExistsDoyak) {
            log.info("해당 아이디가 있는 도약이 있니?");
            shareDoyakRepository.deleteDoyakByMemberId(memberId);
            Long doyakCount = shareDoyakRepository.findDoyakAllCount();
            return doyakCount;
        }

        // 회원 존재 여부 확인
        boolean isExistsMember = memberRepository.existsByMemberId(memberId);
        boolean isExistsShareDoyak = shareDoyakRepository.existsByshareDoyakId(shareDoyakId);

        // 회원이 존재 한다면
        if(isExistsMember){
            if(isExistsShareDoyak){

                Member selectMember = memberRepository.findMemberByMemberId(memberId);
                ShareDoyak selectShareDoyak = shareDoyakRepository.findShareDoyakByShareDoyakId(shareDoyakId);

                log.info("=============================================shareDoyakService");
                log.info("memberId {}", selectMember.getMemberId());
                log.info("shareDoyakId {}", selectShareDoyak.getShareDoyakId());

                Doyak doyak = Doyak.builder()
                        .doyakId(new DoyakId(
                                selectShareDoyak.getShareDoyakId(),
                                selectMember.getMemberId()
                                )
                        )
                        .member(selectMember)
                        .shareDoyak(selectShareDoyak)
                        .build();
                entityManager.persist(doyak);
            }
        }
        // 해당 게시글의 총 도약수 select
        Long doyakCount = shareDoyakRepository.findDoyakAllCount();
        log.info("===============해당 게시글의 총 도약수 ");
        return doyakCount;

    }

    /*
     * 서로도약 작성
     * req : memberId(Long), shareContent(String), shareImage(MultipartFile)
     * res :
     * */
    @Transactional
    public void setShareDoyakAdd(Long memberId, ReqShareDoyakDTO reqShareDoyakDTO, MultipartFile shareImage){

        // 회원 존재 여부 확인
        boolean isExistsMember = memberRepository.existsByMemberId(memberId);

        // 회원이 존재 한다면
        if(isExistsMember){

            FileDTO fileDTO = new FileDTO();

            // 이미지 S3 업로드
            try {
               fileDTO = s3FileManager.saveImageFile(shareImage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // 파일 DB insert
            File file = File.builder()
                    .filePathName(fileDTO.getFilePathName())
                    .originalName(fileDTO.getOriginalName())
                    .build();
            entityManager.persist(file);

            File selectFile = fileRepository.findByfilePathName(fileDTO.getFilePathName());
            Member selectMember = memberRepository.findMemberByMemberId(memberId);

            // 서로도약 insert
            ShareDoyak shareDoyak = ShareDoyak.builder()
                    .member(selectMember)
                    .content(reqShareDoyakDTO.getShareContent())
                    .file(selectFile)
                    .build();
            entityManager.persist(shareDoyak);
        }


    }

}
