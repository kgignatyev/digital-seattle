import {Component, OnDestroy} from '@angular/core';
import {DonationsServiceV1Service, V1Commodity, V1Donation} from "../../generated/api_client";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
function makeNewDonation():V1Donation {
  return { donorId: "", id: "", commodities: [] };
}
@Component({
  selector: 'app-donation',
  templateUrl: './donation.component.html'
})
export class DonationComponent implements OnDestroy{


  private sub: Subscription;
  id: string = '';
  donation: V1Donation = makeNewDonation()

  constructor(private donationsSvc: DonationsServiceV1Service, private route: ActivatedRoute, private router: Router) {
    this.sub = this.route.paramMap.subscribe(params => {
      this.id = params.get('id') || 'no-id-param';
      if( 'new' == this.id ){
        this.donation = makeNewDonation();
      }else {
        this.donationsSvc.getDonation(this.id).subscribe(d => {
          console.log('got donation', d);
          this.donation = d;
        });
      }
    })
  }

  ngOnDestroy(): void {
    if( this.sub ){
      this.sub.unsubscribe();
    }
  }

  saveCase() {
    this.donationsSvc.createDonation(this.donation).subscribe(c => {
      this.donation = c;
    })
  }

  cancel() {
    this.router.navigate(['/inventory']);
  }
}
