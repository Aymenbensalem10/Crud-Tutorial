import { Component } from '@angular/core';
import { Tutorial } from 'src/app/models/tutorial.models';
import { TutorialService } from 'src/app/services/tutorial.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-tutorial',
  templateUrl: './add-tuto.component.html',
  styleUrls: ['./add-tuto.component.css']
})
export class AddTutorialComponent {
  tutorialForm: FormGroup;
  tutorial: Tutorial = {
    title: '',
    description: '',
    published: false
  };
  submitted = false;

  constructor(private tutorialService: TutorialService, private formBuilder: FormBuilder) {
    this.tutorialForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', Validators.required]
    });
  }

  saveTutorial(): void {
    if (this.tutorialForm.valid) {
      const data = {
        title: this.tutorialForm.value.title,
        description: this.tutorialForm.value.description
      };

      this.tutorialService.create(data)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.submitted = true;
          },
          error: (e) => console.error(e)
        });
    }
  }

  newTutorial(): void {
    this.submitted = false;
    this.tutorialForm.reset();
  }
}
