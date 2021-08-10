import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { OrderStatus } from '../../type/order-status.type';

@Component({
  selector: 'order-card',
  templateUrl: './order-card.component.html',
  styleUrls: ['./order-card.component.scss']
})
export class OrderCardComponent implements OnChanges {
  
  @Input() title: string | undefined;
  @Input() content: string | undefined;
  @Input() orderStatus: OrderStatus;

  @Output() onNextStep = new EventEmitter<OrderStatus>();

  public stepLabel: string = '';

  public ngOnChanges(changes: SimpleChanges): void {
    switch (this.orderStatus) {
      case 'TODO':
        this.stepLabel = 'AVVIA';
        break;
      case 'PROGRESS':
        this.stepLabel = 'COMPLETA';
        break;
    }
  }

  public startNextStep(): void {

    switch (this.orderStatus) {
      case 'TODO':
        this.onNextStep.emit('PROGRESS');
        break;
      case 'PROGRESS':
        this.onNextStep.emit('DONE');
        break;
    }
  }
}
