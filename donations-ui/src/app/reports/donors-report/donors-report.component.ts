import { Component } from '@angular/core';
import {
  DonationsServiceV1Service,
  ReportsServiceV1Service,
  V1Donation, V1DonorContribution,
  V1SearchRequest
} from "../../generated/api_client";

@Component({
  selector: 'app-donors-report',
  templateUrl: './donors-report.component.html'
})
export class DonorsReportComponent {
  donorContributions: Array<V1DonorContribution> = [];
  constructor(private reportsSvc: ReportsServiceV1Service) {
    this.refresh();
  }


  refresh() {

    this.reportsSvc.donatorsReport().subscribe(dists => {
      this.donorContributions = dists
    });
  }
}
