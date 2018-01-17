import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';
import {VideoGenService} from '../videogen/videogen.service';
import {Media, VideoGeneratorModel} from '../videogen/model/videogen.model';
import {isNullOrUndefined} from 'util';

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
    playlist: string[];

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private videoGenService: VideoGenService
    ) {
    }

    getFiles() {
        this.loadingString = 'Loading files';
        this.videoGenService.getVideoGenFiles().subscribe((files) => {
            this.listFiles = files ;
            this.loadingString = null;
        });
    }

    loadModel() {
        console.log(this.videoGen);
        this.loadingString = 'Loading Model';
        this.videoGenService.getModel(this.videoGen).subscribe((model) => {
            this.videoGenModel = model;
            this.loadingString = null;
            console.log(this.videoGenModel);
        });
    }

    generateRandomVariant() {
        this.loadingString = 'Generating Random Variant';
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
        if (isNullOrUndefined(this.listFiles)) {
            this.getFiles();
        }
    }

    isAuthenticated() {
        const bool = this.principal.isAuthenticated();
        if (bool && isNullOrUndefined(this.listFiles)) {
            this.getFiles();
        }
        return bool;
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    test() {
        console.log(this.videoGenModel.medias);
    }

    checkAndUncheck(videoId: string, media: Media) {
        if (media.type === 'av') {
            for (const desc of media.medias) {
                if (desc.videoId === videoId) {
                    desc.selected = true;
                }else {
                    desc.selected = false;
                }
            }
        }
    }

    generateVideo() {
        this.playlist = [];
        for (const media of this.videoGenModel.medias) {
            if (media.type === 'mv') {
                this.playlist.push(media.description.location);
            }else if (media.type === 'ov' && media.description.selected) {
                this.playlist.push(media.description.location);
            }else if (media.type === 'av') {
                for (const desc of media.medias) {
                    if (desc.selected) {
                        this.playlist.push(desc.location);
                        break;
                    }
                }
            }
        }
        console.log(this.playlist);
        this.loadingString = 'Generating Video'
        this.videoGenService.generatePlaylist(this.playlist).subscribe((video) => {
            console.log(video._body);
            this.videoLocation = video._body;
            this.loadingString = null;
        });
    }
}
