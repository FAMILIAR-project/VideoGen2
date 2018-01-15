import { Component, OnInit } from '@angular/core';

import { VideoGenService, VideoGeneratorModel } from './';

@Component({
  selector: 'jhi-video-gen-configurator',
  templateUrl: './video-gen-configurator.component.html',
  styles: []
})
export class VideoGenConfiguratorComponent implements OnInit {

  videoGen: string;
  listFiles: string[];
  thumbs: string[] = [];
  videoGenModel: VideoGeneratorModel;

  constructor(private videoGenService: VideoGenService) { }

  ngOnInit() {
    this.videoGenService.getRandomVariant().subscribe(response => {
      console.log(response);
      response.forEach(thumb => {
        this.thumbs.push(thumb);
      })
    });
  }

  getFiles(){
        this.videoGenService.getVideoGenFiles().subscribe((files) => {
            console.log("GetVideoGenFiles : files");
            console.log(files);
            this.listFiles = files ;
        });
    }


}
