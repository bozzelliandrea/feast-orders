import {Directive, ElementRef} from '@angular/core';

@Directive({
  selector: '[todo]'
})
export class TodoDirective {

  constructor(elRef: ElementRef) {
    const button: HTMLButtonElement = elRef.nativeElement;
    button.classList.add('btn');
    button.classList.add('btn-info');
    _enrichBaseButton(button);

    const icon: any = document.createElement("div");
    icon.className = 'material-icons-outlined fs-1 button-icon';
    icon.textContent = 'play_arrow';
    icon.style = 'font-size: 25px !important';

    elRef.nativeElement.append(icon);
  }

}

@Directive({
  selector: '[progress]'
})
export class ProgressDirective {

  constructor(elRef: ElementRef) {
    const button: HTMLButtonElement = elRef.nativeElement;
    button.classList.add('btn');
    button.classList.add('btn-warning');
    _enrichBaseButton(button);

    const icon: any = document.createElement("div");
    icon.className = 'material-icons-outlined fs-1 button-icon';
    icon.textContent = 'autorenew';
    icon.style = 'font-size: 25px !important';

    elRef.nativeElement.append(icon);
  }

}

@Directive({
  selector: '[done]'
})
export class DoneDirective {

  constructor(elRef: ElementRef) {
    const button: HTMLButtonElement = elRef.nativeElement;
    button.classList.add('btn');
    button.classList.add('btn-success');
    _enrichBaseButton(button);

    const icon: any = document.createElement("div");
    icon.className = 'material-icons-outlined fs-1 button-icon';
    icon.textContent = 'done_all';
    icon.style = 'font-size: 25px !important';

    elRef.nativeElement.append(icon);
  }

}

@Directive({
  selector: '[delete]'
})
export class DeleteDirective {

  constructor(elRef: ElementRef) {
    const button: HTMLButtonElement = elRef.nativeElement;
    button.classList.add('btn');
    button.classList.add('btn-danger');
    _enrichBaseButton(button);

    const icon: any = document.createElement("div");
    icon.className = 'material-icons-outlined fs-1 button-icon';
    icon.textContent = 'clear';
    icon.style = 'font-size: 25px !important';

    elRef.nativeElement.append(icon);
  }

}


function _enrichBaseButton(button: HTMLButtonElement) {
  button.style.width = "150px";
  button.style.height = "40px";
  button.style.display = "flex";
  button.style.justifyContent = "space-around";
}
