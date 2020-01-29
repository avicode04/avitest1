import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';
import { BuyerDashboardComponent } from './buyer-dashboard/buyer-dashboard.component';
import { HomeComponent } from './home/home.component';
import { SellerDashboardComponent } from './seller-dashboard/seller-dashboard.component';
import { SellerRegistrationComponent } from './seller-registration/seller-registration.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { BestDealsComponent } from './best-deals/best-deals.component';
import { BuyerRegistrationComponent } from './buyer-registration/buyer-registration.component';
import { BuyerProfileComponent } from './buyer-profile/buyer-profile.component';
import { SellerDashboardInventoryComponent } from './seller-dashboard-inventory/seller-dashboard-inventory.component';
import { AddProductComponent } from './add-product/add-product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { IncartProductsComponent } from './incart-products/incart-products.component';
import { PaymentService } from './services/payment.service';
import { PaymentComponent } from './payment/payment.component';
//import { PaymentComponent } from './payment/payment.component';

const routes: Routes = [

  // Normal Routes
  { path: 'seller-dashboard-inventory', component: SellerDashboardInventoryComponent },
  { path: 'seller-dashboard', component: SellerDashboardComponent },
  { path: 'buyer-profile', component: BuyerProfileComponent},
  { path: 'seller-registration', component: SellerRegistrationComponent },
  { path: 'login-page', component: LoginPageComponent},
  { path: 'search', component: BestDealsComponent },
  { path: 'buyer-dashboard', component: BuyerDashboardComponent},
  { path: 'buyer-registration',component: BuyerRegistrationComponent},
  { path: 'add-product', component: AddProductComponent },
  { path: 'product-details', component: ProductDetailsComponent },
  { path: 'update-product', component: UpdateProductComponent },
  { path: 'app-incart-products',component:IncartProductsComponent},
  { path: 'payment', component: PaymentComponent},
  
  // Routes to be placed at the last
  { path: '', component: HomeComponent },
  { path: '**', component: PageNotFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}

export const routingComponents = [HomeComponent, SellerDashboardComponent,
  BestDealsComponent, LoginPageComponent, BuyerDashboardComponent, 
  AddProductComponent,IncartProductsComponent];
