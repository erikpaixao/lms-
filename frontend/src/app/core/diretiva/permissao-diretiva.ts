import { Directive, ElementRef, Input, OnInit } from '@angular/core';
import { TokenService } from './../services/token-service';

@Directive({
  selector: '[authPermission]',
  standalone: true,
})
export class AuthPermissionDirective implements OnInit {
  @Input('authPermission') requiredPermission: string = '';

  constructor(private el: ElementRef, private tokenService: TokenService) {}

  ngOnInit() {
    const permissions: string = this.tokenService.getPermissoes() || '';
    const hasPermission = permissions.includes(this.requiredPermission);

    if (!hasPermission) {
      this.el.nativeElement.style.display = 'none';
    }
  }
}
