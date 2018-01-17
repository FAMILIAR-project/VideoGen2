import { BaseEntity } from './../../shared';

export class VideoGen implements BaseEntity {
    constructor(
        public id?: number,
        public author?: string,
        public date?: string,
        public version?: string
    ) {
    }
}

export class VideoGeneratorModel {
    constructor(
        public medias?: Media[],
        public information?: VideoGenInformation
    ) {}
}

export class VideoGenInformation {
    constructor(
        public authorName?: string,
        public version?: string,
        public creationDate?: string
    ) {}
}

export class Media {
    constructor(
        public type?: string,
        public descriptionWrapper?: MediaDescriptionWrapper,
        public descriptionWrappers?: MediaDescriptionWrapper[],
        public id?: number
    ) {}
}

export class MediaDescriptionWrapper{
  constructor(
    public thumb_url?: string,
    public description?: MediaDescription
  ){}
}

export class MediaDescription {
    constructor(
        public location?: string,
        // Video
        public videoid?: number,
        public duration?: number,
        public probability?: number,
        public description?: string,
        public filter?: any,
        public FilterWrapped?: FilterWrapped,
        // TODO Filter + VideoText

        // Image
      //  public toptext?: string,
      //  public bottomtext?: string
    ) {}
}

export class FilterWrapped{
  constructor(){}
}
