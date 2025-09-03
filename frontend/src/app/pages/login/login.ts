import { Component, inject, signal } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AuthService } from '../../core/services/auth-service';
import { TokenService } from '../../core/services/token-service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  isRegisterFormVisible = signal(false);
  private authService = inject(AuthService);
  private tokenService = inject(TokenService);
  private toast = inject(NgToastService);
  private router = inject(Router);

  // Custom validator for future dates
  futureDateValidator(control: FormControl): { [key: string]: any } | null {
    const today = new Date();
    const dob = new Date(control.value);
    return dob > today ? { futureDate: true } : null;
  }

  // Custom validator for password mismatch
  passwordsMatchValidator: ValidatorFn = (
    control: AbstractControl
  ): ValidationErrors | null => {
    const formGroup = control as FormGroup;
    const password = formGroup.get('senha')?.value;
    const confirmPassword = formGroup.get('confirmarSenha')?.value;
    return password === confirmPassword ? null : { passwordsMismatch: true };
  };

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    senha: new FormControl('', [Validators.required]),
  });

  registerForm = new FormGroup(
    {
      primeiroNome: new FormControl('', [Validators.required]),
      ultimoNome: new FormControl('', [Validators.required]),
      dataNascimento: new FormControl('', [
        Validators.required,
        this.futureDateValidator,
      ]),
      email: new FormControl('', [Validators.required, Validators.email]),
      telefone: new FormControl('', [Validators.required]),
      senha: new FormControl('', [Validators.required]),
      confirmarSenha: new FormControl('', [Validators.required]),
    },
    { validators: this.passwordsMatchValidator }
  );

  toggleForm(showRegister: boolean) {
    this.isRegisterFormVisible.set(showRegister);
    if (!showRegister) {
      this.registerForm.reset();
    }
  }

  onLoginSubmit() {
    if (this.loginForm.valid) {
      console.log('Dados de login válidos:', this.loginForm.value);
      const credentials = {
        username: this.loginForm.value.email as string,
        password: this.loginForm.value.senha as string,
      };
      this.authService
        .login(credentials)
        .then((response) => {
          console.log('Login bem-sucedido:', response);
          this.tokenService.saveToken(response.token);
          this.tokenService.saveUserId(response.userId);
          this.tokenService.savePermissoes(response.permissoes);
          this.toast.success('Login realizado com sucesso', 'Sucesso', 10000);
          this.router.navigate(['']);
        })
        .catch((error) => {
          console.error('Erro no login:', error);
          this.toast.danger('Dados de login inválidos', 'Erro', 10000);
        });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }

  onRegisterSubmit() {
    if (this.registerForm.valid) {
      console.log('Dados de registro válidos:', this.registerForm.value);
      this.authService
        .register(this.registerForm.value)
        .then(() => {
          this.toast.success('Dados registrados com sucesso', 'Sucesso', 10000);
          this.toggleForm(false);
        })
        .catch(() => {
          this.toast.danger('Dados de registro inválidos', 'Erro', 10000);
        });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
}
