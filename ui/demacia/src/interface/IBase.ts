export interface IBaseResponse<T> {
    data: T;
    errorMessage: string;
    errorCode: string;
}
