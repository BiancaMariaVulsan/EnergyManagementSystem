import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ShopComponent } from './shop/shop.component';
import { ProfileDetailsComponent } from './profile-details/profile-details.component';
import { SignupComponent } from './signup/signup.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './guards/auth.guard';
import { AdminDevicesComponent } from './admin/admin-devices/admin-devices.component';
import { AdminUsersComponent } from './admin/admin-users/admin-users.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'shop', component: ShopComponent, canActivate: [AuthGuard]},
  {path: 'profile-details', component: ProfileDetailsComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  // {path: 'forgot-password', component: ForgotPasswordComponent},
  {path: 'admin', component: AdminComponent, canActivate: [AuthGuard], data: { roles: 'Admin' }},
  {path: 'admin/devices', component: AdminDevicesComponent, canActivate: [AuthGuard], data: { roles: 'Admin' }},
  {path: 'admin/users', component: AdminUsersComponent, canActivate: [AuthGuard], data: { roles: 'Admin' }}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
