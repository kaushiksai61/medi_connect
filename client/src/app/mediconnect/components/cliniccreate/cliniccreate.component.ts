import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-cliniccreate',
    templateUrl: './cliniccreate.component.html',
    styleUrls: ['./cliniccreate.component.scss']
})
export class ClinicCreateComponent implements OnInit {
    clinicForm!: FormGroup;
    successMessage: string | null = null;
    errorMessage: string | null = null;

    constructor(private formBuilder: FormBuilder) { }

    ngOnInit(): void {
        this.clinicForm = this.formBuilder.group({
            clinicId: [null, [Validators.required, Validators.min(1)]],
            clinicName: ['', [Validators.required, Validators.minLength(2)]],
            location: ['', [Validators.required]],
            contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
            establishedYear: [null, [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear())]]
        });
    }

    onSubmit(): void {
        if (this.clinicForm.valid) {
            this.successMessage = 'Clinic has been successfully created!';
            this.errorMessage = null;
            console.log('Clinic Created: ', this.clinicForm.value);
            this.resetForm();
        } else {
            this.errorMessage = 'Please fill out all required fields correctly.';
            this.successMessage = null;
        }
    }

    resetForm(): void {
        this.clinicForm.reset({
            clinicId: null,
            clinicName: '',
            location: '',
            contactNumber: '',
            establishedYear: null
        });
    }
}
