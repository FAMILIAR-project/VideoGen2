import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { VideoGen } from './video-gen.model';
import { VideoGenPopupService } from './video-gen-popup.service';
import { VideoGenService } from './video-gen.service';

@Component({
    selector: 'jhi-video-gen-delete-dialog',
    templateUrl: './video-gen-delete-dialog.component.html'
})
export class VideoGenDeleteDialogComponent {

    videoGen: VideoGen;

    constructor(
        private videoGenService: VideoGenService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.videoGenService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'videoGenListModification',
                content: 'Deleted an videoGen'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-video-gen-delete-popup',
    template: ''
})
export class VideoGenDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private videoGenPopupService: VideoGenPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.videoGenPopupService
                .open(VideoGenDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
