import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Video } from './video.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class VideoService {

    private resourceUrl = SERVER_API_URL + 'api/videos';

    constructor(private http: Http) { }

    create(video: Video): Observable<Video> {
        const copy = this.convert(video);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(video: Video): Observable<Video> {
        const copy = this.convert(video);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Video> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Video.
     */
    private convertItemFromServer(json: any): Video {
        const entity: Video = Object.assign(new Video(), json);
        return entity;
    }

    /**
     * Convert a Video to a JSON which can be sent to the server.
     */
    private convert(video: Video): Video {
        const copy: Video = Object.assign({}, video);
        return copy;
    }
}
