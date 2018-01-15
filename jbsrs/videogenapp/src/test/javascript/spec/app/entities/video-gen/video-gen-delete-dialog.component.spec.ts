/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { VideogenappTestModule } from '../../../test.module';
import { VideoGenDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/video-gen/video-gen-delete-dialog.component';
import { VideoGenService } from '../../../../../../main/webapp/app/entities/video-gen/video-gen.service';

describe('Component Tests', () => {

    describe('VideoGen Management Delete Component', () => {
        let comp: VideoGenDeleteDialogComponent;
        let fixture: ComponentFixture<VideoGenDeleteDialogComponent>;
        let service: VideoGenService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VideogenappTestModule],
                declarations: [VideoGenDeleteDialogComponent],
                providers: [
                    VideoGenService
                ]
            })
            .overrideTemplate(VideoGenDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VideoGenDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VideoGenService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
