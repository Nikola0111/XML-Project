import { HttpHeaders, HttpClient, HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';



@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>,
              next: HttpHandler): Observable<HttpEvent<any>> {


                console.log("USAAAOOO JE NEMANJA");

        const idToken = localStorage.getItem("id_token");

        console.log("Tokencic je ovaj=="+idToken);

        if (idToken) {
            const cloned = req.clone({
                headers: req.headers.set("Authorization",
                      idToken)
            });

            return next.handle(cloned);
        }
        else {
            return next.handle(req);
        }
    }
}
      