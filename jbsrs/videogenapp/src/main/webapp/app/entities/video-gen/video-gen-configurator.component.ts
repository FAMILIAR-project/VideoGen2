import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

import { VideoGenService, VideoGeneratorModelWrapper, MediaDescriptionWrapper } from './';

@Component({
  selector: 'jhi-video-gen-configurator',
  templateUrl: './video-gen-configurator.component.html',
  styles: []
})
export class VideoGenConfiguratorComponent implements OnInit {

  videoGen: string;
  listFiles: string[];
  thumbs: any[] = [];
  choices: MediaDescriptionWrapper[] = [];

  urlschooses: string[] = [];

  videoGeneratorModelWrapper: VideoGeneratorModelWrapper = new VideoGeneratorModelWrapper();

  constructor(private videoGenService: VideoGenService, private router: Router) { }

  ngOnInit() {

    this.videoGenService.getRandomModel().subscribe((model: VideoGeneratorModelWrapper) => {
      console.log('Response' + model.medias)
      this.videoGeneratorModelWrapper = model;
   });

  }
  
  getCustomPlaylist() {

    this.videoGeneratorModelWrapper.medias.forEach((m) => {
      if (m.type.charAt(0) === 'a') {
        m.descriptionWrappers.forEach((a) => {
          if (a.selected === true) {
            this.choices.push(a)
            return true
          }
        })
      } else {
        if (m.descriptionWrapper.selected === true) {
          this.choices.push(m.descriptionWrapper)
        }
      }
    });

    this.choices.forEach((c) => {
      this.urlschooses.push(c.description.location)
    });

    this.videoGenService.getConfigurePlaylist(this.urlschooses).subscribe((res: any) => {
      this.videoGenService.setVideoUrlShare(res)
      this.router.navigate(['video-gen-player'])
    });
  }

  selectMedia(media: any, type: string) {
    if (type === 'av' || type === 'ai') {
        media.selected = !media.selected
      } else {
        media.descriptionWrapper.selected = !media.descriptionWrapper.selected
      }
    }
}
