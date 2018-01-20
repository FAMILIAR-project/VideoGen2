import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { VideoGenWebVideoGenModule } from './video-gen/video-gen.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        VideoGenWebVideoGenModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VideoGenWebEntityModule {}
