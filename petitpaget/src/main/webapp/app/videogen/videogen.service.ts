import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { SERVER_API_URL } from '../app.constants';
import {VideoGeneratorModel} from './model/videogen.model';
import {ResponseWrapper} from '../shared/model/response-wrapper.model';

@Injectable()
export class VideoGenService {
    private resourceUrl = SERVER_API_URL + 'api/videogen';

    constructor(private http: Http) { }

    getModel(filename: string): Observable<VideoGeneratorModel> {
        return this.http.get(`${this.resourceUrl}/${filename}`).map((res: Response) => res.json());
    }

    generatePlaylist(videos: string[]): any {
        return this.http.post(this.resourceUrl, videos)
            .map((res: Response) => res);
    }

    getVideoGenFiles(): any{
        return this.http.get(this.resourceUrl+ '/files').map((res: Response) => res.json());
    }

    /*private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }*/
}
