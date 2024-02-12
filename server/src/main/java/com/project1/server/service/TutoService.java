package com.project1.server.service;

import com.project1.server.model.Tutorial;

import java.util.List;

public interface TutoService {

    List<Tutorial> getAllTutorials();

    Tutorial getTutorialById(Long id);

    Tutorial createTutorial(Tutorial tutorial);

    Tutorial updateTutorial(Long id, Tutorial tutorial);

    String deleteTutorial(Long id);

    String deleteAllTutorials();

    List<Tutorial> findByPublished();

    List<Tutorial> findByTitleContaining(String title);
}
