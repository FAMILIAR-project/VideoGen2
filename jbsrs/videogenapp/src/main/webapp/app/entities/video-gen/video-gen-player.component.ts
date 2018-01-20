import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

import { VideoGenService } from './';

@Component({
  selector: 'jhi-video-gen-player',
  templateUrl: './video-gen-player.component.html',
  styles: []
})
export class VideoGenPlayerComponent implements OnInit {

  media: any;
  videourl: string;
  modes_: string[] = ['Aléatoire', 'Configurateur'];
  selectedMode_: string;
  constructor(private videoGenService: VideoGenService, private router: Router) { }

  ngOnInit() {

    this.videourl = this.videoGenService.getVideoUrlShare();
  }

  validate() {

    console.log('Mode' + this.selectedMode_ );
    if (this.selectedMode_ === this.modes_[0]) {
      this.videoGenService.getRandomPlayList().subscribe((response: string) => {
          this.videoGenService.setVideoUrlShare(response);
          this.router.navigate(['video-gen-player']);
      });
    } else {
      this.router.navigate(['video-gen-configurator']);
    }
  }

  getGifs() {
    /* let url = window.URL.createObjectURL('data/output/gifs/playlist_14.gif')
    window.open( url ) */

    this.videoGenService.getGifs(this.videourl).subscribe((res: any) => {
      console.log('Res ' + res.text())
      /* let url = window.URL.createObjectURL(res.text());
      window.open(url); */
    });
  }
}
