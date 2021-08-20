import { Component, ComponentFactory, ComponentFactoryResolver, ComponentRef, Type, ViewChild, ViewContainerRef } from '@angular/core';
import { AbstractModal } from '../../class/abstract-modal.class';

@Component({
  template: `
    <ng-template #container></ng-template>
  `
})
export class ModalContainerComponent {

  @ViewChild('container', { read: ViewContainerRef }) private modalContainer!: ViewContainerRef;

  constructor(private _componentFactoryResolver: ComponentFactoryResolver) { }

  createModal<T extends AbstractModal>(component: Type<T>): ComponentRef<T> {
    const factory: ComponentFactory<T> = this._componentFactoryResolver.resolveComponentFactory(component);

    return this.modalContainer.createComponent(factory, 0);
  }
}
