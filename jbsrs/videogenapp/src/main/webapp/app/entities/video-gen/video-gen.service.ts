import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { VideoGen } from './video-gen.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class VideoGenService {

    private resourceUrl = SERVER_API_URL + 'api/video-gens';

    constructor(private http: Http) { }

    create(videoGen: VideoGen): Observable<VideoGen> {
        const copy = this.convert(videoGen);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(videoGen: VideoGen): Observable<VideoGen> {
        const copy = this.convert(videoGen);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<VideoGen> {
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
     * Convert a returned JSON object to VideoGen.
     */
    private convertItemFromServer(json: any): VideoGen {
        const entity: VideoGen = Object.assign(new VideoGen(), json);
        return entity;
    }

    /**
     * Convert a VideoGen to a JSON which can be sent to the server.
     */
    private convert(videoGen: VideoGen): VideoGen {
        const copy: VideoGen = Object.assign({}, videoGen);
        return copy;
    }
}
