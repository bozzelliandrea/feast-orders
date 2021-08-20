import { Component } from '@angular/core';
import { AbstractModal } from 'src/app/shared/class/abstract-modal.class';

@Component({
  template: `
    <div class="modal" role="dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" (click)="cancel()">Cancel</button>
        <button type="button" class="btn btn-primary" (click)="save()">Save</button>
      </div>
    </div>
  </div>
  `
})
export class MyModalComponent extends AbstractModal {

  title: string | undefined;
  message: string | undefined;

  inject(inputs: any): void {
    this.title = inputs.title;
    this.message = inputs.message;
  }

  save(): void {
    this.close('saving');
  }

  cancel(): void {
    this.dismiss('canceling');
  }

}