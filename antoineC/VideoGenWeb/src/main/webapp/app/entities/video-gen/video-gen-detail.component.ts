import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { VideoGen } from './video-gen.model';
import { VideoGenService } from './video-gen.service';

@Component({
    selector: 'jhi-video-gen-detail',
    templateUrl: './video-gen-detail.component.html'
})
export class VideoGenDetailComponent implements OnInit, OnDestroy {

    videoGen: VideoGen;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private videoGenService: VideoGenService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVideoGens();
    }

    load(id) {
        this.videoGenService.find(id).subscribe((videoGen) => {
            this.videoGen = videoGen;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVideoGens() {
        this.eventSubscriber = this.eventManager.subscribe(
            'videoGenListModification',
            (response) => this.load(this.videoGen.id)
        );
    }
}
