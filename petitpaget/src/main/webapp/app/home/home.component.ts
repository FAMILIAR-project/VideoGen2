import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';
import {VideoGenService} from '../videogen/videogen.service';
import {VideoGeneratorModel} from '../videogen/model/videogen.model';
import {isNullOrUndefined} from "util";

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
    loadingString: string;
    videoLocation: string;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private videoGenService: VideoGenService
    ) {
    }

    getFiles(){
        this.loadingString = "Loading files";
        this.videoGenService.getVideoGenFiles().subscribe((files) => {
            console.log("GetVideoGenFiles : files");
            console.log(files);
            this.listFiles = files ;
            this.loadingString = null;
        });
    }

    loadModel(){
        console.log(this.videoGen);
        this.loadingString = "Loading Model";
        this.videoGenService.getModel(this.videoGen).subscribe((model) => {
            console.log(model);
            this.videoGenModel = model;
            this.loadingString = null;
            //this.videoLocation = "data/output/output_1516027553770.mp4";
        });
    }

    generateRandomVariant(){
        this.loadingString = "Generating Random Variant";
        this.videoGenService.generateRandomVariant(this.videoGen).subscribe((randomVariant) => {
            console.log(randomVariant._body);
            this.videoLocation = randomVariant._body;
            this.loadingString = null;
        });
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
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
        var bool = this.principal.isAuthenticated();
        if(bool && isNullOrUndefined(this.listFiles)){
            this.getFiles();
        }
        return bool;
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
