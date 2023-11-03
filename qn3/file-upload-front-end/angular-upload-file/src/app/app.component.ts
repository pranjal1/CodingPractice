import { UploadService  } from './upload-file.service';
import { Component } from '@angular/core';
 
@Component({
 selector: 'app-root',
 templateUrl: './app.component.html',
 styleUrls: ['./app.component.css']
})
export class AppComponent {
  resultText: String = ''; // Variable to hold the result
  file: File | null = null;

 
  constructor(private uploadService: UploadService) {
    
  }
 
  onFileChange(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      this.file = inputElement.files[0];
    }
  }

  upload() {
    if (this.file !== null) {
      this.uploadService.uploadfile(this.file).subscribe({
        next: (response) => {
          this.resultText = response;
          console.log(this.resultText);
        },
        error: (error) => {
          console.error('Error uploading file:', error);
        }
      });
    } else {
      console.error('File is null. Please select a file before uploading.');
    }
  }
}