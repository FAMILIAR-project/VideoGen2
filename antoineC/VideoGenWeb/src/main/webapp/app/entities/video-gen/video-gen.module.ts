import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VideoGenWebSharedModule } from '../../shared';
import { VideoGenWebAdminModule } from '../../admin/admin.module';
import {
    VideoGenService,
    VideoGenPopupService,
    VideoGenComponent,
    VideoGenDetailComponent,
    VideoGenDialogComponent,
    VideoGenPopupComponent,
    VideoGenDeletePopupComponent,
    VideoGenDeleteDialogComponent,
    videoGenRoute,
    videoGenPopupRoute,
} from './';

const ENTITY_STATES = [
    ...videoGenRoute,
    ...videoGenPopupRoute,
];

@NgModule({
    imports: [
        VideoGenWebSharedModule,
        VideoGenWebAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        VideoGenComponent,
        VideoGenDetailComponent,
        VideoGenDialogComponent,
        VideoGenDeleteDialogComponent,
        VideoGenPopupComponent,
        VideoGenDeletePopupComponent,
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
export class VideoGenWebVideoGenModule {}
