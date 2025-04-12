package com.Quiz.Application.controller;

import com.Quiz.Application.dto.StudentResponseDto;
import com.Quiz.Application.entity.StudentResponse;
import com.Quiz.Application.service.StudentResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/response")
public class StudentResponseController {

    @Autowired
    StudentResponseService responseService;

    @PostMapping("{stdId}/{quizId}/{questId}")
    ResponseEntity<StudentResponseDto>createResponse(@PathVariable Integer stdId, @PathVariable Integer quizId, @PathVariable Integer questId, @RequestBody String selOption){
        String clean= selOption.trim()
                .replaceAll("[\\r\\n\"\\\\]", "");
        StudentResponseDto responseDto=this.responseService.createResponse(stdId,quizId,questId,clean);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping
    ResponseEntity<List<StudentResponse>>getAllResponse(){
        List<StudentResponse>studentResponses=this.responseService.getResponses();
        return new ResponseEntity<>(studentResponses,HttpStatus.FOUND);

    }
}
