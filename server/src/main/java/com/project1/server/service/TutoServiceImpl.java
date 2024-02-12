package com.project1.server.service;

import com.project1.server.model.Tutorial;
import com.project1.server.repositry.TutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutoServiceImpl implements TutoService {
    @Autowired
    TutoRepo tutoRepo;


    @Override
    public List<Tutorial> getAllTutorials() {
        return tutoRepo.findAll();
    }

    @Override
    public Tutorial getTutorialById(Long id) {
        return tutoRepo.findById(id).orElse(null);
    }

    @Override
    public Tutorial createTutorial(Tutorial tutorial) {
        return tutoRepo.save(tutorial);
    }

    @Override
    public Tutorial updateTutorial(Long id, Tutorial tutorial) {
        Tutorial tuto = null;
        Optional<Tutorial> optionalTutorial = tutoRepo.findById(id);
        if (optionalTutorial.isPresent()) {
            tuto = optionalTutorial.get();
            tuto.setTitle(tutorial.getTitle());
            tuto.setDescription(tutorial.getDescription());
            tuto.setPublished(tutorial.isPublished());
            tutoRepo.save(tuto);
        } else {
            return new Tutorial();
        }
        return tuto;
    }

    @Override
    public String deleteTutorial(Long id) {
        tutoRepo.deleteById(id);
        return "Tutoriel deleted";
    }

    @Override
    public String deleteAllTutorials() {
        tutoRepo.deleteAll();
        return "all Tutorials are deleted";
    }

    @Override
    public List<Tutorial> findByPublished() {
        return tutoRepo.findByPublished(true);
    }
    @Override
    public List<Tutorial> findByTitleContaining( String title ) {
        if (title == null)
            return   tutoRepo.findAll();
        return tutoRepo.findByTitleContaining(title) ;
    }



    }



