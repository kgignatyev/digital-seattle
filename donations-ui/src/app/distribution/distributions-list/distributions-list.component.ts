import { Component } from '@angular/core';
import {
  DistributionsServiceV1Service,
  ReportsServiceV1Service,
  V1Commodity,
  V1Distribution, V1SearchRequest
} from "../../generated/api_client";
import {Router} from "@angular/router";

@Component({
  selector: 'app-distributions-list',
  templateUrl: './distributions-list.component.html'
})
export class DistributionsListComponent {
  distributions: Array<V1Distribution> = [];
  constructor(private distSvc: DistributionsServiceV1Service) {
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
    this.distSvc.searchDistributions(searchRequest).subscribe(dists => {
      this.distributions = dists.items
    });
  }
}
