import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { Router } from '@angular/router';

import { Http, Response, ResponseContentType, RequestOptions } from '@angular/http';

import { Account, LoginModalService, Principal } from '../shared';
import { VideoGenService, VideoGeneratorModelWrapper } from '../entities/video-gen/';

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
    modes: string[] = ['Aléatoire', 'Configurateur'];
    selectedMode: string;
    videoGeneratorModelWrapper: VideoGeneratorModelWrapper;
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
      this.selectedMode = 'Aléatoire'
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
      console.log('Selected ' + this.selectedMode)
      if (this.selectedMode === this.modes[0]) {
        this.videoGenService.getRandomPlayList().subscribe((response: string) => {
            this.videoGenService.setVideoUrlShare(response)
            console.log('Response ' + response)
            if (this.videoGenService.getVideoUrlShare !== null) {
            //  this.router.navigate(['video-gen-player'])
            }
        });

      } else {
        this.router.navigate(['video-gen-configurator'])
     }
  }
}
