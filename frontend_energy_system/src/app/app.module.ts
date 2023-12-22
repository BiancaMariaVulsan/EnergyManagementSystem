import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { AccountService } from './services/account.service';
import { ShopComponent } from './shop/shop.component';
import { SignupComponent } from './signup/signup.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ProfileDetailsComponent } from './profile-details/profile-details.component';
import { AdminComponent } from './admin/admin.component';
import { JwtModule } from "@auth0/angular-jwt";
import { DeviceService } from './services/device.service';
import { DeviceEditComponent } from './admin/admin-devices/device-edit/device-edit.component';
import { AdminDevicesComponent } from './admin/admin-devices/admin-devices.component';
import { AdminUsersComponent } from './admin/admin-users/admin-users.component';
import { UserEditComponent } from './admin/admin-users/user-edit/user-edit.component';
import { ToastrModule } from 'ngx-toastr';
import { GraphComponent } from './graph/graph.component';
import { MeasurementsChartComponent } from './measurements-chart/measurements-chart.component';
import { NgChartsModule } from 'ng2-charts';
import { DatePipe } from '@angular/common';
import { WebSocketSrvice } from './services/websockets.service';
import { MeasurementService } from './services/measurement.service';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { AuthInterceptor } from './services/interceptor.service';
import { ChatComponent } from './chat/chat.component';

export function tokenGetter() {
  return localStorage.getItem("eshop-jwt");
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    ShopComponent,
    SignupComponent,
    ForgotPasswordComponent,
    ProfileDetailsComponent,
    AdminComponent,
    DeviceEditComponent,
    AdminDevicesComponent,
    AdminUsersComponent,
    UserEditComponent,
    GraphComponent,
    MeasurementsChartComponent,
    ChatComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right',
      preventDuplicates: true
    }),
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ["http://127.0.0.1:8080", "http://127.0.0.1:8000", "http://127.0.0.1:8081", "ws://127.0.0.1:8081", "http://127.0.0.1:4200"],
        disallowedRoutes: []
      }
    }),
    NgChartsModule
  ],
  providers: [AccountService, DeviceService, DatePipe, WebSocketSrvice, MeasurementService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
