export class TripPlanRequest {
    constructor(
        public typeIds : number[],
        public durationInDays : number
    ) {}
}
