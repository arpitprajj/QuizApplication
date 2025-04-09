package com.Quiz.Application.controller;

import com.Quiz.Application.dto.QuestionDto;
import com.Quiz.Application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/hi")
    public String greet(){
        return  "hari bol";
    }
    @PostMapping
    ResponseEntity<QuestionDto>createQuestion(@RequestBody QuestionDto questionDto){
        QuestionDto questionDto1=this.questionService.createQuestion(questionDto);
        return  new ResponseEntity<>(questionDto1, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<QuestionDto>>getAllQuestion(){
        List<QuestionDto> dtoList=this.questionService.getAllQuestions();
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<QuestionDto>getQuestionById(@PathVariable Integer id){
        QuestionDto questionDto=this.questionService.getQuestionById(id);
        return new ResponseEntity<>(questionDto,HttpStatus.FOUND);

    }
    @PutMapping("{id}")
    ResponseEntity<QuestionDto>updateQuestion(@RequestBody QuestionDto questionDto,@PathVariable Integer id){
        QuestionDto questionDto1=this.questionService.updateQuestionById(questionDto,id);
        return  new ResponseEntity<>(questionDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    ResponseEntity<String>deleteQuestion(@PathVariable Integer id){
        this.questionService.deleteQuestion(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("search/{keyword}")
    ResponseEntity<List<QuestionDto>>searchQuestion(@PathVariable String keyword){
        List<QuestionDto>dtoList=this.questionService.searchQuestions(keyword);
        return new ResponseEntity<>(dtoList,HttpStatus.FOUND);
    }
    @GetMapping("getAnswer/{id}")
    ResponseEntity<Map<String,QuestionDto>>getAnswer(@PathVariable Integer id){
        Map<String,QuestionDto>map=new HashMap<>();
        QuestionDto questionDto=this.questionService.getQuestionById(id);
        String answer=this.questionService.getAnswerByQuestionId(id);
        map.put(answer,questionDto);
        return new ResponseEntity<>(map,HttpStatus.FOUND);
    }

}
