import { ComponentRef } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { ModalContainerComponent } from "../component/modal-container/modal-container.component";
import { AbstractModal } from "./abstract-modal.class";

export class Modal {

    private result$ = new Subject<any>();
  
    constructor(
      private modalContainer: ComponentRef<ModalContainerComponent>,
      private modal: ComponentRef<AbstractModal>,
    ) {
      this.modal.instance.modalInstance = this;
    }
  
    close(output: any): void {
      this.result$.next(output);
      this.destroy$();
    }
  
    dismiss(output: any): void {
      this.result$.error(output);
      this.destroy$();
    }
  
    onResult(): Observable<any> {
      return this.result$.asObservable();
    }
  
    private destroy$(): void {
      this.modal.destroy();
      this.modalContainer.destroy();
      this.result$.complete();
    }
  
  }