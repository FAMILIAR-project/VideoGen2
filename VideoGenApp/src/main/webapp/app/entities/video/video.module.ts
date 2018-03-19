import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VideoGenSharedModule } from '../../shared';
import {
    VideoService,
    VideoPopupService,
    VideoComponent,
    VideoDetailComponent,
    VideoDialogComponent,
    VideoPopupComponent,
    VideoDeletePopupComponent,
    VideoDeleteDialogComponent,
    videoRoute,
    videoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...videoRoute,
    ...videoPopupRoute,
];

@NgModule({
    imports: [
        VideoGenSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        VideoComponent,
        VideoDetailComponent,
        VideoDialogComponent,
        VideoDeleteDialogComponent,
        VideoPopupComponent,
        VideoDeletePopupComponent,
    ],
    entryComponents: [
        VideoComponent,
        VideoDialogComponent,
        VideoPopupComponent,
        VideoDeleteDialogComponent,
        VideoDeletePopupComponent,
    ],
    providers: [
        VideoService,
        VideoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VideoGenVideoModule {}
