import {Component, Input} from '@angular/core';
import {V1Commodity} from "../../generated/api_client";

@Component({
  selector: 'app-commodities',
  templateUrl: './commodities.component.html'
})
export class CommoditiesComponent {

  @Input() commodities: Array<V1Commodity> = [];

}
