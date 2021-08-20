import { Modal } from './../class/modal.class';
import { ApplicationRef, ComponentFactory, ComponentFactoryResolver, Injectable, Type, EventEmitter } from '@angular/core';
import { AbstractModal } from '../class/abstract-modal.class';
import { ModalContainerComponent } from '../component/modal-container/modal-container.component';

@Injectable({
    providedIn: 'root'
  })
export class ModalService {

    private modalContainer!: HTMLElement;
    private modalContainerFactory!: ComponentFactory<ModalContainerComponent>;

    constructor(
        private componentFactoryResolver: ComponentFactoryResolver,
        private appRef: ApplicationRef
    ) {
        this._setupModalContainerFactory();
    }

    create<T extends AbstractModal>(component: Type<T>, inputs?: any): Modal {
        this._setupModalContainer();

        const modalContainerRef = this.appRef.bootstrap(this.modalContainerFactory, this.modalContainer);

        const modalComponentRef = modalContainerRef.instance.createModal(component);

        if (inputs) {
            modalComponentRef.instance.inject(inputs);
        }

        const modalRef = new Modal(modalContainerRef, modalComponentRef);

        return modalRef;
    }

    private _setupModalContainer(): void {
        this.modalContainer = document.createElement('div');
        document.getElementsByTagName('body')[0].appendChild(this.modalContainer);
    }

    private _setupModalContainerFactory(): void {
        this.modalContainerFactory = this.componentFactoryResolver.resolveComponentFactory(ModalContainerComponent);
    }

}