package com.Quiz.Application.controller;

import com.Quiz.Application.dto.QuestionDto;
import com.Quiz.Application.dto.QuizDto;
import com.Quiz.Application.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping
    ResponseEntity<QuizDto>createQuiz(@RequestBody QuizDto quizDto){
        QuizDto dto=this.quizService.createQuiz(quizDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
    ResponseEntity<List<QuizDto>>getAllQuiz(){
        List<QuizDto>dtoList=this.quizService.getAllQuiz();
        return new ResponseEntity<>(dtoList,HttpStatus.FOUND);
    }
    @GetMapping("{id}")
    ResponseEntity<QuizDto>getQuizById(@PathVariable Integer id){
        QuizDto dto=this.quizService.getQuizbyId(id);
        return new ResponseEntity<>(dto,HttpStatus.FOUND);
    }
    @DeleteMapping("{id}")
    ResponseEntity<String>deleteQuizById(@PathVariable Integer id){
        this.quizService.deleteQuizById(id);
        return new ResponseEntity<>("Sucessfully deleted",HttpStatus.OK);
    }
    @PutMapping("{id}")
    ResponseEntity<QuizDto>updateQuiz(@PathVariable Integer id,@RequestBody QuizDto quizDto) {
        QuizDto quizDto1 = this.quizService.updateQuizById(id, quizDto);

        return  new ResponseEntity<>(quizDto1,HttpStatus.CREATED);

    }
    @PostMapping("/random")
    ResponseEntity<QuizDto>createQuizRandom(@RequestBody Map<String,Object> randObj){
        Integer numQ=(Integer)randObj.get("numQ");
        String title=(String)randObj.get("title") ;
        QuizDto quizDto=this.quizService.createQuizRandom(numQ,title);
        return new ResponseEntity<>(quizDto,HttpStatus.CREATED);
    }

    @PostMapping("/addQuestions/{quizId}")
    ResponseEntity<QuizDto>addQuestions(@PathVariable Integer quizId, @RequestBody QuestionDto questionDto){
        QuizDto quizDto=this.quizService.addQuestion(quizId,questionDto);
        return new ResponseEntity<>(quizDto,HttpStatus.CREATED);
    }
}
