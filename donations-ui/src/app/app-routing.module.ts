import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommoditiesListComponent} from "./inventory/commodities-list/commodities-list.component";
import {DonationComponent} from "./donation/donation/donation.component";
import {DonorsReportComponent} from "./reports/donors-report/donors-report.component";
import {DistributionsListComponent} from "./distribution/distributions-list/distributions-list.component";
import {DonationsListComponent} from "./donation/donations-list/donations-list.component";
import {DistributionComponent} from "./distribution/distribution/distribution.component";


const routes: Routes = [

  {path: '', component: CommoditiesListComponent},
  {path: 'inventory', component: CommoditiesListComponent},
  {path: 'distributions', component: DistributionsListComponent},
  {path: 'distributions/:id', component: DistributionComponent},
  {path: 'distributions/:name/:amountKind', component: DistributionComponent},
  {path: 'donations', component: DonationsListComponent},
  {path: 'report/donors-report', component: DonorsReportComponent},
  {path: 'donations/:id', component: DonationComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
