export interface UserRegisReqDto {
    userName: string;
    userEmail: string;
    password: string;
    createdById: number;
    roleId: number;
  }
  
  export interface UserRegisResDto {
    userId?: number;  
    message: string;
  }
  