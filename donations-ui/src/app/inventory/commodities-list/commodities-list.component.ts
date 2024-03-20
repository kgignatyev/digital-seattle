import { Component } from '@angular/core';
import {ReportsServiceV1Service, V1Commodity, V1Donation} from "../../generated/api_client";
import {Router} from "@angular/router";

@Component({
  selector: 'app-commodities-list',
  templateUrl: './commodities-list.component.html'
})
export class CommoditiesListComponent {
  inventory: Array<V1Commodity> = [];
  constructor(private reportsSvc: ReportsServiceV1Service,
              private router: Router) {
    this.refresh();
  }


  refresh() {
    this.reportsSvc.inventoryReport().subscribe(invReport => {
      this.inventory = invReport
    });
  }

  newDonation() {
    this.router.navigate(['/donations/new']);
  }


}
