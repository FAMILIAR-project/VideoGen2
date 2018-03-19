import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Video } from './video.model';
import { VideoService } from './video.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-video',
    templateUrl: './video.component.html'
})
export class VideoComponent implements OnInit, OnDestroy {
videos: Video[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private videoService: VideoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.videoService.query().subscribe(
            (res: ResponseWrapper) => {
                this.videos = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInVideos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Video) {
        return item.id;
    }
    registerChangeInVideos() {
        this.eventSubscriber = this.eventManager.subscribe('videoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
