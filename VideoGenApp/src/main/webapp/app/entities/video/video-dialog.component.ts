import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Video } from './video.model';
import { VideoPopupService } from './video-popup.service';
import { VideoService } from './video.service';

@Component({
    selector: 'jhi-video-dialog',
    templateUrl: './video-dialog.component.html'
})
export class VideoDialogComponent implements OnInit {

    video: Video;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private videoService: VideoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.video.id !== undefined) {
            this.subscribeToSaveResponse(
                this.videoService.update(this.video));
        } else {
            this.subscribeToSaveResponse(
                this.videoService.create(this.video));
        }
    }

    private subscribeToSaveResponse(result: Observable<Video>) {
        result.subscribe((res: Video) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Video) {
        this.eventManager.broadcast({ name: 'videoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-video-popup',
    template: ''
})
export class VideoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private videoPopupService: VideoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.videoPopupService
                    .open(VideoDialogComponent as Component, params['id']);
            } else {
                this.videoPopupService
                    .open(VideoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
