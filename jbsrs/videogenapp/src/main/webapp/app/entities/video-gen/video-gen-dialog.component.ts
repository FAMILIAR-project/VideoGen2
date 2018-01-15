import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { VideoGen } from './video-gen.model';
import { VideoGenPopupService } from './video-gen-popup.service';
import { VideoGenService } from './video-gen.service';

@Component({
    selector: 'jhi-video-gen-dialog',
    templateUrl: './video-gen-dialog.component.html'
})
export class VideoGenDialogComponent implements OnInit {

    videoGen: VideoGen;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private videoGenService: VideoGenService,
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
