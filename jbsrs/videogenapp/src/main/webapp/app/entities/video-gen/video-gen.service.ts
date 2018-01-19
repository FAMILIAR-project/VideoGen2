import { Injectable } from '@angular/core';
import { Http, Response, ResponseContentType, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { VideoGen, VideoGeneratorModelWrapper, MediaDescriptionWrapper } from './video-gen.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class VideoGenService {

    private resourceUrl = SERVER_API_URL + 'api/video-gens';
    private videoUrlShare: string;
    videoGeneratorModelWrapper: VideoGeneratorModelWrapper;

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

    getModel(filename: string): Observable<VideoGeneratorModelWrapper> {
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

    setVideoUrlShare(videoUrl: any) {
      this.videoUrlShare = videoUrl;
    }

    getRandomPlayList(): Observable<any> {
      return this.http.get(this.resourceUrl + '/playlist/random').map((res: Response) => {
        return res.text();
      });
    }

    getRandomModel(): Observable<VideoGeneratorModelWrapper> {
      return this.http.get(this.resourceUrl + '/model/random').map((res: Response) => {
          return res.json();
      });
    }

    getConfigurePlaylist(choices: string[]): Observable<any> {
      return this.http.post(this.resourceUrl + '/playlist/configure/', choices).map((res: Response) => {
        return res.text();
      });
    }

    getGifs(playlist: string): Observable<any> {
      return this.http.get(`${this.resourceUrl}/playlist/gifs/${playlist}`).map((res: Response) => {
        const blob = new Blob(['http://localhost:8080'+ '/data/output/playlists/playlist_18.mp4'], {
         type: 'application/octet-stream' // must match the Accept type
       });

       window.location.href = 'data/output/playlists/playlist_18.mp4';


        const url = window.URL.createObjectURL(blob);
        window.open('http://localhost:8080'+ '/data/output/playlists/playlist_18.mp4');

        return res
      });
    }

    setVideogeneratorModel(videoGeneratorModelWrapper: VideoGeneratorModelWrapper) {
      this.videoGeneratorModelWrapper = videoGeneratorModelWrapper
    }

    getVideoGeneratorModel() {
      return this.videoGeneratorModelWrapper
    }

    /**
     * Convert a returned JSON object to VideoGen.
     */
    private convertVideoGenFromServer(json: any): VideoGeneratorModelWrapper {
      console.log('JSON ' + json)
        const entity: VideoGeneratorModelWrapper = Object.assign(new VideoGeneratorModelWrapper(), json);
        return entity;
    }

    private convertVideoGen(choices: MediaDescriptionWrapper[]): MediaDescriptionWrapper[] {
        const copy: MediaDescriptionWrapper[] = Object.assign({}, choices);
        return copy;
    }
}
