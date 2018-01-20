import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VideoGenWebSharedModule } from '../shared';

import { EDITOR_ROUTE, EditorComponent} from './';
import {EditorService} from "./editor.service";

@NgModule({
    imports: [
        VideoGenWebSharedModule,
        RouterModule.forChild([ EDITOR_ROUTE ])
    ],
    declarations: [
        EditorComponent,
    ],
    entryComponents: [
    ],
    providers: [
        EditorService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VideoGenWebEditorModule {}
