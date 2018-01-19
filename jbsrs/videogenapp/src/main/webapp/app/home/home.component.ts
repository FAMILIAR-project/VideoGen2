import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { Router } from '@angular/router';

import { Http, Response, ResponseContentType, RequestOptions } from '@angular/http';

import { Account, LoginModalService, Principal } from '../shared';
import { VideoGenService, VideoGeneratorModel } from '../entities/video-gen/';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;

    guestname: string;
    modes = ['Aléatoire', 'Configurateur'];
    selectedMode = null;
    videoGenModel: VideoGeneratorModel;
    thumbs: any = [];

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private router: Router,
        private videoGenService: VideoGenService
    ) {
    }

    ngOnInit() {

    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    validate() {
      this.videoGenService.getRandomModel().subscribe((model: VideoGeneratorModel) => {
        console.log('Response' + model.medias)

        model.medias.forEach((m) => {
          if(m.type.charAt(0) == 'a') {
            m.descriptionWrappers.forEach((a) => {
              console.log(a.thumb_url)
            })
          } else {
            console.log(m.descriptionWrapper.thumb_url)
          }
          });
        // this.videoGenModel = model;

        //this.videoGenService.setVideogeneratorModel(response);
        //this.router.navigate(['video-gen-configurator']);
     });
      /*console.log('Mode' + this.selectedMode);
      if (this.selectedMode = 'Aléatoire') {
        this.videoGenService.getRandomPlayList().subscribe((response) => {
            console.log('Response get' + response)
            this.videoGenService.setVideoUrlShare(response);
            this.router.navigate(['video-gen-player']);
        });
      } else {
        this.videoGenService.getRandomModel().subscribe((response) => {
          console.log('Response' + response)
          this.router.navigate(['video-gen-configurator']);
       });
     }*/
  }
}
