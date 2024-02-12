// tuto-list.component.ts
import { Component,OnInit } from '@angular/core';
import { Tutorial } from 'src/app/models/tutorial.models';
import { TutorialService } from 'src/app/services/tutorial.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-tutorials-list',
  templateUrl: './tuto-list.component.html',
  styleUrls: ['./tuto-list.component.css']
})
export class TutorialsListComponent implements OnInit {
    tutorialForm: FormGroup;
    tutorials?: Tutorial[];
    currentTutorial: Tutorial = {};
    currentIndex = -1;
    title = ''

    constructor(private tutorialService: TutorialService, private formBuilder: FormBuilder){
    this.tutorialForm = this.formBuilder.group({
          title: ['', [Validators.required, Validators.minLength(3)]]
    });
    }
    ngOnInit():void {
      this.retriveTutorials()
    }
    retriveTutorials(): void {
      this.tutorialService.getAll()
        .subscribe({
          next: (data) => {
            this.tutorials = data;
            console.log(data);
            },
            error: (e) => console.error(e)
            });
    }
    refreshList():void {
    this.retriveTutorials();
    this.currentTutorial = {}
    this.currentIndex = -1;
    }

    setActiveTutorial(tutorial: Tutorial,index : number):void {
    this.currentTutorial=tutorial;
    this.currentIndex=index;
    }

    removeAllTutorials():void {
    this.tutorialService.deleteall()
    .subscribe({
      next:(res) => {
        console.log(res);
        this.refreshList();
        },
        error: (e) => console.error(e)
        });
    }

    searchTitle(): void {
      this.currentTutorial = {};
      this.currentIndex = -1;

      this.tutorialService.findByTitle(this.title)
        .subscribe({
          next: (data) => {
            if (Array.isArray(data) && data.length > 0) {
              const tuto = data.find(tutorial => tutorial.title === this.title);

              if (tuto) {
                this.currentTutorial = tuto;
                console.log(this.currentTutorial);
              } else {
                console.log("Aucune donnée trouvée pour le titre spécifié.");
              }
            } else {
              console.log("Aucune donnée trouvée pour le titre spécifié.");
            }
          },
          error: (e) => console.error(e)
        });
    }

        }
