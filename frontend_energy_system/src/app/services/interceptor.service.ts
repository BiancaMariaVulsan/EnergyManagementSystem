import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('Interceptor is executing...');
    const token = localStorage.getItem("eshop-jwt");
    const isLoginRequest = request.url.includes('/person/login');
    const isSignUpRequest = request.url.includes('/person/signup');

    if (!isLoginRequest && !isSignUpRequest) {
        // Clone the request and add the token to the headers
        request = request.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });
    }

    // Pass the cloned request to the next handler
    return next.handle(request);
  }
}