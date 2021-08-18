export interface AbstractDTO {
    id?: number;
    creationTimestamp?: Date;
    creationUser?: number;
    updateTimestamp?: Date;
    updateUser?: string;
    version?: string;
}