package com.fullstack.board.service;

import com.fullstack.board.entity.FileEntity;
import com.fullstack.board.entity.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;

    public Long saveFile(MultipartFile file) throws Exception{
        if(file.isEmpty()) {
            return null;
        }
        /*
         * MultipartFile : 업로드가 요청된 파일의 모든 정보를 담고 있는 객체입니다.
         * 서비스 층에서 multipart 요청시 객체를 메서드의 파라미터로 주입했기에,
         * 모든 정보값들을 이용할 수 있습니다.
         * 쉽게 생각하시면, 여러분이 IO 수행시 File 객체등의 getter, setter 등을
         * 이 객체를 이용해서 하시면 됩니다.
         * 또한, 같은 이름의 파일이 중복되서 넘어오면 덮어쓰기 때문에 자바 API 중
         * UUID(util package)의 randomUUID() 등을 이용해서 들어오는 요청파일마다
         * 고유한 ID(파일명)으로 변경하면 중복된 파일이 삭제될 일이 없어집니다.
         */
        //1.요청한 원래 파일명 추출
        String orginName = file.getOriginalFilename();
        //2.UUID 를 이용해서 파일 이름을 재작성 합니다.
        String uuid = UUID.randomUUID().toString();
        //3.확장자 추출
        String extension = orginName.substring(orginName.lastIndexOf("."));
        //4.UID 와 확장자 결함.
        String savedName = uuid + extension;
        //5.파일을 불러올 때 사용할 패스
        String savedPath = fileDir + savedName;

        //DB에 저장할 File Entity 생성.
        FileEntity fileEntity = FileEntity.builder()
                .orgNm(orginName)
                .savedNm(savedName)
                .savedPath(savedPath)
                .build();

        //로컬 경로에 UUID 를 파일명으로 저장작업.
        file.transferTo(new File(savedPath));

        //DB 에 파일정보 저장.
        FileEntity savedFileEntity = fileRepository.save(fileEntity);

        return savedFileEntity.getId();
    }
}