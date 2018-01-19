import { BaseEntity } from './../../shared';

export class Video implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
    ) {
    }
}
