import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {

  constructor(private http: HttpClient) {}

  selectedFiles!: FileList;
  isUploading = false;

  onFileChange(event: any) {
    this.selectedFiles = event.target.files;
  }

  uploadFiles() {
    this.isUploading = true;

    const formData = new FormData();
    Array.from(this.selectedFiles).forEach(file => formData.append('files', file));

    this.http.post('http://localhost:8080/upload', formData).subscribe(() => {
      setTimeout(() => {
        this.isUploading = false;
      }, 5000);
    });

  }

}
