import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(private router:Router, private jwtHelper: JwtHelperService){}
  
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
      if (localStorage.getItem('eshop-jwt')) {
          // check if route is restricted by role
          if (route.data.roles && route.data.roles.indexOf(localStorage.getItem('eshop-usertype')) === -1) {
              // role not authorised so redirect to login page
              this.router.navigate(['login']);
              return false;
          }
          // authorised so return true
          return true;
      }

      // not logged in so redirect to login page with the return url
      this.router.navigate(['login'], { queryParams: { returnUrl: state.url }});
      return false;
  }
}