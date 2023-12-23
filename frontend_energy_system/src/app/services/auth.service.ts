import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
    private jwtHelper: JwtHelperService = new JwtHelperService();

    isAuthenticated(): boolean {
        return !!localStorage.getItem('eshop-jwt');
    }

    isTokenExpired(): boolean {
        const token = localStorage.getItem('eshop-jwt');
    
        // Token does not exist or is expired
        return !token || this.jwtHelper.isTokenExpired(token);
    }
}
