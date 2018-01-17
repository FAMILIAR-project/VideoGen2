import { Component, OnInit} from '@angular/core';

import { VideoGenService, VideoGeneratorModel } from './';

@Component({
  selector: 'jhi-video-gen-configurator',
  templateUrl: './video-gen-configurator.component.html',
  styles: []
})
export class VideoGenConfiguratorComponent implements OnInit {

  videoGen: string;
  listFiles: string[];
  thumbs: any[] = [];

  videoGenModel: VideoGeneratorModel = new VideoGeneratorModel();

  constructor(private videoGenService: VideoGenService) { }

  ngOnInit() {

    this.videoGenService.getRandomModel().subscribe((model: VideoGeneratorModel) => {
      console.log('Response' + model.medias)
      this.videoGenModel = model;
      model.medias.forEach((m) => {
        if(m.type.charAt(0) == 'a') {
          m.descriptionWrappers.forEach((a) => {
            console.log(a.thumb_url)
            this.thumbs.push({
              videourl: a.thumb_url,
              selected: false
            });
          })
        } else {
          console.log(m.descriptionWrapper.thumb_url)
          this.thumbs.push({
            videourl: m.descriptionWrapper.thumb_url,
            selected: false
          });
        }
        });
      // this.videoGenModel = model;

      //this.videoGenService.setVideogeneratorModel(response);
      //this.router.navigate(['video-gen-configurator']);
   });

  }

  getFiles() {
        this.videoGenService.getVideoGenFiles().subscribe((files) => {
            console.log('GetVideoGenFiles : file');
            console.log(files);
            this.listFiles = files ;
        });
    }
  selecte(thumb: any) {

  }
}
