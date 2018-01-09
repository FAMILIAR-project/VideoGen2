export class VideoGeneratorModel {
    constructor(
        public medias: Media[],
        public information?: VideoGenInformation
    ){}
}

export class VideoGenInformation {
    constructor(
        public authorName: string,
        public version?: string,
        public creationDate?: string
    ){}
}

export class Media {
}

export class MandatoryMedia extends Media {
    constructor(
      public description: MediaDescription,
    ){
        super();
    }
}

export class OptionalMedia extends Media {
    constructor(
        public description: MediaDescription,
    ){
        super();
    }
}

export class AlternativesMedia extends Media {
    constructor(
        public medias: MediaDescription[],
        public id?: number
    ){
        super();
    }
}

export class MediaDescription {
}

export class VideoDescription extends MediaDescription {
    constructor(
        public location: string,
        public videoid?: number,
        public duration?: number,
        public probability?: number,
        public description?: string
        //TODO Filter + VideoText
    ){
        super();
    }
}

export class ImageDescription extends MediaDescription {
    constructor(
        public location: string,
        public toptext?: string,
        public bottomtext?: string
    ){
        super();
    }
}
