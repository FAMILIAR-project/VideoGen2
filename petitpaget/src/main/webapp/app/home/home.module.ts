import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VideoGenSharedModule } from '../shared';

import { HOME_ROUTE, HomeComponent } from './';
import { TestPageDirective } from './test-page.directive';

@NgModule({
    imports: [
        VideoGenSharedModule,
        RouterModule.forRoot([ HOME_ROUTE ], { useHash: true })
    ],
    declarations: [
        HomeComponent,
        TestPageDirective,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VideoGenHomeModule {}
