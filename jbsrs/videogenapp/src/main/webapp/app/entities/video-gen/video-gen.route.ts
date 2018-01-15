import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { VideoGenComponent } from './video-gen.component';
import { VideoGenPlayerComponent } from './video-gen-player.component';
import { VideoGenConfiguratorComponent } from './video-gen-configurator.component';
import { VideoGenDetailComponent } from './video-gen-detail.component';
import { VideoGenPopupComponent } from './video-gen-dialog.component';
import { VideoGenDeletePopupComponent } from './video-gen-delete-dialog.component';

export const videoGenRoute: Routes = [
    {
        path: 'video-gen',
        component: VideoGenComponent,
        data: {
            //authorities: ['ROLE_USER'],
            pageTitle: 'VideoGens'
        },
        //canActivate: [UserRouteAccessService]
    }, {
        path: 'video-gen/:id',
        component: VideoGenDetailComponent,
        data: {
            //authorities: ['ROLE_USER'],
            pageTitle: 'VideoGens'
        },
        //canActivate: [UserRouteAccessService]
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
        component: VideoGenPopupComponent,
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

export const videoGenPlayerRoute: Routes = [
    {
        path: 'video-gen-player',
        component: VideoGenPlayerComponent,
        data: {
            //authorities: ['ROLE_USER'],
            pageTitle: 'Player'
        },
        //canActivate: [UserRouteAccessService]
    }
];

export const videoGenConfiguratorRoute: Routes = [
    {
        path: 'video-gen-configurator',
        component: VideoGenConfiguratorComponent,
        data: {
            //authorities: ['ROLE_USER'],
            pageTitle: 'Configurator'
        },
        //canActivate: [UserRouteAccessService]
    }
];
