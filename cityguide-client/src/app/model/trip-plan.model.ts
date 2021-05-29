import { CityLocation } from "./city-location.model";
import { TripPlanRequest } from "./trip-plan-request.model";

export class TripPlan {
    constructor(
        public request : TripPlanRequest,
        public locations : CityLocation[]
    ) {}
}
