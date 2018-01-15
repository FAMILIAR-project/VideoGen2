import { Component, OnInit } from '@angular/core';

import { VideoGenService } from './';

@Component({
  selector: 'jhi-video-gen-player',
  templateUrl: './video-gen-player.component.html',
  styles: []
})
export class VideoGenPlayerComponent implements OnInit {

  media : any;
  videourl : string;
  modes = ['Aléatoire', 'Configurateur'];
  selectedMode = null;
  constructor(private videoGenService: VideoGenService) { }

  ngOnInit() {

    this.videourl = this.videoGenService.getVideoUrlShare();
    console.log('Video Url' + this.videourl);
  }

}
