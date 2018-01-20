import {Component, OnInit} from "@angular/core";
import {EditorService} from "./editor.service";

@Component({
    selector: 'vdg-editor',
    templateUrl: './editor.component.html',
    styleUrls: [
        'editor.css'
    ]

})
export class EditorComponent implements OnInit {
    videogenSourceCode: String;

    constructor(private editorService: EditorService) {}

    ngOnInit(): void {
        this.videogenSourceCode = '';
    }

    upload() {
        this.editorService.upload(this.videogenSourceCode).then(() => { console.info("Uploaded Successfully !!!"); }, (err) => {
            console.error("Upload failed");
        });
    }

}
