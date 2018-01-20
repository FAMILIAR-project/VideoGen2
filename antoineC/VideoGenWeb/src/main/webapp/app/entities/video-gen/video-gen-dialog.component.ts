import {Component, OnInit, OnDestroy, ViewChild, ElementRef} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VideoGen } from './video-gen.model';
import { VideoGenPopupService } from './video-gen-popup.service';
import { VideoGenService } from './video-gen.service';
import { User, UserService } from '../../shared';
import { ResponseWrapper } from '../../shared';
import {Principal} from "../../shared/auth/principal.service";
import {AccountService} from "../../shared/auth/account.service";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
    selector: 'jhi-video-gen-dialog',
    templateUrl: './video-gen-dialog.component.html'
})
export class VideoGenDialogComponent implements OnInit {

    videoGen: VideoGen;
    isSaving: boolean;
    busy: number;

    @ViewChild('fileField') fileField: ElementRef;
    @ViewChild('assetsField') assetsField: ElementRef;

    users: User[];

    constructor(
        private principal: Principal,
        private account: AccountService,
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private videoGenService: VideoGenService,
        private userService: UserService,
        private eventManager: JhiEventManager,
    ) {
    }

    ngOnInit() {
        this.busy = 0;
        this.isSaving = false;

        this.userService.query()
            .subscribe((res: ResponseWrapper) => {
                this.users = res.json;
                this.principal.identity().then((account) => {
                    this.videoGen.owner = this.users.filter(user => user.id == account.id)[0];
                });
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    onFileChange(event) {
        if(event.target.files && event.target.files.length > 0) {
            for (let file of event.target.files) {
                console.info(file);
                if(file.size > 20000000) {
                    this.jhiAlertService.error("Fichier " + file.name + " tros gros, limite de 20Mo par fichier", null, null);
                    continue;
                }

                let formData = new FormData();
                formData.set("file", file, file.name);

                this.videoGenService.updateVideoGenFile(formData).subscribe((success) => {
                    console.info(success);
                });

            }
        }
    }

    copyAccount(account) {
        return {
            id: account.id,
            activated: account.activated,
            email: account.email,
            firstName: account.firstName,
            langKey: account.langKey,
            lastName: account.lastName,
            login: account.login,
            imageUrl: account.imageUrl
        };
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.videoGen.id !== undefined) {
            this.subscribeToSaveResponse(
                this.videoGenService.update(this.videoGen));
        } else {
            this.subscribeToSaveResponse(
                this.videoGenService.create(this.videoGen));
        }
    }

    private subscribeToSaveResponse(result: Observable<VideoGen>) {
        result.subscribe((res: VideoGen) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: VideoGen) {
        this.eventManager.broadcast({ name: 'videoGenListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-video-gen-popup',
    template: ''
})
export class VideoGenPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private videoGenPopupService: VideoGenPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.videoGenPopupService
                    .open(VideoGenDialogComponent as Component, params['id']);
            } else {
                this.videoGenPopupService
                    .open(VideoGenDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
