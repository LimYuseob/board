package com.fullstack.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fullstack.board.service.FileService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Log4j2
@RequiredArgsConstructor
@Controller
public class FileUpController {

    //Service 호출.
    private final FileService fileService;

    @Value("${file.dir}")
    private String filePath;

    @GetMapping("/upload")
    public String tUploadForm() {

        return "/upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes)
            throws Exception{

        System.out.println("파일 업로드 요청됨..........................");

        fileService.saveFile(file);

        Long id = null;
        id = fileService.saveFile(file);

        Long ids[] = new Long[files.length];

        for(int i = 0; i < files.length; i++) {
            ids[i] = fileService.saveFile(files[i]);
        }

        redirectAttributes.addFlashAttribute("id", (ids.length!=0?ids:id));
        return "redirect:/upload/fileok";



//        for(MultipartFile multiFile : files) {
//            fileService.saveFile(multiFile);


    }
    @GetMapping("/upload/fileok")
    public void fileok() {
        log.info("업로드 완료");
    }

}