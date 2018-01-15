import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VgCoreModule } from 'videogular2/core';
import { VgControlsModule } from 'videogular2/controls';
import { VgOverlayPlayModule } from 'videogular2/overlay-play';
import { VgBufferingModule } from 'videogular2/buffering';

import { VideogenappSharedModule } from '../../shared';
import {
    VideoGenService,
    VideoGenPopupService,
    VideoGenComponent,
    VideoGenDetailComponent,
    VideoGenDialogComponent,
    VideoGenPopupComponent,
    VideoGenDeletePopupComponent,
    VideoGenDeleteDialogComponent,
    VideoGenPlayerComponent,
    VideoGenConfiguratorComponent,
    videoGenRoute,
    videoGenPopupRoute,
    videoGenPlayerRoute,
    videoGenConfiguratorRoute
} from './';

const ENTITY_STATES = [
    ...videoGenRoute,
    ...videoGenPopupRoute,
    ...videoGenPlayerRoute,
    ...videoGenConfiguratorRoute,
];

@NgModule({
    imports: [
        VideogenappSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        VgCoreModule,
        VgControlsModule,
        VgOverlayPlayModule,
        VgBufferingModule,
    ],
    declarations: [
        VideoGenComponent,
        VideoGenDetailComponent,
        VideoGenDialogComponent,
        VideoGenDeleteDialogComponent,
        VideoGenPopupComponent,
        VideoGenDeletePopupComponent,
        VideoGenPlayerComponent,
        VideoGenConfiguratorComponent,
    ],
    entryComponents: [
        VideoGenComponent,
        VideoGenDialogComponent,
        VideoGenPopupComponent,
        VideoGenDeleteDialogComponent,
        VideoGenDeletePopupComponent,
    ],
    providers: [
        VideoGenService,
        VideoGenPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VideogenappVideoGenModule {}
