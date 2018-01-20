import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VideoGen } from './video-gen.model';
import { VideoGenService } from './video-gen.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-video-gen',
    templateUrl: './video-gen.component.html'
})
export class VideoGenComponent implements OnInit, OnDestroy {
videoGens: VideoGen[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private videoGenService: VideoGenService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.videoGenService.query().subscribe(
            (res: ResponseWrapper) => {
                this.videoGens = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInVideoGens();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: VideoGen) {
        return item.id;
    }
    registerChangeInVideoGens() {
        this.eventSubscriber = this.eventManager.subscribe('videoGenListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
