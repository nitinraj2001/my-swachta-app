import { NavbarComponent } from './components/navbar/navbar.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './page/home/home.component';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { UserdashboardComponent } from './user/userdashboard/userdashboard.component';
import { AdmindashboardComponent } from './user/admindashboard/admindashboard.component';
import {MatCardModule} from '@angular/material/card';
import { WelcomePageComponent } from './common/welcome-page/welcome-page.component';
import { WelcomeAdminComponent } from './admin/welcome-admin/welcome-admin.component';
import { CategoryComponent } from './admin/category/category.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { ViewCategoriesComponent } from './common/view-categories/view-categories.component';
import { UpdateCategoryComponent } from './admin/update-category/update-category.component';
import { AgmCoreModule } from '@agm/core';
import { AddWasteComponent } from './user/add-waste/add-waste.component';
import { NgoRegistrationComponent } from './admin/ngo-registration/ngo-registration.component';
import { RegisterSocietyComponent } from './admin/register-society/register-society.component';
import { ViewSocietiesComponent } from './admin/view-societies/view-societies.component';
import { ViewSocietyComponent } from './admin/view-society/view-society.component';
import { AddIndustryComponent } from './admin/add-industry/add-industry.component';
import { ViewWasteComponent } from './admin/view-waste/view-waste.component';
import { ViewNgosComponent } from './admin/view-ngos/view-ngos.component';
import { ViewIndustryComponent } from './admin/view-industry/view-industry.component';
import { ViewIndustriesComponent } from './admin/view-industries/view-industries.component';
import { ClassifyWasteComponent } from './user/classify-waste/classify-waste.component';
import { SchedulePickupComponent } from './user/schedule-pickup/schedule-pickup.component';
import { ViewNgoMapComponent } from './user/view-ngo-map/view-ngo-map.component';
import { ViewAllIndustriesComponent } from './admin/view-all-industries/view-all-industries.component';
import { ManageWasteComponent } from './common/manage-waste/manage-waste.component';
import { FactsComponent } from './common/facts/facts.component';
import { AboutComponent } from './common/about/about.component';
import { ViewUploadedWasteComponent } from './user/view-uploaded-waste/view-uploaded-waste.component';
import { ViewSocietyByUserComponent } from './user/view-society-by-user/view-society-by-user.component';
import { ViewSlumMapComponent } from './user/view-slum-map/view-slum-map.component';
import { ViewPickupScheduleComponent } from './user/view-pickup-schedule/view-pickup-schedule.component';
import { RegisterSlumAreaComponent } from './admin/register-slum-area/register-slum-area.component';
import { UserViewIndustryComponent } from './user/user-view-industry/user-view-industry.component';
import { RegisterMuncipalityComponent } from './admin/register-muncipality/register-muncipality.component';
import { ForgetPasswordComponent } from './components/forget-password/forget-password.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { ViewAllNGOByUserComponent } from './user/view-all-ngoby-user/view-all-ngoby-user.component';
import { ViewNGODetailsByUserComponent } from './user/view-ngodetails-by-user/view-ngodetails-by-user.component';
import { ViewIndustryDetailsByUserComponent } from './user/view-industry-details-by-user/view-industry-details-by-user.component';
import { ViewSlumAreaDetailsByUserComponent } from './user/view-slum-area-details-by-user/view-slum-area-details-by-user.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    UserdashboardComponent,
    AdmindashboardComponent,
    WelcomePageComponent,
    WelcomeAdminComponent,
    CategoryComponent,
    UserProfileComponent,
    ViewCategoriesComponent,
    UpdateCategoryComponent,
    AddWasteComponent,
    NgoRegistrationComponent,
    RegisterSocietyComponent,
    ViewSocietiesComponent,
    ViewSocietyComponent,
    AddIndustryComponent,
    ViewWasteComponent,
    ViewNgosComponent,
    ViewIndustryComponent,
    ViewIndustriesComponent,
    ClassifyWasteComponent,
    SchedulePickupComponent,
    ViewNgoMapComponent,
    ViewAllIndustriesComponent,
    ManageWasteComponent,
    FactsComponent,
    AboutComponent,
    ViewUploadedWasteComponent,
    ViewSocietyByUserComponent,
    ViewSlumMapComponent,
    ViewPickupScheduleComponent,
    RegisterSlumAreaComponent,
    UserViewIndustryComponent,
    RegisterMuncipalityComponent,
    ForgetPasswordComponent,
    ResetPasswordComponent,
    ViewAllNGOByUserComponent,
    ViewNGODetailsByUserComponent,
    ViewIndustryDetailsByUserComponent,
    ViewSlumAreaDetailsByUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCdbAGiz9-vhYhrtegBjdxV1SDffe_wyB0',
      libraries: ['places']
    }),
    MatSnackBarModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatCardModule,
    MatButtonModule,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
