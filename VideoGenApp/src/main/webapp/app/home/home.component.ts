import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { Http, Response, Headers } from '@angular/http';
import { Account, LoginModalService, Principal } from '../shared';
import { SERVER_API_URL } from '../app.constants';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})
export class HomeComponent implements OnInit {
	private resourceUrl = SERVER_API_URL + 'api/videos/1';
    account: Account;
    modalRef: NgbModalRef;
	results:any = null;
	
    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private http: Http
    ) {
    }
	
    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
    }
	
	private getVideo() {
		console.log(`${this.resourceUrl}`);
		this.results = this.http.get(`api/videogen`).map((res: Response) => {
            const jsonResponse = res.json();
            return jsonResponse;
        }).subscribe(data => {
        	console.log(data);
    		var video = document.getElementsByTagName('video')[0];
		    var sources = video.getElementsByTagName('source');
		    sources[0].src = '../../content/'+data.video.name+'.mp4';
		    video.load();
		});
	}
	
    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
