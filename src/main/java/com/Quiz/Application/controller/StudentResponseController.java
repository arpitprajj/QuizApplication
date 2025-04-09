package com.Quiz.Application.controller;

import com.Quiz.Application.dto.StudentResponseDto;
import com.Quiz.Application.service.StudentResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/response")
public class StudentResponseController {

    @Autowired
    StudentResponseService responseService;

    @PostMapping("{stdId}/{quizId}/{questId}")
    ResponseEntity<StudentResponseDto>createResponse(@PathVariable Integer stdId, @PathVariable Integer quizId, @PathVariable Integer questId, @RequestBody String selOption){
        StudentResponseDto responseDto=this.responseService.createResponse(stdId,quizId,questId,selOption);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
