package com.haru.doyak.harudoyak.domains.log;

import com.haru.doyak.harudoyak.dto.file.FileDTO;
import com.haru.doyak.harudoyak.dto.log.ReqLogDTO;
import com.haru.doyak.harudoyak.dto.log.ResLogDTO;
import com.haru.doyak.harudoyak.entitys.*;
import com.haru.doyak.harudoyak.repository.FileRepository;
import com.haru.doyak.harudoyak.repository.LogRepository;
import com.haru.doyak.harudoyak.repository.MemberRepository;
import com.haru.doyak.harudoyak.util.S3FileManager;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {
    private final LogRepository logRepository;
    private final EntityManager entityManager;
    private final MemberRepository memberRepository;
    private final S3FileManager s3FileManager;
    private final FileRepository fileRepository;


    /*
     * 도약 기록 목록
     * req : memberId(Long)
     * res : logId(Long), creationDate(Date)
     * */
    public List<ResLogDTO> getLogList(Long memberId){

        List<ResLogDTO> resLogDTOS = logRepository.findLogAllByMemberId(memberId);

        for (ResLogDTO resLogDTO : resLogDTOS) {
            log.info("resLogDTO: {}", resLogDTO.getCreationDate());
            log.info("resLogDTO: {}", resLogDTO.getLogId());
        }

        return resLogDTOS;
    }

    /*
     * 도약 기록 작성 (에러 처리 해야함)
     * req : memberId(Long), logImage(MultipartFile),
     *       content(String), tagName(String []), emotion(String)
     * res : 200 ok 400 등
     * */
    @Transactional
    public void setLogAdd(ReqLogDTO reqLogDTO, Long memberId, MultipartFile logImage) {

        // 도약기록 insert 전 회원 존재하는지 isExists 확인
         boolean isExistsMember = memberRepository.existsMemberByMemberId(memberId);

         // 회원이 존재한다면
         if (isExistsMember){

             FileDTO fileDTO = new FileDTO();
             /*
             * S3파일 업로드
             *
             * return : filePathName(urlPath+fileName),
             *          fileOriginalName(업로드시 fileName)
             * */
             try {
                 fileDTO = s3FileManager.saveImageFile(logImage);
                 log.info("=======================");
                 log.info("LogService filePathName {}", fileDTO.getFilePathName());
                 log.info("LogService fileOriginalName {}", fileDTO.getOriginalName());
             } catch (Exception e) {
                 throw new RuntimeException(e);
             }

             /*
             * DB에 파일 정보 insert
             * values : filePathName, originalName
             * */
             File file = File.builder()
                     .filePathName(fileDTO.getFilePathName())
                     .originalName(fileDTO.getOriginalName())
                     .build();
             entityManager.persist(file);

             File selectFile = fileRepository.findByfilePathName(fileDTO.getFilePathName());
             Member selectByMember = memberRepository.findMemberByMemberId(memberId);

           // 도약기록 insert
             Log log = Log.builder()
                     .member(selectByMember)
                     .file(selectFile)
                     .content(reqLogDTO.getContent())
                     .emotion(reqLogDTO.getEmotion())
                     .build();
             entityManager.persist(log);

             /*
             * 태그 insert
             * values :
             * */
             for(String tagName : reqLogDTO.getTagName()){
                 Tag tag = Tag.builder()
                         .name(tagName)
                         .build();
                 entityManager.persist(tag);
                 setTag(log, tag);
             }


         }

         // 회원이 존재하지 않다면

    }

    /*
    * 로그&태그 저장
    * req : log(Long), tag(Long)
    * */
    @Transactional
    public void setTag(Log log, Tag tag){
        LogTag logTag = LogTag.builder()
                .logTagId(new LogTagId(log.getLogId(), tag.getTagId()))
                .log(log)
                .tag(tag)
                .build();
        entityManager.persist(logTag);
    }

}
