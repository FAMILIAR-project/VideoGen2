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

export class VideoGeneratorModelWrapper {
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

export abstract class MediaDescriptionWrapper {
  constructor(
    public thumb_url?: string,
    public description?: MediaDescription,
    public selected?: boolean
  ) {}
}

export class MediaDescription {
    constructor(
        public location?: string,
        public videoid?: number,
        public duration?: number,
        public probability?: number,
        public description?: string,
        public toptext?: string,
        public bottomtext?: string
    ) {}
}

export class VideoDescriptionWrapper extends MediaDescriptionWrapper {
  constructor(
    public thumbs_url?: string,
    public description?: MediaDescription,
    public selected?: boolean,
    public filterWrapped?: FilterWrapper
  ) {
    super(thumbs_url, description, selected)
  }
}

export class ImageDescriptionWrapper extends MediaDescriptionWrapper {
  constructor(
    public thumbs_url?: string,
    public description?: MediaDescription,
    public selected?: boolean
  ) {
    super(thumbs_url, description, selected)
  }
}

export class FilterWrapper {
  constructor() {}
}

export class FilterNegateWrapper extends FilterWrapper {
  constructor() {
    super()
  }
}

export class BlackAndWhiteFilterWrapper extends FilterWrapper {
  constructor() {
    super()
  }
}

export class FlipFilterWrapper extends FilterWrapper {
  constructor() {
    super()
  }
}
