export interface FileReqDto {
    username: string;
    filePath: string;
  }
  
  export interface FileResDto {
    message: string;
    success: boolean;
  }
  
  export interface GetFileReqDto {
    email: string;
  }
  
  export interface GetFileResDto {
    ids: number[]; 
    names: string[]
  }
  
  