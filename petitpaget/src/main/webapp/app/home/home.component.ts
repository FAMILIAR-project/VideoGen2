import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';
import {VideoGenService} from '../videogen/videogen.service';
import {VideoGeneratorModel} from '../videogen/model/videogen.model';

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
    videoGen: string;
    listFiles: string[];
    videoGenModel: VideoGeneratorModel;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private videoGenService: VideoGenService
    ) {
    }

    getFiles(){
        this.videoGenService.getVideoGenFiles().subscribe((files) => {
            console.log("GetVideoGenFiles : files");
            console.log(files);
            this.listFiles = files ;
        });

    }

    test(){
        console.log(this.videoGen);
        /*this.videoGenService.getModel(this.videoGen.split(".")[0]).subscribe((model) => {
            console.log(model);
            this.videoGenModel = model;
        });*/
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();

        /*
        let videos:string[] = [
            "data/input/video/jaunatan.mp4",
            "data/input/video/sheep.mp4"
        ];
        console.log("GENERATING PLAYLIST");
        this.videoGenService.generatePlaylist(videos).subscribe((response) => {
            console.log(response);
            console.log("PLAYLIST GENERATED");
        });
        */
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
        this.getFiles();
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
