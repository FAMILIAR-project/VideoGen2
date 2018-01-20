import { Injectable } from '@angular/core';

import {SERVER_API_URL} from "../app.constants";
import {Http} from "@angular/http";

@Injectable()
export class EditorService {

    constructor(
        private http: Http,
    ) {}

    upload(videogenSourceCode: String) {

        return new Promise((resolve, reject) => {
            this.http.post(SERVER_API_URL + 'api/videogen-editor/test', videogenSourceCode).subscribe((data) => {
                resolve();
            }, (err) => {
                reject(err);
            })
        });
    }
}
