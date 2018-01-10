import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';
import {VideoGenService} from '../videogen/videogen.service';
import {AlternativesMedia, MandatoryMedia} from '../videogen/model/videogen.model';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.scss'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private videoGenService: VideoGenService
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
        /*console.log('LOADING MODEL');
        this.videoGenService.getModel('example1').subscribe((model) => {
            console.log(model);
        });*/
        var videos:string[] = [
            "data/input/video/jaunatan.mp4",
            "data/input/video/sheep.mp4"
        ];
        console.log("GENERATING PLAYLIST");
        this.videoGenService.generatePlaylist(videos).subscribe((response) => {
            console.log(response);
            console.log("PLAYLIST GENERATED");
        });
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    GoSelection() {
        // il faut aller sur une nouvelle page avec le choix des différentes vidéos (voir avec mael pour recuperer la liste des videos)
        // this.principal.hasAnyAuthority() ;

    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
