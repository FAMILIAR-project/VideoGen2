import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { SERVER_API_URL } from '../app.constants';

@Injectable()
export class VideoGenService {
    private resourceUrl = SERVER_API_URL + 'api/videogen';

    constructor(private http: Http) { }

    find(filename: string): Observable<Object> {
        return this.http.get(`${this.resourceUrl}/${filename}`).map((res: Response) => res.json());
    }

}
