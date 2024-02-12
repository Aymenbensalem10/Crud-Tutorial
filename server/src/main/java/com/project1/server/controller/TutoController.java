package com.project1.server.controller;

import com.project1.server.model.Tutorial;
import com.project1.server.service.TutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TutoController {
    @Autowired
    TutoService tutoService;
    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(){
            List<Tutorial> tutorials = tutoService.getAllTutorials();
            return new  ResponseEntity<>(tutorials, HttpStatus.OK);
        }
        @GetMapping("/tutorials/{id}")
    public Tutorial getTutorialById(@PathVariable Long id){
        Tutorial tutorialData = tutoService.getTutorialById(id);
        return tutorialData;
    }
        @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
            Tutorial tutorial1 = tutoService.createTutorial(tutorial);
            return new ResponseEntity<>(tutorial1,HttpStatus.CREATED);
        }
        @PutMapping("/tutorials/{id}")
    public Tutorial updateTutorial(@PathVariable Long id, @RequestBody Tutorial tutorial){
        Tutorial  tuto = tutoService.updateTutorial(id,tutorial);
        return tuto;
        }
        @DeleteMapping("/tutorials/{id}")
    public String deleteTutorial(@PathVariable Long id){
        return tutoService.deleteTutorial(id);
        }
        @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials(){
        try {
            tutoService.deleteAllTutorials();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }
        @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished(){
        try {
            List<Tutorial> tutorials = tutoService.findByPublished();
            if (tutorials.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }
        @GetMapping("/tutorials/title")
        public List<Tutorial> findByTitleContaining(String title){
            return tutoService.findByTitleContaining(title);
        }


}



