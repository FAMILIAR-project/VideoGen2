import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ngx-webstorage';

import { VideoGenWebSharedModule, UserRouteAccessService } from './shared';
import { VideoGenWebAppRoutingModule} from './app-routing.module';
import { VideoGenWebHomeModule } from './home/home.module';
import { VideoGenWebAdminModule } from './admin/admin.module';
import { VideoGenWebAccountModule } from './account/account.module';
import { VideoGenWebEntityModule } from './entities/entity.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        VideoGenWebAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        VideoGenWebSharedModule,
        VideoGenWebHomeModule,
        VideoGenWebAdminModule,
        VideoGenWebAccountModule,
        VideoGenWebEntityModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class VideoGenWebAppModule {}
