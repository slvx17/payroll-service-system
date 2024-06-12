import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { FileReqDto, FileResDto, GetFileReqDto, GetFileResDto } from '../dto/file/files.dto';

@Injectable({
  providedIn: 'root'
})

export class FileService {
  private apiUrl = 'http://localhost:8080/document';  

  constructor(private http: HttpClient) { }

  uploadFile(file: File, email: string, dateId: number): Observable<FileResDto> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('username', email);
    formData.append('dateId', dateId.toString());  
  
    return this.http.post<FileResDto>(`${this.apiUrl}/upload`, formData, {
      reportProgress: true,
      observe: 'body'
    }).pipe(
      catchError(this.errorHandler)
    );
  }
  
  

  getDocuments(email: string): Observable<GetFileResDto> {
    const getFileReqDto: GetFileReqDto = { email };
    return this.http.post<GetFileResDto>(`${this.apiUrl}/getdocuments`, getFileReqDto).pipe(
      catchError(this.errorHandler)
    );
  }

  downloadFile(fileReqDto: FileReqDto): Observable<FileResDto> {
    return this.http.post<FileResDto>(`${this.apiUrl}/download`, fileReqDto).pipe(
      catchError(this.errorHandler)
    );
  }

  private errorHandler(error: any) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Status: ${error.status}\nMessage: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}
