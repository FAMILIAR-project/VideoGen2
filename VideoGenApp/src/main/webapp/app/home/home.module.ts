import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpModule }    from '@angular/http';

import { VideoGenSharedModule } from '../shared';

import { HOME_ROUTE, HomeComponent } from './';

import 'rxjs/add/operator/map'

@NgModule({
    imports: [
    	HttpModule,
        VideoGenSharedModule,
        RouterModule.forChild([ HOME_ROUTE ]),
    ],
    declarations: [
        HomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VideoGenHomeModule {}
