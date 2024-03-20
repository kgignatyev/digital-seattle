import { Component } from '@angular/core';
import {
  DistributionsServiceV1Service,
  DonationsServiceV1Service,
  V1Distribution,
  V1Donation,
  V1SearchRequest
} from "../../generated/api_client";

@Component({
  selector: 'app-donations-list',
  templateUrl: './donations-list.component.html'
})
export class DonationsListComponent {
  donations: Array<V1Donation> = [];
  constructor(private dntsSvc: DonationsServiceV1Service) {
    this.refresh();
  }


  refresh() {
    //todo: implement data source
    const searchRequest:V1SearchRequest = {
      pagination: {
        offset: 0,
        limit: 1000
      }, textCriteria: ""

    }
    this.dntsSvc.searchDonations(searchRequest).subscribe(dists => {
      this.donations = dists.items
    });
  }
}
