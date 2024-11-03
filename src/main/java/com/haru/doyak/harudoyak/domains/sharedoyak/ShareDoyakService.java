package com.haru.doyak.harudoyak.domains.sharedoyak;

import com.haru.doyak.harudoyak.dto.file.FileDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ReqShareDoyakDTO;
import com.haru.doyak.harudoyak.entitys.File;
import com.haru.doyak.harudoyak.entitys.Member;
import com.haru.doyak.harudoyak.entitys.ShareDoyak;
import com.haru.doyak.harudoyak.repository.FileRepository;
import com.haru.doyak.harudoyak.repository.MemberRepository;
import com.haru.doyak.harudoyak.repository.ShareDoyakRepository;
import com.haru.doyak.harudoyak.util.S3FileManager;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ShareDoyakService {
    private final ShareDoyakRepository shareDoyakRepository;
    private final EntityManager entityManager;
    private final MemberRepository memberRepository;
    private final S3FileManager s3FileManager;
    private final FileRepository fileRepository;

    /*
     * 서로도약 작성
     * req : memberId(Long), content(String), shareImage(MultipartFile)
     * res :
     * */
    @Transactional
    public void setShareDoyakAdd(Long memberId, ReqShareDoyakDTO reqShareDoyakDTO, MultipartFile shareImage){

        // 회원 확인
        boolean isExistsMember = memberRepository.existsMemberByMemberId(memberId);

        // 회원이 있다면
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
                    .content(reqShareDoyakDTO.getContent())
                    .file(selectFile)
                    .build();
            entityManager.persist(shareDoyak);
        }


    }

}
