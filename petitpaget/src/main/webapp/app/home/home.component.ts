import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';

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
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
<<<<<<< Updated upstream
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
=======
>>>>>>> Stashed changes
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
}
