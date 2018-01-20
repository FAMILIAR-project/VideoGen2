import { Route } from '@angular/router';

import { EditorComponent } from './';

export const EDITOR_ROUTE: Route = {
    path: 'videogen-editor',
    component: EditorComponent,
    data: {
        authorities: [],
        pageTitle: 'VideoGen Editor'
    }
};
