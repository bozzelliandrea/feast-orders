import { Modal } from "./modal.class";

export abstract class AbstractModal {

    modalInstance!: Modal;

    abstract inject(inputs: any): void;

    close(output?: any): void {
        this.modalInstance.close(output);
    }

    dismiss(output?: any): void {
        this.modalInstance.dismiss(output);
    }
}