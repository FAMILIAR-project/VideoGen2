import { BaseEntity } from './../../shared';

export class VideoGen implements BaseEntity {
    constructor(
        public id?: number,
        public author?: string,
        public date?: string,
        public version?: string,
    ) {
    }
}
