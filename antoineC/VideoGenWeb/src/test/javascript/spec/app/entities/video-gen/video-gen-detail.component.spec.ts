/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { VideoGenWebTestModule } from '../../../test.module';
import { VideoGenDetailComponent } from '../../../../../../main/webapp/app/entities/video-gen/video-gen-detail.component';
import { VideoGenService } from '../../../../../../main/webapp/app/entities/video-gen/video-gen.service';
import { VideoGen } from '../../../../../../main/webapp/app/entities/video-gen/video-gen.model';

describe('Component Tests', () => {

    describe('VideoGen Management Detail Component', () => {
        let comp: VideoGenDetailComponent;
        let fixture: ComponentFixture<VideoGenDetailComponent>;
        let service: VideoGenService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VideoGenWebTestModule],
                declarations: [VideoGenDetailComponent],
                providers: [
                    VideoGenService
                ]
            })
            .overrideTemplate(VideoGenDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VideoGenDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VideoGenService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new VideoGen(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.videoGen).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
