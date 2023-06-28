package com.site.bemystory.controller;

import com.site.bemystory.domain.Diary;
import com.site.bemystory.domain.StoryBook;
import com.site.bemystory.service.DiaryService;
import com.site.bemystory.service.StoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiaryController {
    private final DiaryService diaryService;
    private final StoryBookService storyBookService;

    @Autowired
    public DiaryController(DiaryService diaryService, StoryBookService storyBookService) {
        this.diaryService = diaryService;
        this.storyBookService = storyBookService;
    }

    /**
     * 일기 저장하고 fastapi로 넘겨줌
     * return을 뭘로 해야할까? 일단 StoryBook으로 함
     *
     */
    @ResponseBody   //json 형식으로 response 보낸다
    @PostMapping("/diary-form")
    public Diary create(@RequestBody DiaryForm diaryForm){
        //DB 저장
        System.out.println(diaryForm.getSubject());
        Diary diary = new Diary();
        diary.setDate(diaryForm.getDate());
        diary.setSubject(diaryForm.getSubject());
        diary.setContents(diaryForm.getContents());
        diary.setStory_type(diaryForm.getStoryType());
        diaryService.save(diary);
        return diary;
        /*
        //fastapi로 넘기기
        StoryBook storyBook = diaryService.passToAI(diary);
        //동화책 저장
        storyBookService.saveBook(storyBook);
        return storyBook;


         */

    }
}
