import { Injectable } from '@angular/core';
import { Http, Response, ResponseContentType, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { VideoGen, VideoGeneratorModel } from './video-gen.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class VideoGenService {

    private resourceUrl = SERVER_API_URL + 'api/video-gens';
    private videoUrlShare: string;
    videoGenModel: VideoGeneratorModel;

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
            console.log(jsonResponse);
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

    getModel(filename: string): Observable<VideoGeneratorModel> {
        return this.http.get(`${this.resourceUrl}/${filename}`).map((res) => res.json());
    }

    generatePlaylist(videos: string[]): any {
        return this.http.post(this.resourceUrl, videos)
            .map((res: Response) => res);
    }

    getVideoGenFiles(): any {
        return this.http.get(this.resourceUrl + '/files').map((res: Response) => res.json());
    }

    getFile(): any {
      return this.http.get(this.resourceUrl + '/video-gens/file/data/input/v0.mp4').map((res: Response) => res.json());
    }

    getVideoUrlShare() {
      return this.videoUrlShare;
    }

    setVideoUrlShare(videoUrl: string) {
      this.videoUrlShare = videoUrl;
    }

    getRandomPlayList() {
      return this.http.get(this.resourceUrl + '/playlist/random').map((res: Response) => (res.text()));
    }

    getRandomModel(): Observable<VideoGeneratorModel> {
      return this.http.get(this.resourceUrl + '/model/random').map((res: Response) => {
        console.log('JSONReal ' + res.text());

        console.log('JSONRealJ ' + res.json());

        

        /*this.videoGenModel.medias.forEach((m) => {
          console.log(m.thumbUrl)
        });*/

          return res.json();
      });
    }

    setVideogeneratorModel(videoGenModel: VideoGeneratorModel) {
      this.videoGenModel = videoGenModel;
    }

    getVideoGeneratorModel() {
      return this.videoGenModel;
    }

    /**
     * Convert a returned JSON object to VideoGen.
     */
    private convertVideoGenFromServer(json: any): VideoGeneratorModel {
      console.log('JSON ' + json)
        const entity: VideoGeneratorModel = Object.assign(new VideoGeneratorModel(), json);
        return entity;
    }
}
