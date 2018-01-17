import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { Router } from '@angular/router';

import { Http, Response, ResponseContentType, RequestOptions } from '@angular/http';

import { Account, LoginModalService, Principal } from '../shared';
import { VideoGenService } from '../entities/video-gen/';

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
    modes: string[] = ['Aléatoire','Configurateur'];
    selectedMode: string;
    mode: string;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private router: Router,
        private videoGenService: VideoGenService
    ) {
    }

    ngOnInit() {
        /*this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();*/
        this.selectedMode = '';
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

    validate(){
      console.log("Mode Sele" + this.selectedMode);
      if(this.selectedMode = "Aléatoire"){
        this.videoGenService.getRandomPlayList().subscribe((response) => {
            console.log("Response get "+response)
            this.videoGenService.setVideoUrlShare(response);
            this.router.navigate(['video-gen-player']);
        });
      }
      else{
        this.router.navigate(['video-gen-configurator']);
      }
    }

    onChange($event: any){
      console.log("Mode " + $event);
    }
}