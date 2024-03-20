import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {donationsSvcApiModule, donationsSvcConfiguration} from "./generated/api_client";
import {
  DxAutocompleteModule,
  DxBoxModule,
  DxButtonModule,
  DxCalendarModule,
  DxChartModule,
  DxCheckBoxModule, DxDataGridModule,
  DxDateBoxModule,
  DxDropDownBoxModule,
  DxDropDownButtonModule,
  DxFormModule,
  DxListModule,
  DxLoadIndicatorModule,
  DxLoadPanelModule,
  DxLookupModule,
  DxNumberBoxModule,
  DxPopoverModule,
  DxPopupModule,
  DxScrollViewModule,
  DxSelectBoxModule,
  DxSwitchModule,
  DxTabPanelModule,
  DxTabsModule,
  DxTagBoxModule,
  DxTemplateModule,
  DxTextAreaModule,
  DxTextBoxModule,
  DxToastModule,
  DxTooltipModule
} from "devextreme-angular";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { CommoditiesListComponent } from './inventory/commodities-list/commodities-list.component';
import { DonationComponent } from './donation/donation/donation.component';
import { DonorsReportComponent } from './reports/donors-report/donors-report.component';
import { DistributionComponent } from './distribution/distribution/distribution.component';
import { DistributionsListComponent } from './distribution/distributions-list/distributions-list.component';
import { DonationsListComponent } from './donation/donations-list/donations-list.component';
import {CommoditiesComponent} from "./components/commodities/commodities.component";
@NgModule({
  declarations: [
    AppComponent,
    CommoditiesComponent,
    CommoditiesListComponent,
    DonationComponent,
    DonorsReportComponent,
    DistributionComponent,
    DistributionsListComponent,
    DonationsListComponent

  ],
  imports: [
    BrowserModule,
    DxAutocompleteModule,
    DxChartModule,
    DxPopupModule,
    DxPopoverModule,
    DxSwitchModule,
    DxButtonModule,
    DxBoxModule,
    DxNumberBoxModule,
    DxTabsModule,
    DxTemplateModule,
    DxTextBoxModule,
    DxSelectBoxModule,
    DxCheckBoxModule,
    DxLoadPanelModule,
    DxListModule,
    DxLookupModule,
    DxFormModule,
    DxDropDownBoxModule,
    DxLoadIndicatorModule,
    DxDateBoxModule,
    DxDropDownButtonModule,
    DxCheckBoxModule,
    DxTabPanelModule,
    DxTagBoxModule,
    DxToastModule,
    DxScrollViewModule,
    DxCalendarModule,
    DxTextAreaModule,
    HttpClientModule,
    AppRoutingModule,
    donationsSvcApiModule,
    FormsModule,
    DxDataGridModule,
  ],
  providers: [
    {
      provide: donationsSvcConfiguration,
      useFactory: () => new donationsSvcConfiguration(
        {
          basePath: "/donations-svc/api",
        }
      ),
      deps: [],
      multi: false
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
