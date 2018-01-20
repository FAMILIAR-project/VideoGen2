import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { VideoGenComponent } from './video-gen.component';
import { VideoGenDetailComponent } from './video-gen-detail.component';
import { VideoGenPopupComponent } from './video-gen-dialog.component';
import { VideoGenDeletePopupComponent } from './video-gen-delete-dialog.component';
import {VideoGenEditPopupComponent} from "./video-gen-edit-dialog.component";

export const videoGenRoute: Routes = [
    {
        path: 'video-gen',
        component: VideoGenComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VideoGens'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'video-gen/:id',
        component: VideoGenDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VideoGens'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const videoGenPopupRoute: Routes = [
    {
        path: 'video-gen-new',
        component: VideoGenPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VideoGens'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'video-gen/:id/edit',
        component: VideoGenEditPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VideoGens'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'video-gen/:id/delete',
        component: VideoGenDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VideoGens'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
