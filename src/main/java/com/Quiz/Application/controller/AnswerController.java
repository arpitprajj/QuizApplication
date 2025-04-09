package com.Quiz.Application.controller;

import com.Quiz.Application.dto.AnswerDto;
import com.Quiz.Application.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping("{questionId}")
    ResponseEntity<AnswerDto>createAnswer(@PathVariable Integer questionId, @RequestBody AnswerDto answerDto){
        AnswerDto dto=this.answerService.createAnswer(questionId,answerDto);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    ResponseEntity<AnswerDto>getAnswer(@PathVariable Integer id){
        AnswerDto dto=this.answerService.getAnswerById(id);
        return new ResponseEntity<>(dto,HttpStatus.FOUND);
    }
    @PutMapping("{id}")
    ResponseEntity<AnswerDto>updateAnswer(@PathVariable Integer id,@RequestBody AnswerDto answerDto){

        AnswerDto dto=this.answerService.updateAnswerById(id,answerDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<String>deleteAnswer(@PathVariable Integer id){
        this.answerService.deleteAnswerById(id);
        return new ResponseEntity<>("Answer Deleted Sucessfully",HttpStatus.OK);
    }

}
