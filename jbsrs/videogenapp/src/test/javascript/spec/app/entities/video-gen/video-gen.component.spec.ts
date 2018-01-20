/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { VideogenappTestModule } from '../../../test.module';
import { VideoGenComponent } from '../../../../../../main/webapp/app/entities/video-gen/video-gen.component';
import { VideoGenService } from '../../../../../../main/webapp/app/entities/video-gen/video-gen.service';
import { VideoGen } from '../../../../../../main/webapp/app/entities/video-gen/video-gen.model';

describe('Component Tests', () => {

    describe('VideoGen Management Component', () => {
        let comp: VideoGenComponent;
        let fixture: ComponentFixture<VideoGenComponent>;
        let service: VideoGenService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VideogenappTestModule],
                declarations: [VideoGenComponent],
                providers: [
                    VideoGenService
                ]
            })
            .overrideTemplate(VideoGenComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VideoGenComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VideoGenService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new VideoGen(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.videoGens[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
