import { ModeType } from './../../../shared/enums/mode.enum';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent implements OnInit {

  public mode: ModeType | undefined;
  public orderId: number | undefined;

  constructor(private _route: ActivatedRoute) {
    if (_route.snapshot.paramMap.get('id'))
      this.orderId = parseInt(_route.snapshot.paramMap.get('id') as string);
    this.mode = _route.snapshot.data?.mode as ModeType;
  }

  ngOnInit(): void {

  }

}
