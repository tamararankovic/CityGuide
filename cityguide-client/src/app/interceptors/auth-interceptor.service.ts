
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem('token');
        if (!token) {
            return next.handle(req);
        }
        const modifiedReq = req.clone({
            headers: req.headers.set('Authorization', 'Bearer ' +  token)
          });
        return next.handle(modifiedReq);
    }
}